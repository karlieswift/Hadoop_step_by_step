package com.tay.hive;


import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karlieswift
 * date: 2020/6/5 17:06
 * ClassName: StringLengthUDTF
 * @version java "13.0.1"
 */
public class StringLengthUDTF extends GenericUDTF {

    private ArrayList<String> arrayList=new ArrayList<>();

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<? extends StructField> allStructFieldRefs = argOIs.getAllStructFieldRefs();
        if(allStructFieldRefs.size()!=2){
            throw new UDFArgumentException("参数错误");
        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add("word");
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(strings, fieldOIs);

    }

    @Override
    public void process(Object[] args) throws HiveException {

        String s = args[0].toString();
        String s1 = args[1].toString();
        String []strings=s.split(s1);
        for (String string : strings) {
            arrayList.clear();
            arrayList.add(string);
            forward(arrayList);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
 
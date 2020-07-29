package com.tay.hive;


import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * @author karlieswift
 * date: 2020/6/5 15:13
 * ClassName: StringLengthUDF
 * @version java "13.0.1"
 */
public class StringLengthUDF extends GenericUDF {

    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
       if(arguments.length!=1){
           throw new UDFArgumentException("参数错误！！！！");
       }
       if(!arguments[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
           throw new UDFArgumentException("参数类型错误！！！！");
       }
       return PrimitiveObjectInspectorFactory.javaIntObjectInspector ;
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
      return arguments[0].get().toString().length();
    }

    @Override
    public String getDisplayString(String[] children) {
        return "";
    }
}
 
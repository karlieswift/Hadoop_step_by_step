package com.tay.reducejoin1;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author karlieswift
 * date: 2020/5/26 13:18
 * ClassName: grouping
 * @version java "13.0.1"
 */
public class grouping extends WritableComparator {

    public grouping(){
        super(Order.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order o1=(Order)a;
        Order o2=(Order)b;
        return  o1.getPid().compareTo(o2.getPid());
    }
}
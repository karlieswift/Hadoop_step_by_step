package com.tay.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author karlieswift
 * date: 2020/5/23 16:07
 * ClassName: OrderGroup
 * @version java "13.0.1"
 */
public class OrderGroup extends WritableComparator {
    public OrderGroup(){
        super(Order.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order o1=(Order)a;
        Order o2=(Order)b;
        int temp=o1.order_id.compareTo(o2.order_id);
         return  temp;

    }
}
 
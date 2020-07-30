package com.tay.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/23 14:55
 * ClassName: Order
 * @version java "13.0.1"
 */
public class Order implements WritableComparable<Order> {
    String  order_id; //订单id
    String  product_id;  //产品id
    double price;  //产品价格
    public Order(){

    }

    public Order(String order_id, String product_id, double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.price = price;
    }

    @Override
    public int compareTo(Order o) {
   int temp=this.order_id.compareTo(o.order_id);
        if(temp!=0){
            return temp;
        }
        else   return  (int)(o.price-this.price);

    }

    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(product_id);
        out.writeUTF(order_id);
        out.writeDouble(price);

    }

    @Override
    public void readFields(DataInput in) throws IOException {

        this.product_id=in.readUTF();
        this.order_id=in.readUTF();
        this.price=in.readDouble();
    }

    @Override
    public String toString() {
        return   order_id + '\t' + product_id + '\t'+price ;
    }
}
 
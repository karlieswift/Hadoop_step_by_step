package com.tay.reducejoin1;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 9:21
 * ClassName: Order
 * @version java "13.0.1"
 */
public class Order implements WritableComparable<Order> {
    private String oid;
    private String Pid;
    private int amount;
    private String Pname;
    private String flag;

    public String getId() {
        return oid;
    }

    public void setId(String oid) {
        this.oid = oid;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(oid);
        out.writeUTF(Pid);
        out.writeInt(amount);
        out.writeUTF(Pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

       this.oid=in.readUTF();
        this.Pid=in.readUTF();
        this.amount=in.readInt();
        this.Pname=in.readUTF();
        this.flag=in.readUTF();
    }


    public String toString() {
        return oid+"\t"+Pname+"\t"+amount;
    }


//    @Override
//    public String toString() {
//        return "Order{" +
//                "oid='" + oid + '\'' +
//                ", Pid='" + Pid + '\'' +
//                ", amount=" + amount +
//                ", Pname='" + Pname + '\'' +
//                ", flag='" + flag + '\'' +
//                '}';
//    }

    @Override
    public int compareTo(Order o) {
        int temp=this.getPid().compareTo(o.getPid()) ;
        if(temp!=0){
            return temp;
        }
        else{
            return  -this.flag.compareTo(o.flag);
        }
    }
}
 
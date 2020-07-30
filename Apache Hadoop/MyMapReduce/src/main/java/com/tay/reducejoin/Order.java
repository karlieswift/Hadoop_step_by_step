package com.tay.reducejoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/26 9:21
 * ClassName: Order
 * @version java "13.0.1"
 */
public class Order implements Writable {
    private String id;
    private String Pid;
    private int amount;
    private String Pname;
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        out.writeUTF(id);
        out.writeUTF(Pid);
        out.writeInt(amount);
        out.writeUTF(Pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

        this.id = in.readUTF();
        this.Pid = in.readUTF();
        this.amount = in.readInt();
        this.Pname = in.readUTF();
        this.flag = in.readUTF();
    }


    @Override
    public String toString() {
        return id + "\t" + Pname + "\t" + amount;
    }
}

//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "id='" + id + '\'' +
//                ", Pid='" + Pid + '\'' +
//                ", amount=" + amount +
//                ", Pname='" + Pname + '\'' +
//                ", flag='" + flag + '\'' +
//                '}';
//    }
//
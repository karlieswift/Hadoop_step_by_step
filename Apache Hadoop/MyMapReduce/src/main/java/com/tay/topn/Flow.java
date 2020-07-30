package com.tay.topn;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/27 9:39
 * ClassName: Flow
 * @version java "13.0.1"
 */
public class Flow implements WritableComparable<Flow> {
    private int up_flow;
    private int down_flow;
    private int sum_flow;

    public Flow(){

    }

    public Flow(int up_flow, int down_flow, int sum_flow) {
        this.up_flow = up_flow;
        this.down_flow = down_flow;
        this.sum_flow = sum_flow;
    }

    public int getUp_flow() {
        return up_flow;
    }

    public void setUp_flow(int up_flow) {
        this.up_flow = up_flow;
    }

    public int getDown_flow() {
        return down_flow;
    }

    public void setDown_flow(int down_flow) {
        this.down_flow = down_flow;
    }

    public int getSum_flow() {
        return sum_flow;
    }

    public void setSum_flow(int sum_flow) {
        this.sum_flow = sum_flow;
    }

    @Override
    public String toString() {
        return up_flow+"\t"+down_flow+"\t"+sum_flow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(up_flow);
        out.writeInt(down_flow);
        out.writeInt(sum_flow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.up_flow=in.readInt();
        this.down_flow=in.readInt();
        this.sum_flow=in.readInt();
    }



    @Override
    public int compareTo(Flow o) {
      return o.sum_flow-this.sum_flow;
    }
}

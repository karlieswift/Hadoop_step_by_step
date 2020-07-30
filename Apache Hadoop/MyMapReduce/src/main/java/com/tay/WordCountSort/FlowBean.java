package com.tay.WordCountSort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author karlieswift
 * date: 2020/5/22 12:31
 * ClassName: FlowBean
 * @version java "13.0.1"
 *
 * 对数据进行分区 输出
 * 136--p0
 * 137--p1
 * 138--p2
 * 139--p3
 * ohter--p4
 *
 * 个别数据格式
 * 7 	13590439668	192.168.100.4		        	1116	954	    200
 * 8 	15910133277	192.168.100.5	www.hao123.com	3156	2936	200
 * 9 	13729199489	192.168.100.6	        		240	       0	200
 *
 */
public class FlowBean implements WritableComparable<FlowBean> {
    private int up_flow;
    private int down_flow;
    private int sum_flow;

    public FlowBean(){

    }

    public FlowBean(int up_flow, int down_flow, int sum_flow) {
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
        return up_flow+"\t" +down_flow+"\t"+sum_flow;
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
    public int compareTo(FlowBean o) {
        return this.getSum_flow()-o.getSum_flow();
    }
}
 
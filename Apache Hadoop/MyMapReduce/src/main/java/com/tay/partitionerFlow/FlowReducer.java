package com.tay.partitionerFlow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/22 12:50
 * ClassName: FlowReducer
 * @version java "13.0.1"
 */
public class FlowReducer extends Reducer<Text, FlowBean,Text, FlowBean> {

    private FlowBean out_value;
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        int sum_up_flow=0;
        int sum_down_flow=0;
        int sum_sum_flow=0;
        for (FlowBean value : values) {
            sum_up_flow+=value.getUp_flow();
            sum_down_flow+=value.getDown_flow();
        }
        sum_sum_flow=sum_down_flow+sum_up_flow;
        out_value=new FlowBean(sum_up_flow,sum_down_flow,sum_sum_flow);
        context.write(key,out_value);
    }
}
 
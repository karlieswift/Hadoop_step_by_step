package com.tay.topn;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

/**
 * datas:
 13470253144	180	180	360
 13509468723	7335	110349	117684
 13560439638	918	4938	5856
 13568436656	3597	25635	29232
 13590439668	1116	954	2070
 13630577991	6960	690	7650
 13682846555	1938	2910	4848
 13729199489	240	0	240
 13736230513	2481	24681	27162
 13768778790	120	120	240
 13846544121	264	0	264
 13956435636	132	1512	1644
 13966251146	240	0	240
 13975057813	11058	48243	59301
 13992314666	3008	3720	6728
 15043685818	3659	3538	7197
 15910133277	3156	2936	6092
 15959002129	1938	180	2118
 18271575951	1527	2106	3633
 18390173782	9531	2412	11943
 84188413	4116	1432	5548
 */

/**
 * @author karlieswift
 * date: 2020/5/27 9:38
 * ClassName: TopnReducer
 * @version java "13.0.1"
 */
public class TopnReducer extends Reducer<Flow,Text,Text,Flow> {

    TreeMap<Flow,String> tree=new TreeMap<>();

    @Override
    protected void reduce(Flow key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        int down=0;
        int up=0;
        for (Text value : values) {
            System.out.println(value.toString());
            up+=key.getUp_flow();
            down+=key.getDown_flow();
            sum+=key.getSum_flow();
            tree.put(new Flow(up,down,sum),value.toString());
            if(tree.size()>10){
                tree.remove(tree.lastKey());
            }
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Flow flow : tree.keySet()) {
            context.write(new Text(tree.get(flow)),flow);
        }
    }
}

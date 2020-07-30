package com.tay.commomfans;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author karlieswift
 * date: 2020/5/27 17:40
 * ClassName: FansMapper
 * @version java "13.0.1"
 * datas:
A:B,C,D,F,E,O
B:A,C,E,K
C:F,A,D,I
D:A,E,F,L
E:B,C,D,M,L
F:A,B,C,D,E,O,M
G:A,C,D,E,F
H:A,C,D,E,O
I:A,O
J:B,O
K:A,C,D
L:D,E,F
M:E,F,G
O:A,H,I,J
 */
public class FansMapper extends Mapper<LongWritable, Text,Text,Text> {
    /**
     * 将每行数据  A:B,C,D 转化为
     * A:B
     * A:C
     * A:D
     * 输出
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(":");
        String start=split[0];
        String[] split1 = split[1].split(",");
        for (String s : split1) {
            context.write(new Text(start),new Text(s));
        }
    }
}
 
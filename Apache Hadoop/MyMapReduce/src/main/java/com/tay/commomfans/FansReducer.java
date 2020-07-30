package com.tay.commomfans;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;

/**
 * @author karlieswift
 * date: 2020/5/27 17:40
 * ClassName: FansReducer
 * @version java "13.0.1"
 */
public class FansReducer extends Reducer<Text,Text,Text,Text> {
    //定义一个集合key=="string" value="int[]"  数组是存放String 的其他粉丝,A---Z,将其A--Z 映射到0-25
    //通过利用散列算法的思想，进行记录。
    HashMap<String,int[]>  map=new HashMap<String,int[]>() ;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        /**
         * 对 key-values 遍历
         * A:B
         * A:C
         * A:D
         */
        int []temp=new int[26];
        for (Text value : values) {
            int hashcode=value.toString().charAt(0)-'A'; //将A-Z映射到0-25
            temp[hashcode]=1;//散列思想 记录value这个fans在列表里
            // 此时temp={0,1,1,1,1,0.............}
        }
        map.put(key.toString(),temp);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Object[] set =  map.keySet().toArray();
        for(int i=0;i<set.length;i++){
           int []temp1=map.get(set[i]);
            for(int j=i+1;j<set.length;j++) {
                boolean flag=false;
                StringBuilder sb=new StringBuilder();
               int[] temp2 = map.get(set[j]);
               for (int k = 0; k < temp2.length; k++) {
                   if (temp1[k] == 1 && temp2[k] == 1) {
                       //查看是否有共同的粉丝
                       // temp1={0,1,1,0.............}-->{A,B,C,D..............}
                       // temp2={0,0,1,0.............}-->{A,B,C,D..............}
                       //所以k=2时 --->  C为 A与B的共同粉丝
                       sb.append(" " + (char) (k + 65)); //返回原来的fans
                       flag=true;
                   }
               }
               if(flag==true)
               context.write(new Text(set[i].toString()+"-"+set[j].toString()),new Text(sb.toString()));
           }
       }
    }
}


/**
 * 结果：
 * A-B	 C E
 * A-C	 D F
 * A-D	 E F
 * A-E	 B C D
 * A-F	 B C D E O
 * A-G	 C D E F
 * A-H	 C D E O
 * A-I	 O
 * A-J	 B O
 * A-K	 C D
 * A-L	 D E F
 * A-M	 E F
 * B-C	 A
 * B-D	 A E
 * B-E	 C
 * B-F	 A C E
 * B-G	 A C E
 * B-H	 A C E
 * B-I	 A
 * B-K	 A C
 * B-L	 E
 * B-M	 E
 * B-O	 A
 * C-D	 A F
 * C-E	 D
 * C-F	 A D
 * C-G	 A D F
 * C-H	 A D
 * C-I	 A
 * C-K	 A D
 * C-L	 D F
 * C-M	 F
 * C-O	 A I
 * D-E	 L
 * D-F	 A E
 * D-G	 A E F
 * D-H	 A E
 * D-I	 A
 * D-K	 A
 * D-L	 E F
 * D-M	 E F
 * D-O	 A
 * E-F	 B C D M
 * E-G	 C D
 * E-H	 C D
 * E-J	 B
 * E-K	 C D
 * E-L	 D
 * F-G	 A C D E
 * F-H	 A C D E O
 * F-I	 A O
 * F-J	 B O
 * F-K	 A C D
 * F-L	 D E
 * F-M	 E
 * F-O	 A
 * G-H	 A C D E
 * G-I	 A
 * G-K	 A C D
 * G-L	 D E F
 * G-M	 E F
 * G-O	 A
 * H-I	 A O
 * H-J	 O
 * H-K	 A C D
 * H-L	 D E
 * H-M	 E
 * H-O	 A
 * I-J	 O
 * I-K	 A
 * I-O	 A
 * K-L	 D
 * K-O	 A
 * L-M	 E F
 */
package com.tay.other;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author karlieswift
 * date: 2020/7/3 16:35
 * ClassName: MapCombine
 * @version java "13.0.1"
 *
 * map集合相加
 * output
 * a:3
 * b:6
 * c:5
 * d:3
 */
public class MapCombine1 {

    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a",2);
        map1.put("b",6);
        map1.put("c",3);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("a",1);
        map2.put("c",2);
        map2.put("d",3);

        Iterator<String> iterator = map2.keySet().iterator();
        while (iterator.hasNext()){
            String k=iterator.next();
            int v=map2.get(k);
            if(!map1.containsKey(k)){
                map1.put(k,v);
            }else{
                map1.put(k,v+map1.get(k));
            }
        }


        Iterator<String> iterator1 = map1.keySet().iterator();
        while (iterator1.hasNext()){
            String k=iterator1.next();
            int v=map1.get(k);
            System.out.println(k+":"+v);
        }
    }
}
 
package com.tay.flume.selfdefine;

import com.alibaba.fastjson.JSON;

/**
 * @author karlieswift
 * date: 2020/6/17 13:17
 * ClassName: Utils
 * @version java "13.0.1"
 */
public class Utils {

    public static boolean isJsonfile(String logs){
        try {
            JSON.parse(logs);
            return true;
        } catch (Exception e) {
           return false;
        }
    }
}
 
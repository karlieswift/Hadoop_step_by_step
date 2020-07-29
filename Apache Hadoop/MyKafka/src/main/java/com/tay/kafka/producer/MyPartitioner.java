package com.tay.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义kafka分区器
 * @author karlieswift
 * date: 2020/6/13 13:38
 * ClassName: MyPartitioner
 * @version java "13.0.1"
 */
public class MyPartitioner implements Partitioner {
    /**
     * 计算当前消息的分区号,int partition()发生在序列化后
     * 模拟实现：
     *       使用某个topic主题,有2个分区
     *       带有"tay"关键字的分到 1分区
     *       带有非"tay"的关键字分到 0分区
     * @param topic 主题
     * @param key  消息的key  The key to partition on (or null if no key)
     * @param keyBytes 序列化后的key The serialized key to partition on( or null if no key)
     * @param value  消息的value  The value to partition on or null
     * @param valueBytes  序列化的value  The serialized value to partition on or null
     * @param cluster  The current cluster metadata
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(value.toString().contains("tay")){
            return 1;
        }else {
            return 0;
        }
    }
    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
 
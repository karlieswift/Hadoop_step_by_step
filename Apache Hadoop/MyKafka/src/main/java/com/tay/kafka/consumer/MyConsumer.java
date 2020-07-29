package com.tay.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author karlieswift
 * date: 2020/6/13 14:55
 * ClassName: MyConsumer
 * @version java "13.0.1"
 */
public class MyConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //1-配置类
        //ProducerConfig 生产者的配置类
        //CommonClientConfigs 生产者与消费者的通用配置类
        //ConsumerConfig 消费者的配置类
        //指定主机位置
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop2:9092");
        //指定消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"tay_group1");
        //指定是否自动提交offset
       properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
//       properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
       //提交offset的间隔
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");



        /**
         * 满足两种情况会重置offset:
         *   1. 当前的消费者组之前没有在kafka中消费过(新的组新的人)
         *   2. 当前想消费者组中的某个消费者的offset 在kafka中对应的数据已经被删除(默认超过7天会删除)
         *
         * 重置的方式:
         *   earliest: automatically reset the offset to the earliest offset(头)
         *   latest: automatically reset the offset to the latest offset(尾) 默认值
         */
        // offset的重置
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");


        //指定kv的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //2-订阅topic
        ArrayList<String> topics = new ArrayList<>();
        topics.add("kk");
        topics.add("second");
        topics.add("mm");//不存在
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String,String>(properties);
        kafkaConsumer.subscribe(topics);

        //3.消费
        while (true){
            //拉取数据，拉取失败休眠 1s
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> s : poll) {
                System.out.println(s.topic()+"-"+s.partition()+"-"+s.offset()+"-"+s.key()+"-"+s.value());
            }
        }
    }
}
 
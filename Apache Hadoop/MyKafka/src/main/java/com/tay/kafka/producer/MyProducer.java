package com.tay.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

/**
 * @author karlieswift
 * date: 2020/6/13 11:09
 * ClassName: MyProducer
 * @version java "13.0.1"
 */
public class MyProducer {

    public static void main(String[] args) {  //main thread
        //1.create a properties
        Properties properties = new Properties();
        //kafka集群，broker-list
        properties.put("bootstrap.servers", "hadoop2:9092");
        //指定ack的级别，默认为 1，all==-1
        properties.put("acks", "all");
        //重试次数
        properties.put("retries", 5);
        //批次大小 16Kb
        properties.put("batch.size", 16384);
        //等待时间,默认 0ms
        properties.put("linger.ms", 1);
        //RecordAccumulator缓冲区大小
        properties.put("buffer.memory", 33554432);

        //key ,value序列化器
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //2.create a producer
        KafkaProducer kafkaProducer = new KafkaProducer<String,String>(properties);
        //3.生产数据
        for(int i=0;i<100;i++){
            kafkaProducer.send(new ProducerRecord("second",i+"-->"+UUID.randomUUID().toString()));
        }

        //4 关闭生产者对象
        kafkaProducer.close();
    }
}
 
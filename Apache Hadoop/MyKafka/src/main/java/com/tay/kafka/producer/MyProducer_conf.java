package com.tay.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author karlieswift
 * date: 2020/6/13 11:39
 * ClassName: MyProducer_conf
 * @version java "13.0.1"
 */
public class MyProducer_conf {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //配置类
        //ProducerConfig 生产者的配置类
        //CommonClientConfigs 生产者与消费者的通用配置类
        //ConsumerConfig 消费者的配置类
        //指定主机位置
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop2:9092");
        //ack级别
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,"6");
        //缓冲区大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        //指定等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        //指定RecordAccumulator缓冲区的大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        //指定key,value的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.tay.kafka.producer.MyPartitioner");

        KafkaProducer kafkaProducer = new KafkaProducer<String,String>(properties);
        for(int i=0;i<100;i++){
          //  kafkaProducer.send(new ProducerRecord("second",i+"--kafka")); ////黏性分区
          //  kafkaProducer.send(new ProducerRecord("second","key",i+"--kafka"));//指定key,通过hash值分区
         //  kafkaProducer.send(new ProducerRecord("second",0,null,i+"--kafka"));//指定分区
            if(i%2==0){
                kafkaProducer.send(new ProducerRecord("second",i+"-hi kafka")); ////黏性分区
            }else {
                kafkaProducer.send(new ProducerRecord("second",i+"-hi tay")); ////黏性分区
            }
        }
        kafkaProducer.close();
    }
}
 
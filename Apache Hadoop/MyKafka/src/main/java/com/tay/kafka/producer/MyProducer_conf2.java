package com.tay.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 同步发送
 * @author karlieswift
 * date: 2020/6/13 11:39
 * ClassName: MyProducer_conf
 * @version java "13.0.1"
 */
public class MyProducer_conf2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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

        KafkaProducer kafkaProducer = new KafkaProducer<String,String>(properties);
        for(int i=0;i<6;i++){
            //带回调的api
            Future future = kafkaProducer.send(new ProducerRecord("second", i + "--kafka"), new Callback() {
                /**
                 * 消息发生完成执行完成后，会调用该方法
                 * @param metadata  消息发送成功后，metadata会存储当前消息的元数据
                 * @param exception  x消息发送失败，exception会存储相应的异常信息
                 */
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                    if (exception != null) {
                        System.out.println("发送异常");
                    } else {
                        System.out.println(metadata.topic() + "--" + metadata.partition() + "--" + metadata.offset());
                    }
                }
            });
            System.out.println("消息发送完成");
           Object o = future.get(); //阻塞进程，直到get()方法返回结果

        }
        kafkaProducer.close();
    }
}
 
package com.tay.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author karlieswift
 * date: 2020/6/13 16:49
 * ClassName: ProducerInterceptor
 * @version java "13.0.1"
 */
public class ProducerInterceptor {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop2:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,6);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //构建拦截链
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("com.tay.kafka.interceptor.CounterInterceptor");
        interceptors.add("com.tay.kafka.interceptor.TimeInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);


        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);

        for(int i=0;i<10;i++){

            kafkaProducer.send(new ProducerRecord("second","message:"+i));
        }

        kafkaProducer.close();


    }
}
 
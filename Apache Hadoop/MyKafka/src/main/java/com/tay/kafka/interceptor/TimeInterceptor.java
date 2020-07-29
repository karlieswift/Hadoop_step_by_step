package com.tay.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 增加时间戳拦截器
 * @author karlieswift
 * date: 2020/6/13 16:33
 * ClassName: TimeInterceptor
 * @version java "13.0.1"
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {
    // ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value)
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        return new ProducerRecord(record.topic(),record.partition(),record.key(),
                System.currentTimeMillis()+"-"+record.value());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
 
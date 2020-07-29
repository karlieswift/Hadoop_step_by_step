package com.tay.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 统计发送消息成功和发送失败消息数，并在producer关闭时打印这两个计数器
 * @author karlieswift
 * date: 2020/6/13 16:44
 * ClassName: CounterInterceptor
 * @version java "13.0.1"
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {
    private int errorcounts=0;
    private int successcounts=0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        if(exception!=null){
            errorcounts++;
        }else{
            successcounts++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功次数:"+successcounts);
        System.out.println("失败次数:"+errorcounts);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
 
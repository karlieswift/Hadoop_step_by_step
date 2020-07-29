package com.tay.flume_kafka_interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * 将flume作为source,kafka作为sink,进行拦截器，
 * "taylor"-->first主题
 * "karlie"-->second主题
 * 其他进入-->third主题
 * @author karlieswift
 * date: 2020/6/13 21:45
 * ClassName: Flume_Kafka_Interceptor
 * @version java "13.0.1"
 */
public class Flume_Kafka_Interceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody());
        Map<String, String> headers = event.getHeaders();
        if(body.contains("taylor")){
            headers.put("topic","first");
        }else if(body.contains("karlie")){
            headers.put("topic","second");
        }
        //这里没有指定其他的进third主题，我们在flume的配置文件里显示的配置third,
        //就是说默认是third
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Mybuilder implements Builder{

        @Override
        public Interceptor build() {
            return new Flume_Kafka_Interceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
 
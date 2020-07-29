package com.tay.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * @author karlieswift
 * date: 2020/6/10 9:26
 * ClassName: LogInterceptor
 * @version java "13.0.1"
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        String s = new String(event.getBody());
        if(s.contains("tay")){
            headers.put("title","tay");
        }else {
            headers.put("title","other");
        }
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


    public static class MyBuilder implements Builder{

        @Override
        public Interceptor build() {
            return new LogInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
 
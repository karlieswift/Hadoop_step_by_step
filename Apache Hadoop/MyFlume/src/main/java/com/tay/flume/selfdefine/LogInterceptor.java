package com.tay.flume.selfdefine;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * @author karlieswift
 * date: 2020/6/17 13:16
 * ClassName: LogInterceptor
 * @version java "13.0.1"
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String s = new String(event.getBody(), StandardCharsets.UTF_8);
        if(Utils.isJsonfile(s)){
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        Iterator<Event> iterator = list.iterator();
        while (iterator.hasNext()){
            if(intercept(iterator.next())==null){
                iterator.remove();
            }
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
 
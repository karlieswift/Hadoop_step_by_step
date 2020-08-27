package com.tay.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author karlieswift
 * date: 2020/8/21 8:49
 * ClassName: Jedis_api
 * @version java "13.0.1"
 */
public class Jedis_api {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.1.102", 6379);
//        jedis.auth("123456");
        Jedis jedis = RedisUtils.getJedisFromPool(); //连接池
        String ping = jedis.ping();
        System.out.println(ping); //PONG


//        5.4.1.	Jedis-API:    Key
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size()); //3
        for (String key : keys) {
            System.out.println(key); //k3 k1 k2
        }
        System.out.println(jedis.exists("k1")); //true
        System.out.println(jedis.ttl("k1"));//-1  查看还有多少秒过期，-1表示永不过期，-2表示已过期
        System.out.println(jedis.get("k1"));//v1



//        5.4.2.	Jedis-API:    String
        jedis.mset("str1","v1","str2","v2","str3","v3");
        System.out.println(jedis.mget("str1","str2","str3")); //[v1, v2, v3]

//        5.4.4.	Jedis-API:    set
        jedis.sadd("orders", "order01");
        jedis.sadd("orders", "order02");
        jedis.sadd("orders", "order03");
        jedis.sadd("orders", "order04");
        Set<String> smembers = jedis.smembers("orders");//取出该集合的所有值。
        for (String order : smembers) {
            System.out.println(order);
        }
        /**
         * order03
         * order04
         * order02
         * order01
         */


        jedis.srem("orders", "order02");// 删除集合中的某个元素。


//        5.4.5.	Jedis-API:    hash
        jedis.hset("hash1","userName","tay");
        System.out.println(jedis.hget("hash1","userName"));//tay
        Map<String,String> map = new HashMap<String,String>();
        map.put("telphone","13810169999");
        map.put("address","haha");
        map.put("email","abc@163.com");
        jedis.hmset("hash2",map);
        List<String> result = jedis.hmget("hash2", "telphone","email");
        for (String element : result) {
            System.out.println(element);
        }
//        5.4.6.	Jedis-API:    zset
        jedis.zadd("zset01", 100d, "z3");
        jedis.zadd("zset01", 90d, "l4");
        jedis.zadd("zset01", 80d, "w5");
        jedis.zadd("zset01", 70d, "z6");
        Set<String> zrange = jedis.zrange("zset01", 0, -1);//返回有序集 key 中，下标在<start> <stop>之间的元素
        for (String e : zrange) {
            System.out.println(e);
        }


        jedis.close();
    }
}
 
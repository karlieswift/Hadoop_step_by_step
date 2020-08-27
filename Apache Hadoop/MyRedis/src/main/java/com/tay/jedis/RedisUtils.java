package com.tay.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author karlieswift
 * date: 2020/8/21 9:35
 * ClassName: RedisUtils
 * @version java "13.0.1"
 *
 * 连接池
 * 节省每次连接redis服务带来的消耗，把连接好的实例反复利用。
 * 通过参数管理连接的行为
 *
 */
public class RedisUtils {

    /**
     * 链接池参数
     * MaxTotal：控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制；
     *            如果pool已经分配了MaxTotal个jedis实例，则此时pool的状态为exhausted。
     * maxIdle：控制一个pool最多有多少个状态为idle(空闲)的jedis实例；
     * minIdle：控制一个pool最少有多少个状态为idle(空闲)的jedis实例；
     * BlockWhenExhausted：连接耗尽是否等待
     * MaxWaitMillis：表示当borrow一个jedis实例时，最大的等待毫秒数，如果超过等待时间，则直接抛JedisConnectionException；
     * testOnBorrow：获得一个jedis实例的时候是否检查连接可用性（ping()）；如果为true，则得到的jedis实例均是可用的；
     */

    private static JedisPool jedisPool=null;

    public static Jedis getJedisFromPool(){
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong


//            jedisPool=new JedisPool(jedisPoolConfig,"hadoop102", 6379 );
            jedisPool=new JedisPool(jedisPoolConfig,"hadoop102", 6379,2000,"123456");
            return jedisPool.getResource();
        }else{
            return jedisPool.getResource();
        }
    }

}
 
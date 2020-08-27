package com.tay.jedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;
/**
 * @author karlieswift
 * date: 2020/8/24 13:11
 * ClassName: Redis_Cluster
 * @version java "13.0.1"
 */
public class Redis_Cluster {

    public static void main(String[] args) {

        JedisCluster jedisCluster = getJedisCluster();
        jedisCluster.set("k100", "v100");
        String k100 = jedisCluster.get("k100");
        System.out.println(k100);
    }

    private static JedisCluster jedisCluster=null;

    public  static JedisCluster getJedisCluster(){
        if(jedisCluster==null){

            Set<HostAndPort> hostAndPortSet =new HashSet<>();
            hostAndPortSet.add(new HostAndPort("hadoop102",6379));
            hostAndPortSet.add(new HostAndPort("hadoop102",6380));
//            hostAndPortSet.add(new HostAndPort("hadoop102",6381));
//            hostAndPortSet.add(new HostAndPort("hadoop102",6382));
//            hostAndPortSet.add(new HostAndPort("hadoop102",6383));
//            hostAndPortSet.add(new HostAndPort("hadoop102",6384));

            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            jedisCluster=new JedisCluster(hostAndPortSet,jedisPoolConfig);
            return  jedisCluster;
        }else{
            return  jedisCluster;
        }

    }





}
 
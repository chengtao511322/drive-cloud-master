package com.drive.common.redis.util;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

/**
 * @ClassName JedisConnect
 * @Description TODO：
 * @Author @{用户} 小郭
 * @Date @{时间} 2020/3/30 15:57
 * @Version 1.0
 **/
public class JedisConnect {

    // 指向自己实例的私有静态引用
    private static Jedis jedisConnect;

    // 私有的构造方法
    private JedisConnect(){}

    // 以自己实例为返回值的静态的公有方法，静态工厂方法 双重检查锁定
    public static Jedis getJedisConnect(){
        // 被动创建，在真正需要使用时才去创建,双重验证
        if (jedisConnect == null) {
            synchronized (Jedis.class) {
                if (jedisConnect == null) {
                    jedisConnect = getConnect();
                }
            }

        }
        return jedisConnect;
    }

    static Jedis getConnect(){
        Jedis jedis = null;
      try {
          //jedis=new Jedis("172.24.86.62",4579);// 创建客户端 设置IP和端口
          //jedis=new Jedis("172.24.86.61",5300);// 创建客户端 设置IP和端口
          jedis=new Jedis("125.0.8.191",6379);// 创建客户端 设置IP和端口

          //jedis.auth("yqkj888.");// 设置密码
          //jedis.auth("yqkj888.redis");// 设置密码
          jedis.auth("xiaoguo");// 设置密码
          jedis.select(2);
     /*   jedis.set("name","java知识分享网");// 设置值

        String value=jedis.get("name");// 获取值
        System.out.println(value);
        jedis.close();// 释放连接资源*/
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          jedis.close();
      }
        return jedis;
    }

    // get 方法
    public static JSONObject get(String key){
        Jedis jedisConnect= getJedisConnect();
        return JSONObject.parseObject(jedisConnect.get(key));
    }

    // get 方法
    public static String set(String key,String value){
        Jedis jedisConnect= getJedisConnect();
        return jedisConnect.set(key,value);
    }

    public static void main(String[] args) {
        //JedisConnect.set("xiaoguo","xiaoguo");
        System.out.println(JedisConnect.get("operator_dept:bbdc1bd499b241daa6fe99063e63a193"));;
        System.out.println(get("REDIS_STUDENT_KEY:93ca25ba866848c0ac3c:STUDENT_DATA"));
    }
}

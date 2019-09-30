package com.hd.config;


import com.hd.common.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService{

    @Autowired
    RedisTemplate<String ,Token> redisTemplate;
    @Autowired
    RedisTemplate<String ,Integer> integerRedisTemplate;



    public void putTokenWithExpireTime(String key,Token token, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key,token,timeout,unit);
    }

    public void putToken(String key,Token token){
        redisTemplate.opsForValue().set(key,token);
    }
    public Object getToken(String key) {
        return  redisTemplate.opsForValue().get(key) ;
    }


    public void setExpire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public  void putKey(String key , Integer value ){
        integerRedisTemplate.opsForValue().set(key,value);
    }

    public Object getKey(String key){
        return integerRedisTemplate.opsForValue().get(key);
    }

    public long generate(String key, int increment, long timeout, TimeUnit unit) {

        RedisAtomicLong counter = new RedisAtomicLong(key, integerRedisTemplate.getConnectionFactory());
        counter.expire(timeout, unit);
        return counter.addAndGet(increment);
    }

}

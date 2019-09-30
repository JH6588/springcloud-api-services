package com.hd.config;

import com.hd.common.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {
    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;
    @Value("${spring.redis.port}")
    private int REDIS_PORT;
    @Value("${spring.redis.password}")
    private String REDIS_PASSWORD;

    @Bean
    protected JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig  poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(150);
        poolConfig.setMaxIdle(30);
        poolConfig.setMaxWaitMillis(3000L);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(REDIS_HOSTNAME,REDIS_PORT);
        redisStandaloneConfiguration.setPassword(REDIS_PASSWORD);
        return new JedisConnectionFactory(redisStandaloneConfiguration);

    }

    @Bean
    public RedisTemplate<String, Token> redisTemplate() {
        final RedisTemplate<String, Token> redisTemplate = new RedisTemplate<String, Token>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
//        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Token>(Token.class));
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String ,Integer> integerRedisTemplate(){
        final  RedisTemplate<String ,Integer> redisTemplate = new RedisTemplate<String ,Integer>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
        return redisTemplate;
    }



}
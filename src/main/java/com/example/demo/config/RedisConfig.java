package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    protected static final ConversionService conversionService = ApplicationConversionService.getSharedInstance();
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /* @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager manager = new RedisCacheManager(redisTemplate);
        manager.setUsePrefix(true);
        RedisCachePrefix cachePrefix = new RedisPrefix("prefix");
        manager.setCachePrefix(cachePrefix);
        // ????????????????????????
        manager.setDefaultExpiration(3600L);
        // ???????????????????????????key?????????????????????????????????
        Map<String, Long> expiresMap = new HashMap<>();
        expiresMap.put("user", 1000L);
        manager.setExpires(expiresMap);
        return manager;
    }*/
/*
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //???????????????RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //??????CacheManager????????????????????????JdkSerializationRedisSerializer,?????????RedisCacheConfiguration??????????????????StringRedisSerializer?????????key???JdkSerializationRedisSerializer?????????value,???????????????????????????????????????
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //??????????????????????????????30???
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //?????????RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }*/

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) throws IOException {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(Duration.ofSeconds(6000)),//???????????????????????????key???????????????
                this.getRedisCacheConfigurationMap() //??????key??????
        );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() throws IOException {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        // ??????????????????????????????????????????put??????
        List<CacheValue> ros = CacheValue.readAllConfig(applicationContext);
        ros.forEach(cacheValue -> {
            Duration ttl = conversionService.convert(cacheValue.getTtl(), Duration.class);
            redisCacheConfigurationMap.put(cacheValue.getName(), this.getRedisCacheConfigurationWithTtl(ttl)); // 120????????????
        });
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Duration seconds) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer())
        ).entryTtl(seconds);

        return redisCacheConfiguration;
    }

    private RedisSerializer jsonSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        //???????????????????????????????????????
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(jsonSerializer());
        template.afterPropertiesSet();
        return template;
    }

}

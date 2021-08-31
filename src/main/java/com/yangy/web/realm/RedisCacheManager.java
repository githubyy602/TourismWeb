package com.yangy.web.realm;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: Yangy
 * @Date: 2021/8/30 16:50
 * @Description
 */
public class RedisCacheManager implements CacheManager {
	
	private long cacheLive;    //cache存活时间
    private String cacheKeyPrefix;      //cache前缀
    private RedisTemplate redisTemplate;
 
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
 
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = this.caches.get(name);
        if (cache == null) {
            //自定义shiroCache
            cache = new RedisCache(redisTemplate, cacheLive, cacheKeyPrefix);
            this.caches.put(name, cache);
        }
        return cache;
    }
 
    public void setCacheLive(long cacheLive) {
        this.cacheLive = cacheLive;
    }
 
    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }
 
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}

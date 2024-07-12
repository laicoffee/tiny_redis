package com.github.awstan.cache.core.expire;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheExpire;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author pw7563
 * @Date 2024/7/12 16:20
 * usage
 */
public class CacheExpire <K,V>  extends Thread implements ICacheExpire<K,V>{
    
    private long limit = 100;
    
    private Map<K,Long> expireMap = new HashMap<>();

    private ICache<K,V> cache;

    private ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();

    public CacheExpire(ICache<K,V> cache) {

    }

    @Override
    public synchronized void run() {

    }

    @Override
    public void expire(K key, long expireAt) {

    }

    @Override
    public void refreshExpire(Collection<K> keyList) {

    }

    @Override
    public Long expireTime(K key) {
        return null;
    }
}

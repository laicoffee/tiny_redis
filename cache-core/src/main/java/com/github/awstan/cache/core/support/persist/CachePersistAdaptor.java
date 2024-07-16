package com.github.awstan.cache.core.support.persist;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICachePersist;

import java.util.concurrent.TimeUnit;

/**
 * @Author pw7563
 * @Date 2024/7/16 10:33
 * usage  缓存持久化-适配器模式
 */
public class CachePersistAdaptor <K,V> implements ICachePersist<K,V> {

    /**
     * 持久化
     * key长度 key+value
     * @param cache
     */
    @Override
    public void persist(ICache<K, V> cache) {

    }

    @Override
    public long delay() {
        return 0;
    }

    @Override
    public long period() {
        return 0;
    }

    @Override
    public TimeUnit timeUnit() {
        return TimeUnit.SECONDS;
    }
}

package com.github.awstan.cache.core.interceptor;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheInterceptor;
import com.github.awstan.cache.api.ICacheInterceptorContext;
import com.github.awstan.cache.api.ICachePersist;
import com.github.awstan.cache.core.support.persist.CachePersistAof;

/**
 * @Author pw7563
 * @Date 2024/7/16 11:09
 * usage
 */
public class CacheInterceptorPersist<K,V> implements ICacheInterceptor<K,V> {

    @Override
    public void before(ICacheInterceptorContext<K, V> context) {
        ICache<K, V> cache = context.cache();
        ICachePersist<K, V> persist = cache.persist();
        if (persist!= null && persist instanceof CachePersistAof) {
            CachePersistAof aof = (CachePersistAof) persist;
            aof.append(context.method()+":"+context.params().toString());
        }
    }

    @Override
    public void after(ICacheInterceptorContext<K, V> context) {

    }
}

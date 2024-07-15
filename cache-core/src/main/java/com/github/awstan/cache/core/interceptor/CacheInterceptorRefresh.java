package com.github.awstan.cache.core.interceptor;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheExpire;
import com.github.awstan.cache.api.ICacheInterceptor;
import com.github.awstan.cache.api.ICacheInterceptorContext;

/**
 * @Author pw7563
 * @Date 2024/7/15 11:07
 * usage
 */
public class CacheInterceptorRefresh<K, V> implements ICacheInterceptor<K, V> {
    @Override
    public void before(ICacheInterceptorContext<K, V> context) {
        ICache cache = context.cache();
        ICacheExpire expire = cache.expire();
        expire.refreshExpire(cache.keySet());
    }

    @Override
    public void after(ICacheInterceptorContext<K, V> context) {

    }
}

package com.github.awstan.cache.core.proxy;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.core.proxy.dynamic.DynamicProxy;

/**
 * @Author pw7563
 * @Date 2024/7/8 14:30
 * usage
 */
public class CacheProxy {

    public CacheProxy() {
    }
    
    public static <K,V> ICache<K,V> getProxy(final ICache<K,V> cache) {
//        Class<? extends ICache> clazz = cache.getClass();
        return (ICache<K, V>) new DynamicProxy(cache).proxy();

    }
    
}

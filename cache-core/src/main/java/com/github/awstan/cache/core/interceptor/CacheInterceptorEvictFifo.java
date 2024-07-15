package com.github.awstan.cache.core.interceptor;

import com.github.awstan.cache.api.*;
import com.github.awstan.cache.core.support.CacheEvictContext;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:26
 * usage
 */
public class CacheInterceptorEvictFifo<K, V> implements ICacheInterceptor<K, V> {
    @Override
    public void before(ICacheInterceptorContext<K, V> context) {

    }

    @Override
    public void after(ICacheInterceptorContext<K, V> context) {
        // fifo的淘汰直接实现在了put方法中
        // 但是由于fifo底层采用的是队列，不支持随机元素的处理，所以对于remove和update不做处理


//        Method method = context.method();
//        if(method == null) return ;
//        String name = method.getName();
//        System.out.println("拦截到的当前方法:"+ name);
//        ICache cache = context.cache();
//        ICacheEvict evict = cache.evict();
//
//        CacheEvictContext evictContext = new CacheEvictContext<K,V>();
//        evictContext.cache(context.cache());
//        evictContext.key(context.params()[0]);
//        evictContext.size(cache.size());
//        evict.updateKey(context.params()[0]);


    }
}

package com.github.awstan.cache.core.interceptor;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheInterceptorContext;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:12
 * usage
 */
public class CacheInterceptorContext <K,V> implements ICacheInterceptorContext <K,V> {

    private ICache<K, V> cache;

    private Method method;

    private Object[] params;

    private Object result;

    private long startMills;

    private long endMills;

    @Override
    public ICache<K, V> cache() {
        return cache;
    }

    @Override
    public Method method() {
        return method;
    }

    @Override
    public Object[] params() {
        return params;
    }

    @Override
    public Object result() {
        return result;
    }

    @Override
    public long startMills() {
        return 0;
    }

    @Override
    public long endMills() {
        return 0;
    }

    public CacheInterceptorContext cache(ICache<K, V> cache){
        this.cache = cache;
        return this;
    }

    public CacheInterceptorContext method(Method method){
        this.method = method;
        return this;
    }

    public CacheInterceptorContext params(Object[] params){
        this.params = params;
        return this;
    }

    public CacheInterceptorContext result(Object result){
        this.result = result;
        return this;
    }

    public CacheInterceptorContext startMills(long startMills){
        this.startMills = startMills;
        return this;
    }

    public CacheInterceptorContext endMills(long endMills){
        this.endMills = endMills;
        return this;
    }

    public static <K,V> CacheInterceptorContext<K,V> newInstance(){
        return new CacheInterceptorContext<>();
    }


}

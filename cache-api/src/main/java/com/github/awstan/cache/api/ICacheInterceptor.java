package com.github.awstan.cache.api;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:24
 * usage 拦截器处理器接口
 */
public interface ICacheInterceptor<K, V> {

    void before(ICacheInterceptorContext<K, V> context);

    void after(ICacheInterceptorContext<K, V> context);

}

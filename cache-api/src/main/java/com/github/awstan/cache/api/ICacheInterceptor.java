package com.github.awstan.cache.api;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:24
 * usage 拦截器处理器接口
 */
public interface ICacheInterceptor {

    void before(ICacheInterceptorContext context);

    void after(ICacheInterceptorContext context);

}

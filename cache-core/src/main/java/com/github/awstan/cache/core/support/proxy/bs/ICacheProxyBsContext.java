package com.github.awstan.cache.core.support.proxy.bs;

import com.github.awstan.cache.annotation.CacheInterceptor;
import com.github.awstan.cache.api.ICache;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/9 10:41
 * usage
 */
public interface ICacheProxyBsContext {


    /**
     * 获取代理对象信息
     * @return 代理
     * @since 0.0.4
     */
    ICache target();

    /**
     * 目标对象
     * @param target 对象
     * @return 结果
     * @since 0.0.4
     */
    ICacheProxyBsContext target(final ICache target);

    /**
     * 参数信息
     * @return 参数信息
     * @since 0.0.4
     */
    Object[] params();

    /**
     * 方法信息
     * @return 方法信息
     * @since 0.0.4
     */
    Method method();

    /**
     * 方法执行
     * @return 执行
     * @since 0.0.4
     * @throws Throwable 异常信息
     */
    Object process() throws Throwable;

    CacheInterceptor interceptor();

}

package com.github.awstan.cache.api;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:12
 * usage
 *  * 拦截器上下文接口
 *  *
 *  * （1）get
 *  * （2）put
 *  * （3）remove
 *  * （4）expire
 *  * （5）evict
 */
public interface ICacheInterceptorContext<K,V> {

    /**
     * 缓存信息
     * @return
     */
    ICache<K,V> cache();

    /**
     * 执行的方法信息
     * @return
     */
    Method method();

    /**
     * 执行的方法参数
     * @return
     */
    Object[] params();

    /**
     * 方法的执行结果
     * @return
     */
    Object result();

    long startMills();

    long endMills();




}

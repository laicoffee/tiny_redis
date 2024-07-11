package com.github.awstan.cache.core.interceptor;

import com.github.awstan.cache.api.ICacheInterceptor;
import com.github.awstan.cache.api.ICacheInterceptorContext;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/11 10:26
 * usage
 */
public class CacheInterceptorEvictFifo implements ICacheInterceptor {
    @Override
    public void before(ICacheInterceptorContext context) {

    }

    @Override
    public void after(ICacheInterceptorContext context) {
        Method method = context.method();
        if(method == null) return ;
        String name = method.getName();
        System.out.println("拦截到的当前方法:"+ name);

    }
}

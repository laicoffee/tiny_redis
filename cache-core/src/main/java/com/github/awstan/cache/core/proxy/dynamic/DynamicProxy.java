package com.github.awstan.cache.core.proxy.dynamic;

import com.github.awstan.cache.annotation.CacheInterceptor;
import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEntry;
import com.github.awstan.cache.api.ICacheEvict;
import com.github.awstan.cache.core.core.Cache;
import com.github.awstan.cache.core.proxy.ICacheProxy;
import com.github.awstan.cache.core.support.CacheEvictContext;
import com.github.awstan.cache.core.support.proxy.bs.CacheProxyBs;
import com.github.awstan.cache.core.support.proxy.bs.CacheProxyBsContext;
import com.github.awstan.cache.core.support.proxy.bs.ICacheProxyBsContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author pw7563
 * @Date 2024/7/8 14:33
 * usage
 */
public class DynamicProxy implements InvocationHandler, ICacheProxy {

    /**
     * 被代理的对象
     */
    private ICache target;


    public DynamicProxy(ICache target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ICacheProxyBsContext context = CacheProxyBsContext.newInstance()
                .method(method).params(args).target(target).interceptor(method.getAnnotation(CacheInterceptor.class));

        return new CacheProxyBs().context(context).execute();
    }

    @Override
    public Object proxy() {
        InvocationHandler handler = new DynamicProxy(target);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
    }
}

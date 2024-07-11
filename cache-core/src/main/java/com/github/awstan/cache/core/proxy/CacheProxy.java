package com.github.awstan.cache.core.proxy;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.core.proxy.cglib.CglibProxy;
import com.github.awstan.cache.core.proxy.dynamic.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @Author pw7563
 * @Date 2024/7/8 14:30
 * usage
 */
public class CacheProxy {

    public CacheProxy() {
    }
    
    public static <K,V> ICache<K,V> getProxy(final ICache<K,V> target) {
        Class<?> clazz = target.getClass();

        // 判断是否是接口或者 Proxy 类
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return (ICache<K, V>) new DynamicProxy(target).proxy();
        }

        // 否则使用 CGLIB 动态代理
        return (ICache<K, V>) new CglibProxy(target).proxy();
    }
    
}

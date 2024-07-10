package com.github.awstan.cache.core.support.proxy.bs;

import com.github.awstan.cache.api.ICache;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/9 10:43
 * usage
 */
public class CacheProxyBsContext implements ICacheProxyBsContext {

    private ICache target;

    private Object[] params;

    private Method method;

    public static CacheProxyBsContext newInstance(){
        return new CacheProxyBsContext();
    }


    @Override
    public ICache target() {
        return target;
    }

    @Override
    public ICacheProxyBsContext target(ICache target) {
        this.target = target;
        return this;
    }

    public ICacheProxyBsContext params(Object[] params) {
        this.params = params;
        return this;
    }

    @Override
    public Object[] params() {
        return params;
    }

    @Override
    public Method method() {
        return method;
    }

    @Override
    public Object process() throws Throwable {
        return this.method.invoke(target,params);
    }

    public CacheProxyBsContext method(Method method) {
        this.method = method;
        return this;
    }
}

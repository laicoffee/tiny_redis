package com.github.awstan.cache.core.proxy.cglib;

import com.github.awstan.cache.annotation.CacheInterceptor;
import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.core.proxy.ICacheProxy;
import com.github.awstan.cache.core.support.proxy.bs.CacheProxyBs;
import com.github.awstan.cache.core.support.proxy.bs.CacheProxyBsContext;
import com.github.awstan.cache.core.support.proxy.bs.ICacheProxyBsContext;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/11 15:32
 * usage
 */

public class CglibProxy implements MethodInterceptor, ICacheProxy {

    /**
     * 被代理的对象
     */
    private final ICache target;

    public CglibProxy(ICache target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        ICacheProxyBsContext context = CacheProxyBsContext.newInstance()
                .method(method).params(params).target(target).interceptor(method.getAnnotation(CacheInterceptor.class));

        return CacheProxyBs.newInstance().context(context).execute();
    }

    @Override
    public Object proxy() {
        Enhancer enhancer = new Enhancer();
        //目标对象类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        //通过字节码技术创建目标对象类的子类实例作为代理
        return enhancer.create();
    }

}
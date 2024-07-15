package com.github.awstan.cache.core.support.proxy.bs;

import com.github.awstan.cache.annotation.CacheInterceptor;
import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheInterceptor;
import com.github.awstan.cache.api.ICacheInterceptorContext;
import com.github.awstan.cache.core.interceptor.CacheInterceptorContext;
import com.github.awstan.cache.core.interceptor.CacheInterceptorEvictFifo;
import com.github.awstan.cache.core.interceptor.CacheInterceptorRefresh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author pw7563
 * @Date 2024/7/9 10:50
 * usage
 */
public class CacheProxyBs {

    private Object proxy;

    private Method method;

    private Object[] args;

    private ICacheProxyBsContext context;

    /**
     * 淘汰策略拦截器
     */
    private ICacheInterceptor evictInterceptor = new CacheInterceptorEvictFifo();

    /**
     * 过期策略拦截器
     */
    private ICacheInterceptor expireInterceptor = new CacheInterceptorRefresh();


    public CacheProxyBs context(ICacheProxyBsContext context){
        this.context = context;
        return this;
    }

    public CacheProxyBs proxy(Object proxy) {
        this.proxy = proxy;
        return this;
    }

    public CacheProxyBs method(Method method){
        this.method = method;
        return this;
    }

    public CacheProxyBs args(Object[] args){
        this.args = args;
        return this;
    }

    public static CacheProxyBs newInstance(){
        return new CacheProxyBs();
    }

    public Object execute() throws InvocationTargetException, IllegalAccessException {
        long startMills = System.currentTimeMillis();
        ICache cache = context.target();
        CacheInterceptorContext interceptorContext = CacheInterceptorContext.newInstance()
                .startMills(startMills)
                .method(context.method())
                .params(context.params())
                .cache(cache);

        CacheInterceptor interceptor = context.interceptor();
        this.interceptorHandler(interceptor,interceptorContext,cache,true);

        Object result = context.method().invoke(context.target(), context.params());

        long endMills = System.currentTimeMillis();
        interceptorContext.endMills(endMills).result(result);
        this.interceptorHandler(interceptor,interceptorContext,cache,false);
        return result;
    }

    /**
     * 拦截器执行类
     * @param cacheInterceptor 缓存拦截器
     * @param context 上下文
     * @param cache 缓存
     * @param before 是否执行拦截
     */
    public void interceptorHandler(CacheInterceptor cacheInterceptor,ICacheInterceptorContext context,ICache cache,boolean before){

        // 执行过期策略
        if(expireInterceptor != null){
            if(before){
                expireInterceptor.before(context);
            }else{
                expireInterceptor.after(context);
            }
        }


        // 执行淘汰策略
        if(evictInterceptor != null && cacheInterceptor!=null && cacheInterceptor.evict()){
            if(before){
                evictInterceptor.before(context);
            }else{
                evictInterceptor.after(context);
            }
        }

    }



}

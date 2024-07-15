package com.github.awstan.cache.core.bs;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEvict;
import com.github.awstan.cache.core.core.Cache;
import com.github.awstan.cache.core.evict.CacheEvictFifo;
import com.github.awstan.cache.core.evict.CacheEvicts;
import com.github.awstan.cache.core.proxy.CacheProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author pw7563
 * @Date 2024/7/11 9:22
 * usage 缓存引导类，Cache的引导类
 */
public class CacheBs <K,V>{


    /**
     * 缓存数据 map信息
     */
    private Map<K,V> map = new HashMap<>();

    /**
     * 大小限制
     */
    private Integer size = Integer.MAX_VALUE;

    /**
     * 淘汰策略
     */
    private ICacheEvict<K,V> evict = CacheEvicts.fifo();

    public CacheBs<K,V> map(Map<K,V> map){
        this.map = map;
        return this;
    }

    public CacheBs<K,V> sizeLimit(Integer sizeLimit){
        this.size = sizeLimit;
        return this;
    }

    public CacheBs<K,V> evict(ICacheEvict<K,V> evict){
        this.evict = evict;
        return this;
    }

    public ICache<K,V> build(){
        Cache<K, V> cache = new Cache<>();
        cache.map(map);
        cache.sizeLimit(size);
        cache.evict(evict);

        cache.init();
        ICache<K, V> proxy =
                CacheProxy.getProxy(cache);
        return (ICache<K, V>) proxy;

    }



}

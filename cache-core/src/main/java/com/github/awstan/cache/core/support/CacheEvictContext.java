package com.github.awstan.cache.core.support;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEvictContext;

/**
 * @Author pw7563
 * @Date 2024/7/8 13:49
 * usage
 * 1. 新加的key
 * 2.map实现
 * 3.淘汰监听器
 */
public class CacheEvictContext<K, V> implements ICacheEvictContext<K,V> {

    /**
     * 新加的key
     */
    private K key;

    /**
     * Cache实现
     */
    private ICache<K, V> cache;

    /**
     * Cache设置的大小
     */
    private int size;



    @Override
    public K key() {
        return key;
    }

    public CacheEvictContext<K, V> key(K key) {
        this.key = key;
        return this;
    }

    @Override
    public ICache<K, V> cache() {
        return cache;
    }

    public CacheEvictContext<K, V> cache(ICache<K, V> cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    public CacheEvictContext<K, V> size(int size) {
        this.size = size;
        return this;
    }
}

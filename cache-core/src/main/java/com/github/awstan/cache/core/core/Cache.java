package com.github.awstan.cache.core.core;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEntry;
import com.github.awstan.cache.api.ICacheEvict;
import com.github.awstan.cache.core.proxy.CacheProxy;
import com.github.awstan.cache.core.support.CacheEvictContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Author pw7563
 * @Date 2024/7/8 11:00
 * usage 缓存信息
 */
public class Cache<K, V> implements ICache <K, V> {

    /**
     * 缓存数据 map信息
     */
    private Map<K,V> map;

    /**
     * 大小限制
     */
    private Integer sizeLimit;

    /**
     * 淘汰策略
     */
    private ICacheEvict<K,V> evict;

    /**
     * 设置大小限制
     * @param sizeLimit
     * @return
     */
    public Cache<K,V> sizeLimit(Integer sizeLimit){
        this.sizeLimit = sizeLimit;
        return this;
    }

    public Cache<K,V> map(Map<K,V> map){
        this.map = map;
        return this;
    }

    /**
     * 设置淘汰策略
     * @param evict
     * @return
     */
    public Cache<K,V> evict(ICacheEvict<K,V> evict){
        this.evict = evict;
        return this;
    }

    @Override
    public ICacheEvict<K, V> evict() {
        return evict;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        CacheEvictContext<K, V> context = new CacheEvictContext<>();
        context.key(key).size(sizeLimit).cache(this);
        evict.evict(context);
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        evict.removeKey((K) key);
        return map.remove(key);
    }

    /**
     * 返回当前缓存是否达到大小限制
     * @return
     */
    public boolean isSizeLimit(){
        return this.size() >= this.sizeLimit;
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public ICache<K,V> build(){
        Cache<K,V> cache = new Cache<>();
        cache.map(map);
        cache.evict(evict);
        cache.sizeLimit(sizeLimit);
        return CacheProxy.getProxy(cache);
    }

}

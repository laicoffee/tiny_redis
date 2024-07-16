package com.github.awstan.cache.core.core;

import com.github.awstan.cache.annotation.CacheInterceptor;
import com.github.awstan.cache.api.*;
import com.github.awstan.cache.core.expire.CacheExpire;
import com.github.awstan.cache.core.proxy.CacheProxy;
import com.github.awstan.cache.core.proxy.ICacheProxy;
import com.github.awstan.cache.core.support.CacheEvictContext;
import com.github.awstan.cache.core.support.persist.CachePersistAof;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
     * 过期策略
     */
    private ICacheExpire<K,V> expire;

    /**
     * 持久化策略
     */
    private ICachePersist<K,V> persist = new CachePersistAof("1.txt");

    /**
     * 初始化
     */
    public void init(){
        this.expire = new CacheExpire<>(this);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(()->{
            persist.persist(this);
        },persist.delay(),persist.period(),persist.timeUnit());
    }

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
    public ICache<K, V> expire(K key, Long timeInMills) {
        long expireTime = System.currentTimeMillis() + timeInMills;
        Cache<K, V> proxy = (Cache<K, V>) CacheProxy.getProxy(this);
        return proxy.expireAt(key,expireTime);
    }

    @Override
    public ICache<K, V> expireAt(K key, Long timeInMills) {
        this.expire.expire(key,timeInMills);
        return this;
    }

    @Override
    public ICacheExpire<K, V> expire() {
        return this.expire;
    }

    @Override
    public ICacheEvict<K, V> evict() {
        return evict;
    }

    @Override
    public ICachePersist<K, V> persist() {
        return persist;
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
    @CacheInterceptor(evict = true,aof = true)
    public V put(K key, V value) {
        CacheEvictContext<K, V> context = new CacheEvictContext<>();
        context.key(key).size(sizeLimit).cache(this);
        evict.evict(context);
        return map.put(key, value);
    }

    @Override
    @CacheInterceptor(evict = true,aof = true)
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
    @CacheInterceptor(evict = true,aof = true)
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public ICache<K,V> persist(ICachePersist<K,V> persist){
        this.persist = persist;
        return this;
    }

}

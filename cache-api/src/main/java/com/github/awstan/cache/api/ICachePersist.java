package com.github.awstan.cache.api;

import java.util.concurrent.TimeUnit;

/**
 * @Author pw7563
 * @Date 2024/7/16 9:57
 * usage 持久化缓存接口
 */
public interface ICachePersist<K,V> {

    void persist(ICache<K,V> cache);

    /**
     * 延迟时间，持久化的延时时间
     * @return
     */
    long delay();

    /**
     * 时间间隔
     * @return
     */
    long period();

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit();




}

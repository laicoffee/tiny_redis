package com.github.awstan.cache.api;

import java.util.Map;

/**
 * @Author pw7563
 * @Date 2024/7/8 9:49
 * usage
 */
public interface ICache<K, V> extends Map <K, V> {

    /**
     * 设置过期时间
     * @param key
     * @param timeInMills
     * @return
     */
    ICache<K, V> expire(K key, Long timeInMills);

    /**
     * 在指定时间过期
     * @param key
     * @param timeInMills
     * @return
     */
    ICache<K,V> expireAt(K key, Long timeInMills);

    /**
     * 获取缓存的过期处理类
     * @return
     */
    ICacheExpire<K,V> expire();

    /**
     * 淘汰策略
     * @return
     */
    ICacheEvict<K,V> evict();

    /**
     * 持久化策略
     * @return
     */
    ICachePersist<K,V> persist();

}

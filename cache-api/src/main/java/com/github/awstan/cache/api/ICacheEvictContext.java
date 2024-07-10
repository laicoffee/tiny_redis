package com.github.awstan.cache.api;

/**
 * @Author pw7563
 * @Date 2024/7/8 11:22
 * usage
 * 1. 新加的key
 * 2.map实现
 * 3.淘汰监听器
 */
public interface ICacheEvictContext<K, V> {

    /**
     * 新加的 key
     * @return key
     * @since 0.0.2
     */
    K key();

    /**
     * cache 实现
     * @return map
     * @since 0.0.2
     */
    ICache<K, V> cache();

    /**
     * 获取大小
     * @return 大小
     * @since 0.0.2
     */
    int size();

}

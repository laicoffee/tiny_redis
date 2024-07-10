package com.github.awstan.cache.api;

/**
 * @Author pw7563
 * @Date 2024/7/8 10:22
 * usage 缓存明确信息接口
 */
public interface ICacheEntry <K,V>{

    K key();

    V value();
}

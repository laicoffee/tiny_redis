package com.github.awstan.cache.api;

import java.util.Map;

/**
 * @Author pw7563
 * @Date 2024/7/8 9:49
 * usage
 */
public interface ICache<K, V> extends Map <K, V> {

    /**
     * 淘汰策略
     * @return
     */
    ICacheEvict<K,V> evict();

}

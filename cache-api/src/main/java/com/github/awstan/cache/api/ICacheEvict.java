package com.github.awstan.cache.api;

/**
 * @Author pw7563
 * @Date 2024/7/8 10:22
 * usage 缓存详细信息
 */
public interface ICacheEvict <K,V>{

    /**
     * 淘汰策略
     * @param context
     * @return
     */
    ICacheEntry<K,V> evict(final ICacheEvictContext<K,V> context);

    /**
     * 更新 key 信息
     * @param key key
     */
    void updateKey(final K key);

    /**
     * 删除 key 信息
     * @param key key
     */
    void removeKey(final K key);

}

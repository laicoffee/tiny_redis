package com.github.awstan.cache.api;

import java.util.Collection;

/**
 * @Author pw7563
 * @Date 2024/7/12 16:17
 * usage
 */
public interface ICacheExpire <K,V>{

    /**
     * 指定过期时间
     * @param key
     * @param expireAt
     */
    void expire(K key, long expireAt);

    /**
     * 惰性删除中需要处理的keys
     * @param keyList
     */
    void refreshExpire(Collection<K> keyList);

    /**
     * 待过期的key
     * 不存在，则返回null
     * @param key
     * @return
     */
    Long expireTime(K key);

}

package com.github.awstan.cache.core.evict;

import com.github.awstan.cache.api.ICacheEntry;
import com.github.awstan.cache.api.ICacheEvict;
import com.github.awstan.cache.api.ICacheEvictContext;

/**
 * @Author pw7563
 * @Date 2024/7/9 21:27
 * usage
 */
public abstract class AbstractCacheEvict <K,V> implements ICacheEvict<K,V> {
    @Override
    public ICacheEntry<K, V> evict(ICacheEvictContext<K, V> context) {
        return doEvict(context);
    }

    protected abstract ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context);

    @Override
    public void updateKey(K key) {

    }

    @Override
    public void removeKey(K key) {

    }
}

package com.github.awstan.cache.core.evict;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEntry;
import com.github.awstan.cache.api.ICacheEvict;
import com.github.awstan.cache.api.ICacheEvictContext;
import com.github.awstan.cache.core.core.model.CacheEntry;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @Author pw7563
 * @Date 2024/7/9 10:59
 * usage
 */
public class CacheEvictFifo<K,V> extends AbstractCacheEvict<K,V> {

    private final Deque<K> queue = new LinkedList<>();

    @Override
    protected ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context) {
        CacheEntry<K,V> result = null;

        final ICache<K,V> cache = context.cache();
        // 超过限制，执行移除
        if(queue.size() >= context.size()) {
            K evictKey = queue.remove();
            // 移除最开始的元素
            V evictValue = cache.remove(evictKey);
            result = new CacheEntry<>(evictKey, evictValue);

            System.out.println("本地淘汰的数据是:" + result.toString());
        }

        // 将新加的元素放入队尾
        final K key = context.key();
        queue.add(key);
        return result;
    }

    @Override
    public void updateKey(K key) {
        super.updateKey(key);
    }

    @Override
    public void removeKey(K key) {
        queue.remove();
        super.removeKey(key);
    }
}

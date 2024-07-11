package com.github.awstan.cache.core.evict;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheEvict;

/**
 * @Author pw7563
 * @Date 2024/7/11 9:30
 * usage
 */
public class CacheEvicts {

    public static <K,V> ICacheEvict<K,V> fifo(){
        return new CacheEvictFifo<>();
    }

}

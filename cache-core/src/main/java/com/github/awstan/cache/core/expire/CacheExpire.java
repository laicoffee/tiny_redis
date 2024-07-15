package com.github.awstan.cache.core.expire;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.api.ICacheExpire;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author pw7563
 * @Date 2024/7/12 16:20
 * usage
 */
public class CacheExpire <K,V>  implements ICacheExpire<K,V>{

    /**
     * 单次清空的数量限制
     */
    private long limit = 100;

    /**
     * 过期map
     */
    private Map<K,Long> expireMap = new HashMap<>();

    /**
     * 缓存实现
     */
    private ICache<K,V> cache;

    private ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();

    public CacheExpire(ICache<K,V> cache) {
        this.cache = cache;
        this.init();
    }

    /**
     * 初始化任务
     */
    private void init(){
        executors.scheduleWithFixedDelay(()->{
            if(expireMap == null || expireMap.isEmpty()){
                return;
            }
            int count = 0;
            for(Map.Entry<K,Long> entry : expireMap.entrySet()){
                if(count >= limit){
                    return;
                }
                expireKey(entry.getKey(),entry.getValue());
                count++;
            }
        },100,100, TimeUnit.MILLISECONDS);
    }

    /**
     * 过期处理key
     * @param key key
     * @param expireAt 过期时间
     */
    private void expireKey(final K key, final Long expireAt){
        if(expireAt == null){
            return;
        }

        long currentTime = System.currentTimeMillis();
        if(currentTime >= expireAt){
            expireMap.remove(key);
            V remove = cache.remove(key);
            // todo:执行淘汰策略监听器
        }

    }

    @Override
    public void expire(K key, long expireAt) {
        expireMap.put(key,expireAt);
    }

    @Override
    public void refreshExpire(Collection<K> keyList) {
        if(keyList == null || keyList.isEmpty()){
            return;
        }

        for(K key : keyList){
            Long expireAt = expireMap.get(key);
            if(expireAt == null){
                continue;
            }
            this.expireKey(key,expireAt);
        }
    }

    @Override
    public Long expireTime(K key) {
        return expireMap.get(key);
    }
}

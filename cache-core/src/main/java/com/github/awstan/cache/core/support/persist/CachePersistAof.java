package com.github.awstan.cache.core.support.persist;

import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.core.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author pw7563
 * @Date 2024/7/16 10:29
 * usage  缓存持久化-AOF
 */
public class CachePersistAof<K,V> extends CachePersistAdaptor<K,V> {

    /**
     * 缓存列表
     */
    private final List<String> bufferList = new ArrayList<>();

    /**
     * 数据持久化路径
     */
    private final String doPath;

    public CachePersistAof(String doPath) {
        this.doPath = doPath;
    }

    @Override
    public void persist(ICache<K, V> cache) {
        try{
            if(!FileUtil.exists(doPath)){
                FileUtil.createFile(doPath);
            }
            FileUtil.append(doPath, bufferList);
            bufferList.clear();
        } catch (IOException e) {
            System.out.println("持久化时发生异常");
            throw new RuntimeException(e);
        }
    }

    @Override
    public long delay() {
        return 1;
    }

    @Override
    public long period() {
        return 1;
    }

    @Override
    public TimeUnit timeUnit() {
        return super.timeUnit();
    }

    public void append(String json){
        if(json != null && !json.isEmpty()){
            bufferList.add(json);
        }
    }
}

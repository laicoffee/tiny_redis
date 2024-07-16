package com.github.awstan.cache.annotation;

import java.lang.annotation.*;

/**
 * @Author pw7563
 * @Date 2024/7/11 9:16
 * usage
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface CacheInterceptor {

    /**
     * 是否执行丢弃策略
     * 主要用于LRU、LFU等丢弃策略
     * @return
     */
    boolean evict() default false;

    /**
     * 是否执行AOF策略
     * @return
     */
    boolean aof() default false;

}

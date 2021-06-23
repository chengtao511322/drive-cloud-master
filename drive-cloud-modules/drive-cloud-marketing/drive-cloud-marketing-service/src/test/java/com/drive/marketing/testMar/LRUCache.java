/*
package com.drive.marketing.testMar;

import java.util.LinkedHashMap;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;
*/
/**
* 传递进来最多能缓存多少数据 
*
* @param cacheSize 缓存⼤⼩ 
*//*

    public LRUCache(int cacheSize) {
// true 表示让 linkedHashMap 按照访问顺序来进⾏排序，最近访问的放在头部，最 
super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
CACHE_SIZE = cacheSize; 
}
protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
// 当 map中的数据量⼤于指定的缓存个数的时候，就⾃动删除最⽼的数据。 
return size() > CACHE_SIZE; 
} 
} */

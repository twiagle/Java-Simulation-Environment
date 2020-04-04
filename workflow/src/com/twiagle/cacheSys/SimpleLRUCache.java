package com.twiagle.cacheSys;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author tb
 * @date 7/30/19-11:19 AM
 */
public class SimpleLRUCache<K, V> {

    private final int MAX_CACHE_SIZE;

    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    public LinkedHashMap<K, V> map;

    public SimpleLRUCache(int cacheSize) {

        MAX_CACHE_SIZE = cacheSize;

        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;

        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, true) {

            private static final long serialVersionUID = 1L;

            @Override

            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

                return size() > MAX_CACHE_SIZE;

            }

        };

    }

    public synchronized void put(K key, V value) {

        map.put(key, value);

    }

    public synchronized V get(K key) {

        return map.get(key);

    }

    public synchronized void remove(K key) {

        map.remove(key);

    }

    public synchronized Set<Map.Entry<K, V>> getAll() {

        return map.entrySet();

    }

    @Override

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<K, V> entry : map.entrySet()) {

            stringBuilder.append(String.format("%s: %s ", entry.getKey(), entry.getValue()));

        }

        return stringBuilder.toString();

    }

}
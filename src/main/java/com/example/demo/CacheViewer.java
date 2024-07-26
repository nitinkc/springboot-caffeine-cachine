package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@Slf4j
public class CacheViewer {
    private final CacheManager cacheManager;

    public CacheViewer(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void viewCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache instanceof CaffeineCache caffeineCache) {
            Map<Object, Object> cacheMap = caffeineCache.getNativeCache().asMap();
            cacheMap.forEach((key, value) ->
                    log.info("Key: " + key + ", Value: " + value)
            );
        } else {
            log.info("Cache '" + cacheName + "' is not a Caffeine cache.");
        }
    }

    @Scheduled(fixedDelay = 15000) // 15 seconds
    public void viewAllCache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();

        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);

            if (cache instanceof CaffeineCache) {
                CaffeineCache caffeineCache = (CaffeineCache) cache;
                Map<Object, Object> cacheMap = caffeineCache.getNativeCache().asMap();

                log.info("Cache name: " + cacheName);
                cacheMap.forEach((key, value) ->
                        log.info("Key: " + key + ", Value: " + value)
                );
            } else {
                log.info("Cache '" + cacheName + "' is not a Caffeine cache.");
            }
        }
        log.info("==========================================");
    }
}


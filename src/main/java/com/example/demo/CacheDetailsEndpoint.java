package com.example.demo;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/cache-details")
public class CacheDetailsEndpoint {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public WebEndpointResponse<Map<String, Object>> getCacheDetails() {
        Map<String, Object> cacheDetails = cacheManager.getCacheNames().stream()
                .collect(Collectors.toMap(
                        cacheName -> cacheName,
                        cacheName -> {
                            Cache cache = cacheManager.getCache(cacheName);
                            if (cache instanceof CaffeineCache) {
                                return ((CaffeineCache) cache).getNativeCache().asMap(); // Returns a map of key-value pairs
                            }
                            return null;
                        }
                ));
        return new WebEndpointResponse<>(cacheDetails);
    }
}

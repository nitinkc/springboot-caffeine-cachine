package com.example.demo.service;

import com.example.demo.CacheViewer;
import com.example.demo.UserData;
import com.example.demo.UserDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames = "myServiceCache")
@RequiredArgsConstructor
public class MyService {
    private final CacheViewer cacheViewer;
    private final UserDataRepository userRepository;

    @Cacheable(key = "#key")//Dynamic Key Using Method Parameter
    public String getCachedData(String key) {
        // Simulate some expensive computation
        return "Cached value for the key :: " + key;
    }

    @Cacheable(key = "'daysRemaining'")//Literal String as Key:
    public long getDaysRemainingUntilExpiration() {
       int daysRemaining = 10;
        // Log cache contents
        cacheViewer.viewCache("dateExpiry");
        return daysRemaining;
    }

    @Cacheable(cacheNames = "licenseExpiration", key = "#userId + ':' + #username")
    public UserData getUserData(Long userId, String username) {
        log.info("Fetching user data from database for userId=" + userId + ", username=" + username);
        return userRepository.findByUserIdAndUsername(userId, username);
    }

}

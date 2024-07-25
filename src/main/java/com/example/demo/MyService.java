package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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
        return "Cached value for " + key;
    }

    @Cacheable(key = "'daysRemaining'")//Literal String as Key:
    public long getDaysRemainingUntilExpiration() {
       int daysRemaining = 10;
        // Log cache contents
        cacheViewer.viewCache("dateExpiry");
        return daysRemaining;
    }

    @Cacheable(key = "#userId + ':' + #username")
    public UserData getUserData(Long userId, String username) {
        log.info("Fetching user data from database for userId=" + userId + ", username=" + username);
        return userRepository.findByUserIdAndUsername(userId, username);
    }

}

package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    final String url = "https://api.openbrewerydb.org/v1/breweries";

    @Cacheable(cacheNames = "dataCache")
    public List<String> fetchDataFromUrl(String key) {
        //Data from the network.
        //String forObject = restTemplate.getForObject(url, String.class);

        return List.of();
    }
}

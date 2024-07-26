package com.example.demo;

import com.example.demo.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(value = 1) //Sequence of runner
@ConditionalOnExpression("${runner1:true}") //Can be controlled from App yml
public class MyBootRunner implements CommandLineRunner {
    //Make fine to be used with Required Args Constructor
    private final MyService myService;
    private final CacheViewer cacheViewer;

    public MyBootRunner(MyService myService, CacheViewer cacheViewer) {
        this.myService = myService;
        this.cacheViewer = cacheViewer;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Runner 1");
        //Do processing
        log.info(myService.getCachedData("Nitin"));
        cacheViewer.viewCache("mycache");
    }
}
package com.example.demo;

import com.example.demo.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping(path = "/")
    public long retrieveAllUsers(){
        return myService.getDaysRemainingUntilExpiration();
    }

    @GetMapping("/userdata")
    public UserData getUserData(@RequestParam Long userId, @RequestParam String username) {
        return myService.getUserData(userId, username);
    }
}

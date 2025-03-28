package com.unstage.api;

import com.unstage.core.AEntity;
import com.unstage.core.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private AService aService;

    @GetMapping("/test")
    public String test() {
        AEntity aEntity = aService.create();
        aService.fetch();
        return aEntity.getId().toString();
    }

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }
}

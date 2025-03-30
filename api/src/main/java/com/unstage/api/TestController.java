package com.unstage.api;

import com.unstage.core.AEntity;
import com.unstage.core.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${unstage-version.version}")
    private String version;

    @Autowired
    private AService aService;

    @GetMapping("/")
    public String version() {
        return String.format("Project Version: %s", version);
    }

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

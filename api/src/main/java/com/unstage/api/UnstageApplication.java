package com.unstage.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.unstage.api",
                "com.unstage.core"
        }
)
public class UnstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnstageApplication.class, args);
    }

}

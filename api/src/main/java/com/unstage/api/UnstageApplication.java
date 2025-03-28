package com.unstage.api;

import com.unstage.api.config.APIScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({APIScan.class})
public class UnstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnstageApplication.class, args);
    }

}

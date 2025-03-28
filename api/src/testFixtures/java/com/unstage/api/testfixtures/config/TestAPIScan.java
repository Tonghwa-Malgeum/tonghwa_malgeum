package com.unstage.api.testfixtures.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.unstage.api",
        "com.unstage.core"
})
public class TestAPIScan {
}

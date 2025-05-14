package com.unstage.core.config;

import com.unstage.core.TestContainer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = IntegrationTest.CoreTestConfiguration.class
)
@ActiveProfiles("test")
public abstract class IntegrationTest extends TestContainer {

    @EnableAutoConfiguration
    @ComponentScan(basePackages = {"com.unstage.core"})
    static class CoreTestConfiguration {
    }
}

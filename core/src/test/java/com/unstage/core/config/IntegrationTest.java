package com.unstage.core.config;

import com.unstage.core.testfixtures.DatabaseCleanup;
import com.unstage.core.testfixtures.TestContainer;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = IntegrationTest.CoreTestConfiguration.class
)
@ActiveProfiles("test")
public abstract class IntegrationTest extends TestContainer {
    @Autowired
    private DatabaseCleanup databaseCleanup;

    @AfterEach
    public void cleanup() {
        databaseCleanup.execute();
    }

    @EnableAutoConfiguration
    @ComponentScan(basePackages = {"com.unstage.core"})
    static class CoreTestConfiguration {
    }
}

package com.unstage.api.testfixtures.config;

import com.unstage.core.config.db.JPAScan;
import com.unstage.core.testfixtures.config.TestContainer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestAPIScan.class, JPAScan.class})
public abstract class APITest extends TestContainer {
}

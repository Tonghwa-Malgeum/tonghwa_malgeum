package com.unstage.core.config.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.unstage.core")
@EnableJpaRepositories(basePackages = "com.unstage.core")
public class JPAScan {
}

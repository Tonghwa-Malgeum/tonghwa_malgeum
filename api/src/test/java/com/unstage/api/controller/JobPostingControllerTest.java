package com.unstage.api.controller;

import com.unstage.core.jobposting.entity.JobPosting;
import com.unstage.core.jobposting.repository.JobPostingRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest(
        classes = {
                JobPostingControllerTest.TestConfig.class,
                JobPostingControllerTest.TestJPAConfig.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JobPostingControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
    }

    @Test
    void repositoryTest() {
        List<JobPosting> all =
                jobPostingRepository.findAll();
        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void restAssuredTest() {
        RestAssured
                .given()
                .when()
                .get("/test")
                .then()
                .statusCode(200);
    }

    @Test
    void jobPostingControllerTest() {
        RestAssured
                .given()
                .when()
                .get("/api/v1/job-postings?page=0&size=10")
                .then()
                .statusCode(200);
    }

    @Configuration
    @ComponentScan(
            basePackages = {
                    "com.unstage.api",
                    "com.unstage.core"
            })
    @EnableAutoConfiguration
    static class TestConfig {

    }

    @Configuration
    @EntityScan(basePackages = "com.unstage.core")
    @EnableJpaRepositories(basePackages = "com.unstage.core")
    static class TestJPAConfig {

    }
}

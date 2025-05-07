package com.unstage.api.controller;

import com.unstage.core.instructor.dto.CreatePortfolioRequest;
import com.unstage.core.instructor.dto.InstructorRequest;
import com.unstage.core.instructor.entity.Portfolio;
import com.unstage.core.instructor.repository.InstructorRepository;
import com.unstage.core.instructor.repository.PortfolioRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                PortfolioControllerTest.TestConfig.class,
                PortfolioControllerTest.TestJPAConfig.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PortfolioControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    private Integer instructorId;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        // Clean up the repositories before each test
        portfolioRepository.deleteAll();
        instructorRepository.deleteAll();

        // Create an instructor for testing
        InstructorRequest instructorRequest = new InstructorRequest("John Doe", "john@example.com", "Bio");

        instructorId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(instructorRequest)
                .when()
                .post("/api/v1/instructors")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
    }

    @Test
    void repositoryTest() {
        List<Portfolio> all = portfolioRepository.findAll();
        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void createAndGetPortfolioTest() {
        // Create a portfolio
        CreatePortfolioRequest request = new CreatePortfolioRequest("My Portfolio", "<h1>Hello World</h1><img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=\">");

        // Send POST request to create portfolio
        Integer portfolioId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/v1/instructors/" + instructorId + "/portfolios")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Get the created portfolio
        RestAssured
                .given()
                .when()
                .get("/api/v1/portfolios/" + portfolioId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void getPortfoliosByInstructorTest() {
        // Create a portfolio
        CreatePortfolioRequest request = new CreatePortfolioRequest("My Portfolio", "<h1>Hello World</h1>");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/v1/instructors/" + instructorId + "/portfolios")
                .then()
                .statusCode(201);

        // Get portfolios by instructor
        RestAssured
                .given()
                .when()
                .get("/api/v1/instructors/" + instructorId + "/portfolios")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void updatePortfolioTest() {
        // Create a portfolio first
        CreatePortfolioRequest createRequest = new CreatePortfolioRequest("My Portfolio", "<h1>Hello World</h1>");

        Integer portfolioId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createRequest)
                .when()
                .post("/api/v1/instructors/" + instructorId + "/portfolios")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Update the portfolio
        CreatePortfolioRequest updateRequest = new CreatePortfolioRequest("Updated Portfolio", "<h1>Updated Content</h1>");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateRequest)
                .when()
                .put("/api/v1/portfolios/" + portfolioId)
                .then()
                .statusCode(200);
    }

    @Test
    void deletePortfolioTest() {
        // Create a portfolio first
        CreatePortfolioRequest createRequest = new CreatePortfolioRequest("My Portfolio", "<h1>Hello World</h1>");

        Integer portfolioId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createRequest)
                .when()
                .post("/api/v1/instructors/" + instructorId + "/portfolios")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Delete the portfolio
        RestAssured
                .given()
                .when()
                .delete("/api/v1/portfolios/" + portfolioId)
                .then()
                .statusCode(204);
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
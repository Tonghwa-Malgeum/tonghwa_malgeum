package com.unstage.api.controller;

import com.unstage.core.instructor.dto.InstructorRequest;
import com.unstage.core.instructor.entity.Instructor;
import com.unstage.core.instructor.repository.InstructorRepository;
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
                InstructorControllerTest.TestConfig.class,
                InstructorControllerTest.TestJPAConfig.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class InstructorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private InstructorRepository instructorRepository;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        // Clean up the repository before each test
        instructorRepository.deleteAll();
    }

    @Test
    void repositoryTest() {
        List<Instructor> all = instructorRepository.findAll();
        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void createAndGetInstructorTest() {
        // Create an instructor
        InstructorRequest request = new InstructorRequest("John Doe", "john@example.com", "Bio");

        // Send POST request to create instructor
        Integer instructorId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/v1/instructors")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Get the created instructor
        RestAssured
                .given()
                .when()
                .get("/api/v1/instructors/" + instructorId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void updateInstructorTest() {
        // Create an instructor first
        InstructorRequest createRequest = new InstructorRequest("John Doe", "john@example.com", "Bio");

        Integer instructorId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createRequest)
                .when()
                .post("/api/v1/instructors")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Update the instructor
        InstructorRequest updateRequest = new InstructorRequest("Updated Name", "john@example.com", "Updated Bio");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateRequest)
                .when()
                .put("/api/v1/instructors/" + instructorId)
                .then()
                .statusCode(200);
    }

    @Test
    void deleteInstructorTest() {
        // Create an instructor first
        InstructorRequest createRequest = new InstructorRequest("John Doe", "john@example.com", "Bio");

        Integer instructorId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createRequest)
                .when()
                .post("/api/v1/instructors")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Delete the instructor
        RestAssured
                .given()
                .when()
                .delete("/api/v1/instructors/" + instructorId)
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

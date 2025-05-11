package com.unstage.api.controller;

import com.unstage.api.RestAssuredTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PortfolioControllerTest extends RestAssuredTest {

    @Test
    @Sql(scripts = {"classpath:sql/instructor-portfolio-test-data.sql"})
    void getPortfolio() {
        // when
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .log().all()
                .get("/api/v1/portfolios/1")
                .then()
                .log().all()
                .statusCode(200);

        // then
        Map<String, Object> responseBody = response.extract().body().as(new TypeRef<Map<String, Object>>() {});
        
        // 포트폴리오 정보 검증
        assertThat(responseBody.get("portfolioId")).isEqualTo(1);
        assertThat(responseBody.get("title")).isEqualTo("Portfolio 1");
        assertThat(responseBody.get("content")).isEqualTo("Content for portfolio 1");
        assertThat(responseBody.get("instructorId")).isEqualTo(1);
        assertThat(responseBody.get("instructorName")).isEqualTo("Test Instructor 1");
    }
}
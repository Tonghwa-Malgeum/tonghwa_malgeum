package com.unstage.api.controller;

import com.unstage.api.RestAssuredTest;
import com.unstage.core.user.dto.CreatePortfolioRequest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class InstructorControllerTest extends RestAssuredTest {

    @Test
    @Sql(scripts = {"classpath:sql/instructor-portfolio-test-data.sql"})
    void getPortfoliosByInstructor() {
        // when
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .log().all()
                .get("/api/v1/instructors/1/portfolios")
                .then()
                .log().all()
                .statusCode(200);

        // then
        Map<String, Object> responseBody = response.extract().body().as(new TypeRef<Map<String, Object>>() {});
        
        // 포트폴리오 목록 검증
        List<Map<String, Object>> portfolioInfos = (List<Map<String, Object>>) responseBody.get("portfolioInfos");
        assertThat(portfolioInfos).hasSize(2);
        
        // 첫 번째 포트폴리오 검증
        Map<String, Object> firstPortfolio = portfolioInfos.get(0);
        assertThat(firstPortfolio.get("id")).isEqualTo(1);
        assertThat(firstPortfolio.get("title")).isEqualTo("Portfolio 1");
        
        // 두 번째 포트폴리오 검증
        Map<String, Object> secondPortfolio = portfolioInfos.get(1);
        assertThat(secondPortfolio.get("id")).isEqualTo(2);
        assertThat(secondPortfolio.get("title")).isEqualTo("Portfolio 2");
    }

    @Test
    @Sql(scripts = {"classpath:sql/instructor-portfolio-test-data.sql"})
    void createPortfolio() {
        // given
        CreatePortfolioRequest request = new CreatePortfolioRequest(
                "New Portfolio",
                "Content for new portfolio"
        );

        // when
        ValidatableResponse response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .log().all()
                .post("/api/v1/instructors/1/portfolios")
                .then()
                .log().all()
                .statusCode(201);

        // then
        String location = response.extract().header("Location");
        assertThat(location).startsWith("/api/v1/portfolios/");
    }
}
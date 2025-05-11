package com.unstage.api.controller;

import com.unstage.api.RestAssuredTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JobPostingControllerTest extends RestAssuredTest {

    @Test
    @Sql(scripts = {"classpath:sql/job-posting-test-data.sql"})
    void getJobPostings() {
        // when
        ValidatableResponse response = RestAssured
                .given()
                .when()
                .log().all()
                .get("/api/v1/job-postings?page=0&size=2")
                .then()
                .log().all()
                .statusCode(200);

        // then
        Map<String, Object> responseBody = response.extract().body().as(new TypeRef<Map<String, Object>>() {});

        // 페이지 정보 검증
        assertThat(responseBody.get("totalPages")).isEqualTo(2);
        assertThat(responseBody.get("totalElements")).isEqualTo(3);
        assertThat(responseBody.get("number")).isEqualTo(0);
        assertThat(responseBody.get("size")).isEqualTo(2);

        // 컨텐츠 검증
        List<Map<String, Object>> content = (List<Map<String, Object>>) responseBody.get("content");
        assertThat(content).hasSize(2);

        // 첫 번째 항목 검증
        Map<String, Object> firstItem = content.get(0);
        assertThat(firstItem.get("id")).isEqualTo(1);
        assertThat(firstItem.get("title")).isEqualTo("첫 번째 구인공고");
        assertThat(firstItem.get("welfareCenterName")).isEqualTo("복지센터1");
        assertThat(firstItem.get("region")).isEqualTo("서울");
    }

}

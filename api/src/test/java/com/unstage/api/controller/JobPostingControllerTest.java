//package com.unstage.api.controller;
//
//import com.unstage.api.RestAssuredTest;
//import com.unstage.core.jobposting.dto.GetJobPostingsResponse;
//import com.unstage.core.paging.PageResponse;
//import io.restassured.RestAssured;
//import io.restassured.common.mapper.TypeRef;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.jdbc.Sql;
//
//import static com.unstage.api.auth.config.TestSecurityConfig.TestSecurityFilter.TEST_AUTH_HEADER;
//import static com.unstage.api.auth.config.TestSecurityConfig.TestSecurityFilter.TEST_AUTH_VALUE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//class JobPostingControllerTest extends RestAssuredTest {
//
//    @Test
//    @Sql(scripts = {"classpath:sql/job-posting-controller.sql"})
//    void 구인공고_목록조회_API() {
//        // when
//        ExtractableResponse<Response> response = RestAssured
//                .given()
//                .header(TEST_AUTH_HEADER, TEST_AUTH_VALUE)
//                .when()
//                .log().all()
//                .get("/api/v1/job-postings?page=0&size=2")
//                .then()
//                .log().all()
//                .statusCode(HttpStatus.OK.value())
//                .extract();
//
//        // then
//        PageResponse<GetJobPostingsResponse> pageResponse = response.as(new TypeRef<>() {});
//        assertThat(pageResponse.content()).hasSize(2);
//
//        GetJobPostingsResponse getJobPostingsResponse = pageResponse.content().get(0);
//        assertThat(getJobPostingsResponse.getId()).isEqualTo(1);
//        assertThat(getJobPostingsResponse.getTitle()).isEqualTo("첫 번째 구인공고");
//        assertThat(getJobPostingsResponse.getWelfareCenterName()).isEqualTo("복지센터1");
//        assertThat(getJobPostingsResponse.getRegion()).isEqualTo("서울");
//    }
//}

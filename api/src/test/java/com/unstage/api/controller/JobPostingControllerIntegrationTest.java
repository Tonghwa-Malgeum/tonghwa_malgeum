//package com.unstage.api.jobposting;
//
//import com.unstage.core.jobposting.entity.JobPosting;
//import com.unstage.core.jobposting.repository.JobPostingRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@ContextConfiguration(classes = JobPostingControllerIntegrationTest.TestConfig2.class)
//public class JobPostingControllerIntegrationTest {
//
//    @Autowired
//    private JobPostingRepository jobPostingRepository;
//
//    @Test
//    void integrationTest() {
//        List<JobPosting> all =
//                jobPostingRepository.findAll();
//        Assertions.assertThat(all).isEmpty();
//    }
//
//    @Configuration
//    @ComponentScan(
//            basePackages = {
//                    "com.unstage.api",
//            })
//    @EnableAutoConfiguration
//    static class TestConfig2 {
//
//    }
//
//    @Configuration
//    @EntityScan(basePackages = "com.unstage.core")
//    @EnableJpaRepositories(basePackages = "com.unstage.core")
//    static class TestJPAConfig2 {
//
//    }
//}

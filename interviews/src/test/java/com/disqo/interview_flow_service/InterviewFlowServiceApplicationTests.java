package com.disqo.interview_flow_service;

import edu.aua.interviews.controller.InterviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InterviewFlowServiceApplicationTests {
    @Autowired(required = false)
    InterviewController interviewController;
    @Autowired(required = false)
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
                assertThat(interviewController).isNotNull();
    }

}

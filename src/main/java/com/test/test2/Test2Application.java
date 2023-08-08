package com.test.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Test2Application {

    public static void main(String[] args) {

        SpringApplication.run(Test2Application.class, args);
        restTemplate();
    }

    @Bean
    public static RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

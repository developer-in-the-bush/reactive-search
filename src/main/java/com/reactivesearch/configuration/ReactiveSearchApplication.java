package com.reactivesearch.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.reactivesearch")
@Import(SpringContextConfiguration.class)
public class ReactiveSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveSearchApplication.class, args);
    }
}

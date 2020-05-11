package com.app.kantor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KantorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KantorApplication.class, args);
    }
}

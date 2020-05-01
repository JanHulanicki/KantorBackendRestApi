package com.app.kantor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@ComponentScan(basePackages ={"com.app.kantor"})
//@Configuration
//@ComponentScan("com.app.kantor")
public class KantorApplication {
    public static void main(String[] args) {
        SpringApplication.run(KantorApplication.class, args);
    }
}

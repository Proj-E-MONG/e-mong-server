package com.yours.emong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.yours.emong")
@EnableJpaAuditing
public class EMongApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMongApplication.class, args);
    }

}

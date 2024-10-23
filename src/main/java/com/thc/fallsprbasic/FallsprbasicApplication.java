package com.thc.fallsprbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FallsprbasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(FallsprbasicApplication.class, args);
    }

}

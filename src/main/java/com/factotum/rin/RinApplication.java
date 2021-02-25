package com.factotum.rin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RinApplication.class, args);
    }

}


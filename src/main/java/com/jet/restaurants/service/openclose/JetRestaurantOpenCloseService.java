package com.jet.restaurants.service.openclose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class JetRestaurantOpenCloseService {

    public static void main(String[] args) {
        SpringApplication.run(JetRestaurantOpenCloseService.class, args);
    }

}

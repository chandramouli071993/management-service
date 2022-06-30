package com.jet.restaurants.service.openclose.service;

import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import com.jet.restaurants.service.openclose.kafka.event.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantStatusOutboundService {

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.restaurant.change-status:change-status}")
    private String topicName;

    public RestaurantStatusOutboundService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Long id, Status status) {
        log.info("Producing the event with id : {} and status : {} ", id, status);
        kafkaTemplate.send(topicName, RestaurantEvent.builder().id(id).status(status.getStatus()).build());
    }
}

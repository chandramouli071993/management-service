package com.jet.restaurants.service.openclose.intergration.kafka;

import com.jet.restaurants.service.openclose.JetRestaurantOpenCloseService;
import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import com.jet.restaurants.service.openclose.kafka.event.Status;
import com.jet.restaurants.service.openclose.service.RestaurantStatusOutboundService;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JetRestaurantOpenCloseService.class)
@DirtiesContext
class TestKafkaConsumer {

    @ClassRule
    public static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    @Autowired
    private RestaurantStatusOutboundService producer;

    @Value("${kafka.topic.restaurant.change-status:restaurants-status}")
    private String topic;

    @Test
    public void givenTheIdAndStatusOfRestaurant() {
        String data = "closed";
        RestaurantEvent restaurantEvent = RestaurantEvent.builder().id(111L).status("closed").build();
        producer.sendEvent(111L, Status.CLOSED);
        Assertions.assertEquals(111L, restaurantEvent.getId());
    }
}

package com.jet.restaurants.service.openclose.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import org.apache.kafka.common.serialization.Deserializer;

public class RestaurantEventDeserializer implements Deserializer<RestaurantEvent> {

    @Override
    public RestaurantEvent deserialize(String s, byte[] bytes) {
        RestaurantEvent restaurant = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            restaurant = mapper.readValue(bytes, RestaurantEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }
}

package com.jet.restaurants.service.openclose.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import org.apache.kafka.common.serialization.Serializer;

public class RestaurantEventSerializer implements Serializer<RestaurantEvent> {

    @Override
    public byte[] serialize(String s, RestaurantEvent restaurant) {
        byte[] retVal = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            retVal = mapper.writeValueAsString(restaurant).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}

package com.jet.restaurants.service.openclose.kafka;

import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class RestaurantEventSerde implements Serde<RestaurantEvent> {

    @Override
    public Serializer<RestaurantEvent> serializer() {
        return new RestaurantEventSerializer();
    }

    @Override
    public Deserializer<RestaurantEvent> deserializer() {
        return new RestaurantEventDeserializer();
    }

}

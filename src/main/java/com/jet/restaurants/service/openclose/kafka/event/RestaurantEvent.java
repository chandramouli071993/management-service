package com.jet.restaurants.service.openclose.kafka.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantEvent {

    private Long id;
    private String status;
}

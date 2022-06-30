package com.jet.restaurants.service.openclose.requests;

import com.jet.restaurants.service.openclose.kafka.event.Status;
import lombok.Data;

@Data
public class UpdateRestaurantStatusRequest {

    public Status status;
}

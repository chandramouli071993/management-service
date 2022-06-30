package com.jet.restaurants.service.openclose.controller;

import com.jet.restaurants.service.openclose.requests.UpdateRestaurantStatusRequest;
import com.jet.restaurants.service.openclose.service.RestaurantStatusOutboundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/status")
@Tag(name = "Status-Controller", description = "This service helps the user to set the status of the restaurant based on the input request!")
@AllArgsConstructor
public class StatusController {

    private final RestaurantStatusOutboundService restaurantStatusOutboundService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get the status of the restaurant based on the restaurant ID", operationId = "updateRestaurantStatus")
    public void updateRestaurantStatus(@PathVariable(value = "restaurantId") Long restaurantId,
                                       @RequestBody UpdateRestaurantStatusRequest request) {
        restaurantStatusOutboundService.sendEvent(restaurantId, request.getStatus());
    }

}

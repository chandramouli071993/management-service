package com.jet.restaurants.service.openclose.intergration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.jet.restaurants.service.openclose.JetRestaurantOpenCloseService;
import com.jet.restaurants.service.openclose.kafka.event.RestaurantEvent;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

@SpringBootTest(classes = {JetRestaurantOpenCloseService.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StatusControllerTest {

    @LocalServerPort
    private int randomServerPort;

    private WireMockServer wireMockServer;

    private RequestSpecification request;
    private Response response;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        startWireMockServer();
    }

    @AfterEach
    void tearDown() {
        stopWireMockServer();
    }

    private void stopWireMockServer() {
        wireMockServer.stop();
    }

    private void startWireMockServer() {
        wireMockServer = new WireMockServer(
                wireMockConfig().port(8888));
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @Test
    void shouldCallToProduceEvent() throws JsonProcessingException {
        RestaurantEvent restaurantEvent = RestaurantEvent.builder().id(1L).status("closed").build();
        String requestBody = objectMapper.writeValueAsString(restaurantEvent);
        request = given().contentType(ContentType.JSON);
        String url = "http://localhost:" + randomServerPort + "/restaurants/" + 1 + "/status";
        response = request.body(requestBody).when().put(url);
        response.then().statusCode(200);
    }
}

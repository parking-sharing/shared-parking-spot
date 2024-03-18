package com.parkingsharing.parking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
public class WelcomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/welcome")
    public String welcome(){
        LOGGER.info("Endpoint /welcome accessed.");
        return "Welcome to Spring boot!";
    }
}

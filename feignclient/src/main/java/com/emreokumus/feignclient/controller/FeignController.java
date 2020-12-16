package com.emreokumus.feignclient.controller;

import com.emreokumus.feignclient.config.IGreetingClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private IGreetingClient greetingClient;

    @HystrixCommand(fallbackMethod= "defaultResponse")
    @RequestMapping("/get-greeting")
    public ResponseEntity<String> getGreeting() {
        return new ResponseEntity<String>(greetingClient.greeting("Emre Okumus"), HttpStatus.OK);
    }

    //Fallback metod tanımladığımız zaman , fallback metodun ismi @HystrixCommand anotasyonu ile aynı olmalıdır.
    public ResponseEntity<String> defaultResponse() {
        System.out.println("You are seeing this fallback response because the underlying microservice is down.");
        String err = "Fallback error as the microservice is down.";
        return new ResponseEntity<String>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
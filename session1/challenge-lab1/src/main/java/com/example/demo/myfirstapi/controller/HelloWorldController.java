package com.example.demo.myfirstapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

        @Value("${app.greeting.message}") // Inject value from application.properties
        private String customGreeting;

        @GetMapping("/hello")
        public String sayHello() {
            return customGreeting; // Now returns the custom message
        }

    @GetMapping("/greet/{name}")
    public String greetUser(@PathVariable String name) {
        return "Greetings, " + name + "!";
    }
}

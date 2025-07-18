package com.example.demo.myfirstapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class HelloWorldController {

        @Value("${app.greeting.message}")
        private String customGreeting;

        @GetMapping("/hello")
        public String sayHello() {
            return customGreeting;
        }

    @GetMapping("/greet/{name}")
    public String greetUser(@PathVariable String name) {
        return "Greetings, " + name + "!";
    }

    @GetMapping("/greeting")
    public String greetWithParam(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/calculate/sum")
    public String sumNumbers( @RequestParam("num1") int number1, @RequestParam("num2") int number2) {
            int sum = number1 + number2;
            return "The sum of " + number1 + " and " + number2 + " is " + sum;
        }

    @GetMapping("/info")
    public Map<String, String> getAppInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("appName", "MyFirstSpringBootAPI");
        info.put("version", "1.0.0");
        info.put("description", "This is a simple Spring Boot API.");
        info.put("status", "Running");
        return info;
    }

    @GetMapping("/features")
    public List<String> getFeatures() {
        return Arrays.asList("REST API", "Spring Boot", "Easy Setup", "Fast Development");
    }
}

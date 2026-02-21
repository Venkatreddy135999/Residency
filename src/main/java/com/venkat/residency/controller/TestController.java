package com.venkat.residency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "✅ TEST SUCCESSFUL - Application is working!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "🚀 Application Status: HEALTHY - Tomcat is running on port 8080";
    }
}
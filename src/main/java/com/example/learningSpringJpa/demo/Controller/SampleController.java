package com.example.learningSpringJpa.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learningSpringJpa")
public class SampleController {
    @GetMapping
    public String getResponse() {
        return "Hi, i just started learning spring jpa";
    }
}

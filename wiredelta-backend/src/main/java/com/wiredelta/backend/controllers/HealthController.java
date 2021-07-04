package com.wiredelta.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HealthController {

    @GetMapping(value = "health")
    public String health() {
        return "OK_200";
    }

}

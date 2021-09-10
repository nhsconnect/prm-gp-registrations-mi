package com.prmgpregistrationsmi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @GetMapping("/event")
    public String helloWorld() {
        return "Hello world";
    }
}


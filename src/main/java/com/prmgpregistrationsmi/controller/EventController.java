package com.prmgpregistrationsmi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @PostMapping("/event")
    @ResponseStatus(HttpStatus.OK)
    public void transferEvent() {
        System.out.println("Hello world");
    }
}


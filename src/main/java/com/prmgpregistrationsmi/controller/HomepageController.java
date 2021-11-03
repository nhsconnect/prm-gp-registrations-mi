package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomepageController {
    @GetMapping(
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Event homepage() {
        Event requestBody = Event.builder().build();
        log.info("Reached root GET endpoint");
        return requestBody;
    }
}

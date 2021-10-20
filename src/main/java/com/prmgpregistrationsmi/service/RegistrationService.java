package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class RegistrationService {
    private EventS3Client eventS3Client;

    @Autowired
    public RegistrationService(EventS3Client eventS3Client) {
        this.eventS3Client = eventS3Client;
    }

    public void saveEvent(Event event, EventType eventType) {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType);
        eventS3Client.uploadObject(eventDAO);
    }

}

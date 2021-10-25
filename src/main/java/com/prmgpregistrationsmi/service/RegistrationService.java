package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private S3FileUploader eventS3Client;

    public void saveEvent(Event event, EventType eventType) {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType);
        eventS3Client.uploadObject(eventDAO);
    }
}

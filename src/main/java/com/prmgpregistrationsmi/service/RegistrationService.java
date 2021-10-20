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
    private S3Client s3Client;

    @Autowired
    public RegistrationService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void saveEvent(Event event, EventType eventType) {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType);
        s3Client.uploadObject(eventDAO);
    }

}

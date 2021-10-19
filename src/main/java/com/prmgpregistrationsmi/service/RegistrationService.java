package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RegistrationService {
    private S3Client s3Client;

    public void saveEvent(Event event) {
        EventDAO eventDAO = EventDAO.fromEvent(event);
        s3Client.uploadObject(eventDAO);
    }

}

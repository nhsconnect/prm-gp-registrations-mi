package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final S3FileUploader eventS3Client;

    public EventDAO saveEvent(Event event, EventType eventType) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType);
        eventS3Client.uploadObject(eventDAO);
        return eventDAO;
    }
}

package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@AllArgsConstructor
@Service
public class RegistrationService {
    private static final String OUTPUT_EXTENSION = ".json";
    private static final String OUTPUT_VERSION = "v1";
    private final S3FileUploader eventS3Client;

    public EventDAO saveEvent(Event event, EventType eventType) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType);
        String s3Key = getS3Key(event);
        eventS3Client.uploadJsonObject(eventDAO, s3Key);
        return eventDAO;
    }

    private String getS3DatePrefix(Long eventGeneratedTimestamp) {
        Date eventGeneratedDate = new Date(eventGeneratedTimestamp*1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(eventGeneratedDate);
        int eventGeneratedMonth = calendar.get(Calendar.MONTH) + 1;
        int eventGeneratedYear = calendar.get(Calendar.YEAR);
        int eventGeneratedDay = calendar.get(Calendar.DAY_OF_MONTH);
        int eventGeneratedHour = calendar.get(Calendar.HOUR_OF_DAY);
        return String.format("%d/%d/%d/%d", eventGeneratedYear, eventGeneratedMonth, eventGeneratedDay, eventGeneratedHour);
    }

    private String getS3Key(Event event) {
        Long eventGeneratedTimestamp = event.getEventGeneratedTimestamp();
        String s3DatePrefix = getS3DatePrefix(eventGeneratedTimestamp);

        String eventId = event.getEventId();
        String fileName = eventId + OUTPUT_EXTENSION;

        return String.format("/%s/%s/%s", OUTPUT_VERSION, s3DatePrefix, fileName);
    }
}

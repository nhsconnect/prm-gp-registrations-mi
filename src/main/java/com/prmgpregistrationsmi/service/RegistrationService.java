package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@AllArgsConstructor
@Service
public class RegistrationService {
    private static final String OUTPUT_EXTENSION = ".json";
    private static final String OUTPUT_VERSION = "v1";
    private final S3FileUploader eventS3Client;

    public EventDAO saveEvent(Event event, EventType eventType, TransferProtocol transferProtocol) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType, transferProtocol);
        String s3Key = getS3Key(event);
        eventS3Client.uploadJsonObject(eventDAO, s3Key);
        return eventDAO;
    }

    private String getS3DatePrefix(LocalDateTime eventGeneratedDateTime) {
        Date eventGeneratedDate = Date.from(eventGeneratedDateTime.toInstant(ZoneOffset.UTC));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(eventGeneratedDate);
        int eventGeneratedMonth = calendar.get(Calendar.MONTH) + 1;
        int eventGeneratedYear = calendar.get(Calendar.YEAR);
        int eventGeneratedDay = calendar.get(Calendar.DAY_OF_MONTH);
        int eventGeneratedHour = calendar.get(Calendar.HOUR_OF_DAY);

        return String.format("%02d/%02d/%02d/%02d", eventGeneratedYear, eventGeneratedMonth, eventGeneratedDay, eventGeneratedHour);
    }

    private String getS3Key(Event event) {
        LocalDateTime eventGeneratedDateTime = event.getEventGeneratedDateTime();
        String s3DatePrefix = getS3DatePrefix(eventGeneratedDateTime);

        String eventId = event.getEventId();
        String fileName = eventId + OUTPUT_EXTENSION;

        return String.format("%s/%s/%s", OUTPUT_VERSION, s3DatePrefix, fileName);
    }
}

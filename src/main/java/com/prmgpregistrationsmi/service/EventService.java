package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.SplunkWebclient.SplunkWebClient;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.BaseEvent;
import com.prmgpregistrationsmi.model.Event.DegradesEventDAO;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@AllArgsConstructor
@Service
public class EventService {
    private static final String OUTPUT_EXTENSION = ".json";
    private static final String OUTPUT_VERSION = "v1";
    private static final String DEGRADES_DIRECTORY = "degrades/";
    private final S3FileUploader eventS3Client;
    private final SplunkWebClient splunkWebClient;
    private final EnrichmentService enrichmentService;
    private final MessagePublisher messagePublisher;
    private final Clock clock;

    public EventDAO saveEvent(BaseEvent event, EventType eventType) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType, LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS));
        String s3Key = getS3Key(eventDAO.getRegistrationEventDateTime(), eventDAO.getEventId());
        enrichmentService.enrichEventDAO(eventDAO);
        messagePublisher.sendMessage(eventDAO, eventDAO.getEventId());
        eventS3Client.uploadJsonObject(eventDAO, s3Key);
        splunkWebClient.postEventToSplunkCloud(eventDAO);
        return eventDAO;
    }

    public DegradesEventDAO saveDegradesEvent(EhrDegradesEvent event, EventType eventType) throws UnableToUploadToS3Exception {
        DegradesEventDAO degradeEventDAO = DegradesEventDAO.fromEvent(event, eventType, LocalDateTime.now(clock).truncatedTo(ChronoUnit.DAYS));
        String s3Key = DEGRADES_DIRECTORY + getS3Key(degradeEventDAO.getEventGeneratedDateTime(), degradeEventDAO.getEventId());
        eventS3Client.uploadJsonObject(degradeEventDAO, s3Key);
        splunkWebClient.postEventToSplunkCloud(degradeEventDAO);
        return degradeEventDAO;
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

    private String getS3Key(LocalDateTime eventDatetime, String eventId) {
        String s3DatePrefix = getS3DatePrefix(eventDatetime);
        String fileName = eventId + OUTPUT_EXTENSION;

        return String.format("%s/%s/%s", OUTPUT_VERSION, s3DatePrefix, fileName);
    }
}

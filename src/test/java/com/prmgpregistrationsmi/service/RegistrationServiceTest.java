package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    S3FileUploader eventS3ClientMock = mock(S3FileUploader.class);

    RegistrationService registrationService = new RegistrationService(eventS3ClientMock);

    @Test
    void shouldCallUploadToS3WithEventDAO() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;

        EventDAO expectedEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);
        EventDAO eventDAO = registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(eq(expectedEventDAO), anyString());
        assertEquals(eventDAO, expectedEventDAO);
    }

    @Test
    void shouldUploadEventDAOToCorrectS3Key() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-12345")
                .eventGeneratedDateTime(LocalDateTime.of(2021, 1, 2, 3, 30))
                .build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;

        registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(any(), eq("v1/2021/01/02/03/event-id-12345.json"));
    }
}

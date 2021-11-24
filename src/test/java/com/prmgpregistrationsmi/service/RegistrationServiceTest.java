package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.testhelpers.DataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    S3FileUploader eventS3ClientMock = mock(S3FileUploader.class);

    RegistrationService registrationService = new RegistrationService(eventS3ClientMock);

    @Test
    void shouldCallUploadToS3WithEventDAO() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = DataBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationStartedEventType = EventType.GP2GP_REGISTRATION_STARTED;

        EventDAO expectedEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);
        EventDAO eventDAO = registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(eq(expectedEventDAO), anyString());
        assertEquals(eventDAO, expectedEventDAO);
    }

    @Test
    void shouldUploadEventDAOToCorrectS3Key() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = DataBuilder
                .withDefaultEventValues()
                .eventId("event-id-12345")
                .eventGeneratedTimestamp(1609556400L)
                .build();
        EventType gp2gpRegistrationStartedEventType = EventType.GP2GP_REGISTRATION_STARTED;

        registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(any(), eq("v1/2021/01/02/03/event-id-12345.json"));
    }
}

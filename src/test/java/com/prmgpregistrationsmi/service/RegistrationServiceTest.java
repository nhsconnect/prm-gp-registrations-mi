package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.Event.stage.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
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
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        EventDAO expectedEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType, transferProtocol, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        EventDAO eventDAO = registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType, transferProtocol);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(eq(expectedEventDAO), anyString());
        assertEquals(eventDAO, expectedEventDAO);
    }

    @Test
    void shouldUploadEventDAOToCorrectS3Key() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        EventDAO testEventDAO = registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType, transferProtocol);

        verify(eventS3ClientMock, times(1)).uploadJsonObject(any(),
                eq("v1/2020/01/01/22/" + testEventDAO.getEventId() + ".json"));
    }
}

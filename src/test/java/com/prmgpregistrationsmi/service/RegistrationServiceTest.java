package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    S3Client s3ClientMock = mock(S3Client.class);

    RegistrationService registrationService = new RegistrationService(s3ClientMock);

    @Test
    void shouldCallUploadToS3WithEventDAO() {
        Event testEvent = Event.builder().build();
        EventType gp2gpRegistrationStartedEventType = EventType.GP2GP_REGISTRATION_STARTED;

        registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        EventDAO expectedEvent = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(s3ClientMock, times(1)).uploadObject(eq(expectedEvent));

    }
}

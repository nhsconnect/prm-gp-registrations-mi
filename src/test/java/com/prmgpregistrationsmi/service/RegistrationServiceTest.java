package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    EventS3Client eventS3ClientMock = mock(EventS3Client.class);

    RegistrationService registrationService = new RegistrationService(eventS3ClientMock);

    @Test
    void shouldCallUploadToS3WithEventDAO() {
        Event testEvent = Event.builder().build();
        EventType gp2gpRegistrationStartedEventType = EventType.GP2GP_REGISTRATION_STARTED;

        registrationService.saveEvent(testEvent, gp2gpRegistrationStartedEventType);

        EventDAO expectedEvent = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);

        verify(eventS3ClientMock, times(1)).uploadObject(eq(expectedEvent));

    }
}

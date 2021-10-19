package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    S3Client s3ClientMock = mock(S3Client.class);

    RegistrationService registrationService = new RegistrationService(s3ClientMock);

    @Test
    void shouldCallUploadToS3WithEventDAO() {
        Event testEvent = Event.builder().build();

        registrationService.saveEvent(testEvent);

        EventDAO expectedEvent = EventDAO.fromEvent(testEvent);

        verify(s3ClientMock, times(1)).uploadObject(eq(expectedEvent));

    }
}

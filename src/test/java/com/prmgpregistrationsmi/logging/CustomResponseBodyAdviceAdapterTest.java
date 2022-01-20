package com.prmgpregistrationsmi.logging;

import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CustomResponseBodyAdviceAdapterTest {
    @Test
    void shouldReturnSameObject() throws URISyntaxException {
        ServerHttpRequest serverHttpRequest = mock(ServerHttpRequest.class);
        URI uri = new URI("registration/endpoint");
        when(serverHttpRequest.getURI()).thenReturn(uri);

        CustomResponseBodyAdviceAdapter customResponseBodyAdviceAdapter =
                new CustomResponseBodyAdviceAdapter();

        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        Object result = customResponseBodyAdviceAdapter.beforeBodyWrite(testEvent,
                mock(MethodParameter.class), mock(MediaType.class), null, serverHttpRequest,
                mock(ServerHttpResponse.class));

        assertEquals(testEvent, result);
    }
}
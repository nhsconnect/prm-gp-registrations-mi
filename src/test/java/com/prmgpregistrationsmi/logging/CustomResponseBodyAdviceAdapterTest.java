package com.prmgpregistrationsmi.logging;

import com.prmgpregistrationsmi.model.Event.BaseEvent;
import com.prmgpregistrationsmi.testhelpers.BaseEventBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CustomResponseBodyAdviceAdapterTest {
    @Mock
    private Class<? extends HttpMessageConverter<?>> converterType;

    @Test
    void shouldReturnSameObject() throws URISyntaxException {
        ServerHttpRequest serverHttpRequest = mock(ServerHttpRequest.class);
        URI uri = new URI("registration/endpoint");
        when(serverHttpRequest.getURI()).thenReturn(uri);

        CustomResponseBodyAdviceAdapter customResponseBodyAdviceAdapter =
                new CustomResponseBodyAdviceAdapter();

        BaseEvent testEvent = BaseEventBuilder
                .withDefaultEventValues()
                .build();

        Object result = customResponseBodyAdviceAdapter.beforeBodyWrite(testEvent,
                mock(MethodParameter.class), mock(MediaType.class), converterType, serverHttpRequest,
                mock(ServerHttpResponse.class));

        assertEquals(testEvent, result);
    }
}
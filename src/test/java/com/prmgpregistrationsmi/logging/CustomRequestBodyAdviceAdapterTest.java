package com.prmgpregistrationsmi.logging;

import com.prmgpregistrationsmi.model.Event.stage.EhrRequest.EhrRequestEvent;
import com.prmgpregistrationsmi.testhelpers.stage.EhrRequestEventBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CustomRequestBodyAdviceAdapterTest {
    @Mock
    private Class<? extends HttpMessageConverter<?>> converterType;

    @Test
    void shouldReturnSameObject() {
        HttpServletRequest  httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn("registration/endpoint");

        CustomRequestBodyAdviceAdapter customRequestBodyAdviceAdapter =
                new CustomRequestBodyAdviceAdapter(httpServletRequest);


        EhrRequestEvent testEvent = EhrRequestEventBuilder
                .withDefaultEventValues()
                .build();

        Object result = customRequestBodyAdviceAdapter.afterBodyRead(testEvent, mock(HttpInputMessage.class),
                mock(MethodParameter.class), mock(Type.class), converterType);

        assertEquals(testEvent, result);
    }
}
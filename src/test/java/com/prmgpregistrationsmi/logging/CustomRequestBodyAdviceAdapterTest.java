package com.prmgpregistrationsmi.logging;

import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomRequestBodyAdviceAdapterTest {

    @Test
    void shouldReturnSameObject() {
        HttpServletRequest  httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURI()).thenReturn("registration/endpoint");

        CustomRequestBodyAdviceAdapter customRequestBodyAdviceAdapter =
                new CustomRequestBodyAdviceAdapter(httpServletRequest);


        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        Object result = customRequestBodyAdviceAdapter.afterBodyRead(testEvent, mock(HttpInputMessage.class),
                mock(MethodParameter.class), mock(Type.class), null);

        assertEquals(testEvent, result);
    }
}
package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.utils.JsonHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonHelperTest {
    private Object generateTestObject() {
        return GPTransferMetadata.builder()
                .conversationId("some-id")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30))
                .build();
    }

    @Test
    void shouldConvertObjectWithDateTimeToJsonWithDateTimeString() {
        Object testObject = generateTestObject();

        assertEquals("{\"conversationId\":\"some-id\",\"transferEventDateTime\":\"2020-02-08T08:30:00\"}",
                JsonHelper.asJsonString(testObject));
    }
}
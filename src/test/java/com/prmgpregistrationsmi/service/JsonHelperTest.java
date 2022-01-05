package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.utils.JsonHelper;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonHelperTest {
    private Object generateTestObject() {
        return GPTransferMetadata.builder()
                .conversationId("some-id")
                .transferEventDateTime(new DateTime("2020-02-08T09:30:26Z"))
                .build();
    }

    @Test
    void shouldConvertObjectWithDateTimeToJsonWithDateTimeString() {
        Object testObject = generateTestObject();

        assertEquals("{\"conversationId\":\"some-id\",\"transferEventDateTime\":\"2020-02-08T09:30:26.000Z\"}",
                JsonHelper.asJsonString(testObject));
    }
}
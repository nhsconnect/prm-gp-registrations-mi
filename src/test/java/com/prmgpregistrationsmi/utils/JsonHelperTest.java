package com.prmgpregistrationsmi.utils;

import com.prmgpregistrationsmi.testhelpers.BaseEventBuilder;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonHelperTest {
    @Test
    void shouldReturnStringFromJSONObject() throws Exception {
        JSONObject object = new JSONObject();
        object.put("bool", true);
        object.put("else", 2);
        object.put("something", "value");

        String result = JsonHelper.asJsonString(object);
        String expectedResult = "{\"bool\":" + true + ",\"else\":2,\"something\":\"value\"}";
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowWhenUnableToParseObject() {
        assertThrows(RuntimeException.class, () -> {
            Rectangle rectangle = new Rectangle(10, 20);

            JsonHelper.asJsonString(rectangle);
        });
    }

    @Test
    void shouldConvertObjectWithDateTimeToJsonWithDateTimeString() {
        Object testObject = BaseEventBuilder
                .withDefaultEventValues()
                .build();

        assertEquals("{\"reportingSystemSupplier\":\"some-reporting-system-supplier\"," +
                        "\"reportingPracticeOdsCode\":\"some-reporting-practice-ods-code\"," +
                        "\"conversationId\":\"some-conversation-id\"," +
                        "\"registrationEventDateTime\":\"2020-01-01T22:22:22\"," +
                        "\"requestingPracticeOdsCode\":\"some-requesting-practice-ods-code\"," +
                        "\"sendingPracticeOdsCode\":\"some-sending-practice-ods-code\"}",
                JsonHelper.asJsonString(testObject));
    }
}
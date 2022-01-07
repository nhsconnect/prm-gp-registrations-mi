package com.prmgpregistrationsmi.utils;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonHelperTest {
    @Test
    void shouldReturnStringFromJSONObject() throws Exception {
        JSONObject object = new JSONObject();
        object.put("bool", true);
        object.put("else",2);
        object.put("something", "value");

        String result = JsonHelper.asJsonString(object);
        String expectedResult = "{\"bool\":"+true+",\"else\":2,\"something\":\"value\"}";
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
        Object testObject =  GPTransferMetadata.builder()
                .conversationId("some-id")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30))
                .build();

        assertEquals("{\"conversationId\":\"some-id\",\"transferEventDateTime\":\"2020-02-08T08:30:00\"}",
                JsonHelper.asJsonString(testObject));
    }
}
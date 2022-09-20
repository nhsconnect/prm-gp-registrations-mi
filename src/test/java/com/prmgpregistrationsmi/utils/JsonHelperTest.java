package com.prmgpregistrationsmi.utils;

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

    //TODO: FIX LATER
//    @Test
//    void shouldConvertObjectWithDateTimeToJsonWithDateTimeString() {
//        Object testObject =  BaseEventBuilder
//                .withDefaultEventValues()
//                .build();
//
//        assertEquals("{\"requestingPracticeOdsCode\":\"ABC1234\"," +
//                        "\"sendingPracticeOdsCode\":\"BCD1234\"}",
//                JsonHelper.asJsonString(testObject));
//    }
}
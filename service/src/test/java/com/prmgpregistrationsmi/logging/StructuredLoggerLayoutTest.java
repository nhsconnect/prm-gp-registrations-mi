package com.prmgpregistrationsmi.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.json.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class StructuredLoggerLayoutTest {
    private final StructuredLoggerLayout structuredLoggerLayout = new StructuredLoggerLayout(StandardCharsets.UTF_8);

    @Test
    void shouldConvertLogEventIntoStructuredLog() throws JSONException {
        Log4jLogEvent logEvent = new Log4jLogEvent().asBuilder()
                .setLevel(Level.INFO)
                .setMessage(new ParameterizedMessage("This is my fancy log entry", "some object"))
                .setSource(new StackTraceElement(
                        "classLoaderName",
                        null,
                        null,
                        "declaringClass",
                        "shouldDoThing",
                        "TestFile.java",
                        14))
                .setTimeMillis(1642073151459L)
                .build();

        String structuredLog = structuredLoggerLayout.toSerializable(logEvent);

        JSONObject parsedLog = new JSONObject(structuredLog);

        assertEquals(parsedLog.getString("level"), "INFO");
        assertEquals(parsedLog.getString("message"), "This is my fancy log entry");
        assertEquals(parsedLog.getString("data"), "some object");
        assertEquals(parsedLog.getString("timestamp"), "2022-01-13 11:25:51");

        assertEquals(parsedLog.getJSONObject("source").getString("classLoaderName"), "classLoaderName");
        assertTrue(parsedLog.getJSONObject("source").isNull("moduleName"));
        assertTrue(parsedLog.getJSONObject("source").isNull("moduleVersion"));
        assertEquals(parsedLog.getJSONObject("source").getString("methodName"), "shouldDoThing");
        assertEquals(parsedLog.getJSONObject("source").getString("fileName"), "TestFile.java");
        assertEquals(parsedLog.getJSONObject("source").getInt("lineNumber"), 14);
        assertEquals(parsedLog.getJSONObject("source").getString("className"), "declaringClass");
        assertFalse(parsedLog.getJSONObject("source").getBoolean("nativeMethod"));
    }
}
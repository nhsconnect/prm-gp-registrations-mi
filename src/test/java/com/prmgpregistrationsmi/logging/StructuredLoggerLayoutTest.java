package com.prmgpregistrationsmi.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StructuredLoggerLayoutTest {
    private final StructuredLoggerLayout structuredLoggerLayout = new StructuredLoggerLayout(StandardCharsets.UTF_8);

    @Test
    void shouldConvertLogEventIntoStructuredLog() {
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
        String expectedLog = "{\"level\":\"INFO\",\"message\":\"This is my fancy log entry\"," +
                "\"data\":\"some object\",\"source\":{\"classLoaderName\":\"classLoaderName\",\"moduleName\":null," +
                "\"moduleVersion\":null,\"methodName\":\"shouldDoThing\",\"fileName\":\"TestFile.java\"," +
                "\"lineNumber\":14,\"className\":\"declaringClass\",\"nativeMethod\":false}," +
                "\"timestamp\":\"2022-01-13 11:25:51\"}\r\n";
        assertEquals(expectedLog, structuredLog);
    }
}
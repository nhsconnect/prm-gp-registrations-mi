package com.prmgpregistrationsmi.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.ObjectMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class StructuredLogEntryTest {

    @Test
    void shouldCreateStructuredLogEntryFromALogEvent() {
        Log4jLogEvent expectedLog = new Log4jLogEvent().asBuilder()
                .setLevel(Level.INFO)
                .setMessage(new ObjectMessage("This is my fancy log entry"))
                .setLoggerName("STRUCTURED_LOGGER")
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

        StructuredLogEntry actualLog = StructuredLogEntry.getEntryFromLogEvent(expectedLog);

        assertEquals(expectedLog.getLevel(),actualLog.getLevel());
        assertEquals(expectedLog.getMessage(),actualLog.getMessage());
        assertEquals(expectedLog.getSource(),actualLog.getSource());
        assertEquals("2022-01-13 11:25:51",actualLog.getTimestamp());
    }
}
package com.prmgpregistrationsmi.model;

import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEhrDetails;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.ObjectMessage;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StructuredLogEntryTest {

    @Test
    void shouldCreateStructuredLogEntryFromALogEvent() {
        Log4jLogEvent expectedLog = new Log4jLogEvent().asBuilder()
                .setLevel(Level.INFO)
                .setMessage(new ObjectMessage("This is my fancy log entry"))
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

        assertEquals(Level.INFO.toString(),actualLog.getLevel());
        assertEquals(expectedLog.getMessage().getFormattedMessage(),actualLog.getMessage());
        assertEquals(expectedLog.getSource(),actualLog.getSource());
        assertEquals("2022-01-13 11:25:51",actualLog.getTimestamp());
    }

    @Test
    void shouldCreateStructuredLogEntryFromALogEventWithObject() {
        EhrGeneratedEhrDetails testEvent = EhrGeneratedEhrDetails
                .builder()
                .ehrStructuredSizeBytes(1L)
                .build();

        String expectedMessage = "Some message string";
        Log4jLogEvent expectedLog = new Log4jLogEvent().asBuilder()
                .setMessage(new ParameterizedMessage(expectedMessage,testEvent))
                .build();

        StructuredLogEntry actualLog = StructuredLogEntry.getEntryFromLogEvent(expectedLog);

        assertEquals(expectedMessage,actualLog.getMessage());
        assertEquals(Optional.of(testEvent), actualLog.getData());
    }
}
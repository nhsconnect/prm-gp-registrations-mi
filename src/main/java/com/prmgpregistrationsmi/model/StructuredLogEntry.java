package com.prmgpregistrationsmi.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.core.LogEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class StructuredLogEntry {
    private String level;
    private String message;
    private Object[] data;
    private StackTraceElement source;
    private String timestamp;

    public static StructuredLogEntry getEntryFromLogEvent(LogEvent logEvent) {
        long timeMillis = logEvent.getTimeMillis();
        Instant instant = Instant.ofEpochMilli(timeMillis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return StructuredLogEntry.builder()
                .level(logEvent.getLevel().getStandardLevel().name())
                .message(logEvent.getMessage().getFormattedMessage())
                .data(logEvent.getMessage().getParameters())
                .source(logEvent.getSource())
                .timestamp(dateTime.format(formatter))
                .build();
    }

}
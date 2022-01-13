package com.prmgpregistrationsmi.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class StructuredLogEntry {
    private String loggerName;
    private Message message;
    private Level level;
    private StackTraceElement source;
    private String timestamp;

    public static StructuredLogEntry getEntryFromLogEvent(LogEvent logEvent) {
        long timeMillis = logEvent.getTimeMillis();
        Instant instant = Instant.ofEpochMilli(timeMillis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return StructuredLogEntry.builder()
                .level(logEvent.getLevel())
                .message(logEvent.getMessage())
                .loggerName(logEvent.getLoggerName())
                .source(logEvent.getSource())
                .timestamp(dateTime.format(formatter))
                .build();
    }

}
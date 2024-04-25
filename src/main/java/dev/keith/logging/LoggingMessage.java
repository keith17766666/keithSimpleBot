package dev.keith.logging;

import org.slf4j.event.Level;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoggingMessage {
    private final String message;
    private LoggingMessage(String name, String message, Level level) {
        this.message = LocalTime.now().format(DateTimeFormatter.ISO_DATE) +
                "[" + name + "]" + level.toString() + ": " +  message;
    }
    public static LoggingMessage of(String name, String message, Level level) {
        return new LoggingMessage(name, message, level);
    }
    @Override
    public String toString() {
        return getMessage();
    }
    public String getMessage() {
        return message;
    }
}

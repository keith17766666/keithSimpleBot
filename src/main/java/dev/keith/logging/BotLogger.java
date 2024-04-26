package dev.keith.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.AbstractLogger;

import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BotLogger extends AbstractLogger implements Logger {
    private final String name;
    private Level defaultLevel;
    private final PrintStream output;
    public BotLogger(String botName, Level defaultLevel, PrintStream output) {
        this.name = "Discord bot: " + botName;
        this.defaultLevel = defaultLevel;
        this.output = output;
    }
    public BotLogger(String botName, PrintStream output) {
        this(botName, Level.INFO, output);
    }

    public void setDefaultLevel(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    @Override
    protected String getFullyQualifiedCallerName() {
        return name;
    }

    @Override
    protected void handleNormalizedLoggingCall(Level level, Marker marker, String s, Object[] objects, Throwable throwable) {
        output.print("[" + name + "]" + level.toString() + ": " + s + "\n");
    }

    @Override
    public boolean isTraceEnabled() {
        return isLevelEnabled(Level.TRACE);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isLevelEnabled(Level.TRACE);
    }

    @Override
    public boolean isDebugEnabled() {
        return isLevelEnabled(Level.DEBUG);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isLevelEnabled(Level.DEBUG);
    }

    @Override
    public boolean isInfoEnabled() {
        return isLevelEnabled(Level.INFO);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isLevelEnabled(Level.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return isLevelEnabled(Level.WARN);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isLevelEnabled(Level.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return isLevelEnabled(Level.ERROR);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isLevelEnabled(Level.ERROR);
    }
    private boolean isLevelEnabled(Level level) {
        return defaultLevel.toInt() >= level.toInt();
    }
}

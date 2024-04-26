package dev.keith.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.AbstractLogger;

import java.io.PrintStream;

/**
 * A simple logger for discord bot.
 */
public class BotLogger extends AbstractLogger implements Logger {
    /**
     * the bot name.
     */
    private final String name;
    /**
     * the allowed Level
     * @see Level
     */
    private Level defaultLevel;
    /**
     * the output
     */
    private final PrintStream output;

    /**
     * The init.
     * @param botName the name.
     * @param defaultLevel the default allowed level.
     * @param output the output.
     */
    public BotLogger(String botName, Level defaultLevel, PrintStream output) {
        this.name = "Discord bot: " + botName;
        this.defaultLevel = defaultLevel;
        this.output = output;
    }

    /**
     * simply init
     * @param botName the name.
     * @param output the output
     */
    public BotLogger(String botName, PrintStream output) {
        this(botName, Level.INFO, output);
    }

    /**
     * set the default level
     * @param defaultLevel the level
     */
    public void setDefaultLevel(Level defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    /**
     * get the name
     * @return name
     */
    @Override
    protected String getFullyQualifiedCallerName() {
        return name;
    }

    /**
     * printing
     * @param level level
     * @param marker marker
     * @param s message
     * @param objects objects
     * @param throwable throwable
     */
    @Override
    protected void handleNormalizedLoggingCall(Level level, Marker marker, String s, Object[] objects, Throwable throwable) {
        output.print("[" + name + "]" + level.toString() + ": " + s + "\n");
    }

    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isTraceEnabled() {
        return isLevelEnabled(Level.TRACE);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isTraceEnabled(Marker marker) {
        return isLevelEnabled(Level.TRACE);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isDebugEnabled() {
        return isLevelEnabled(Level.DEBUG);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isDebugEnabled(Marker marker) {
        return isLevelEnabled(Level.DEBUG);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isInfoEnabled() {
        return isLevelEnabled(Level.INFO);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isInfoEnabled(Marker marker) {
        return isLevelEnabled(Level.INFO);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isWarnEnabled() {
        return isLevelEnabled(Level.WARN);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isWarnEnabled(Marker marker) {
        return isLevelEnabled(Level.WARN);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isErrorEnabled() {
        return isLevelEnabled(Level.ERROR);
    }
    /**
     * enabled?
     * @return is enable?
     */
    @Override
    public boolean isErrorEnabled(Marker marker) {
        return isLevelEnabled(Level.ERROR);
    }
    /**
     * enabled?
     * @return is enable?
     */
    private boolean isLevelEnabled(Level level) {
        return defaultLevel.toInt() >= level.toInt();
    }
}

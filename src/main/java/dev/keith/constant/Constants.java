package dev.keith.constant;

import dev.keith.logging.BotLogger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * Constants for all classes.
 */
public final class Constants {
    /**
     * Don't let anyone init this class.
     */
    private Constants() {}

    /**
     * The token.
     */
    public static String TOKEN;
    /**
     * The invite link for the bot.
     */
    public static String INVITE_LINK;
    /**
     * The logger.
     */
    public static BotLogger LOGGER;
    /**
     * The jda.
     */
    public static JDA JDA;

    /**
     * Init the jda and connect.
     * @param token -the token
     */
    public static void createJDAFrom(String token) {
        JDA = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.of(Activity.ActivityType.PLAYING,
                        "/help to get help, !slash to reload slash command."))
                .build();
    }
}

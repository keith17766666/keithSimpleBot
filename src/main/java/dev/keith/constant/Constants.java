package dev.keith.constant;

import dev.keith.logging.BotLogger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.event.Level;

public final class Constants {
    private Constants() {}
    public static String TOKEN;
    public static String INVITE_LINK =
            "https://discord.com/oauth2/authorize?client_id=1231250638474707104&permissions=633318697598975&scope=bot+applications.commands";
    public static BotLogger LOGGER;
    public static JDA JDA;
    public static void createJDAfrom(String TOKEN) {
        JDA = JDABuilder.createDefault(TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.of(Activity.ActivityType.PLAYING,
                        "/help to get help, !slash to reload slash command."))
                .build();
    }
}

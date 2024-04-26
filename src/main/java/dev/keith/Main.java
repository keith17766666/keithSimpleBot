package dev.keith;

import dev.keith.constant.Constants;
import dev.keith.listeners.CommandListener;
import dev.keith.logging.BotLogger;
import dev.keith.swing.Screen;
import dev.keith.swing.SwingOutputStream;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.slf4j.event.Level;

import javax.swing.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class for the bot.
 */
public class Main extends ListenerAdapter {
    /**
     * is enabled the slash command?
     */
    public static final Map<Guild, Boolean> enabledSlash = new HashMap<>();
    /**
     * the screen
     */
    public static final Screen screen = new Screen();
    /**
     * the area
     */
    public static final JTextArea area = new JTextArea();

    /**
     * a simple init
     */
    public Main() {}
    /**
     * main
     * @param args the args
     */
    public static void main(String[] args) {
        PrintStream outputStream = new PrintStream(new SwingOutputStream(area));
        System.setErr(outputStream);
        Constants.LOGGER = new BotLogger("keith's bot", Level.DEBUG, outputStream);
        screen.setup(area);
        try {
            Constants.TOKEN = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("No token is provided, exit now");
            System.exit(1);
        }
        Constants.createJDAFrom(Constants.TOKEN);
        init();
    }

    /**
     * init the bot
     */
    public static void init() {
        Constants.LOGGER.info("keith's bot started");
        Constants.INVITE_LINK = Constants.JDA.getInviteUrl(Permission.getPermissions(Permission.ALL_PERMISSIONS));

        Constants.LOGGER.debug("registering command");

        CommandListener.register(Constants.JDA);
        Constants.JDA.addEventListener(new Main());
        Constants.LOGGER.info("Invite link: " + Constants.INVITE_LINK);
        Constants.LOGGER.info("Login in " + Constants.JDA.getSelfUser().getName());
    }

    /**
     * register all slash command
     * @param id the guild id
     */
    public static void register(Long id) {
        Guild guild = Constants.JDA.getGuildById(id);
        if(guild != null && enabledSlash.getOrDefault(guild, false)) {
            guild.updateCommands()
                    .addCommands(Commands.slash("hello", "Say hello"))
                    .addCommands(Commands.slash("ping", "get Pings"))
                    .addCommands(Commands.slash("help", "get Help"))
                    .addCommands(Commands.slash("invite", "get Invite for this bot"))
                    .addCommands(Commands.slash("message", "send message by this command")
                            .addOption(OptionType.STRING, "message_to_send", "the message to send", true))
                    .addCommands(Commands.slash("role", "add roles")
                            .addOption(OptionType.ROLE, "role", "the role to added", true)
                            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_ROLES)))
                    .queue();
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Constants.LOGGER.info("New guild " + event.getGuild().getName() + " added!");
        enabledSlash.put(event.getGuild(), false);
    }
}
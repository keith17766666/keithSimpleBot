package dev.keith;

import dev.keith.constant.Constants;
import dev.keith.listeners.CommandListener;
import dev.keith.swing.Screen;
import dev.keith.swing.SwingOutputStream;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import javax.swing.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;


public class Main extends ListenerAdapter {
    public static final Map<Guild, Boolean> enabledSlash = new HashMap<>();
    public static final Screen screen = new Screen();
    public static final JTextArea area = new JTextArea();
    public static void main(String[] args) {
        Constants.TOKEN = args[0];
        SwingOutputStream outputStream = new SwingOutputStream(area);
        System.setErr(new PrintStream(outputStream));
        screen.setup(area);
        init();
    }

    public static void init() {
        Constants.LOGGER.info("keith's bot started.");
        if(Constants.JDA.getGuilds().isEmpty()) {
            Constants.LOGGER.warn("invite link: " + Constants.INVITE_LINK);
        }
        Constants.LOGGER.debug("registering command.");
        CommandListener.register(Constants.JDA);
        Constants.JDA.addEventListener(new Main());
    }
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
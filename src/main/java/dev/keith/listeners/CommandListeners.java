package dev.keith.listeners;

import dev.keith.Main;
import dev.keith.constant.Constants;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.Objects;

import static dev.keith.listeners.CommandListener.helper;


/**
 * All commands here.
 */
@SuppressWarnings("unused")
public class CommandListeners {
    /**
     * don't let anyone init.
     */
    private CommandListeners() {}
    /**
     * ping command
     */
    public static final EventListener PING =
            new CommandListener("ping",
                    event -> {
                        event.deferReply().queue();
                        event.getHook().sendMessage("Ping is: " + Constants.JDA.getGatewayPing() + "ms").queue();
                    });
    /**
     * hello command
     */
    public static final EventListener HELLO =
            new CommandListener("hello",
                    event -> {
                event.deferReply().queue();
                event.getHook().sendMessage("Hello " + event.getUser().getName() + "!").queue();
            });
    /**
     * message command
     */
    public static final EventListener MESSAGE =
            GenericEvent -> {
                if(GenericEvent instanceof SlashCommandInteractionEvent event) {
                    if(helper.checkMessage(event)) {
                        event.deferReply().queue();
                        try {
                            event.getHook().sendMessage(event.getOption("message_to_send").getAsString()).queue();
                        } catch (NullPointerException ignored) {}
                    }
                }
            };
    /**
     * role command
     */
    public static final EventListener ROLE =
            new CommandListener("role",
                    event -> {
                        event.getHook().sendMessage("if you want to get role \""
                                + Objects.requireNonNull(event.getOption("role"))
                                .getAsRole().getName() + "\", please retention this message").queue();
                        EventListener ROLE_HELPER =
                                GenericEvent -> {
                                    if(GenericEvent instanceof MessageReactionAddEvent event1) {
                                        if(event1.getMessageAuthorIdLong() == Constants.JDA.getSelfUser().getIdLong()) {
                                            event1.getGuild().addRoleToMember(event.getUser()
                                                    , Objects.requireNonNull(event.getOption("role")).getAsRole()).queue();
                                        }
                                    }
                                };
                        Constants.JDA.addEventListener(ROLE_HELPER);
                    });
    /**
     * invite command
     */
    public static final EventListener INVITE =
            new CommandListener("invite",
                    event -> event.getHook().
                            sendMessage(Constants.INVITE_LINK).queue());
    /**
     * register slash command
     */
    public static final EventListener SLASH =
            GenericEvent -> {
                if(GenericEvent instanceof MessageReceivedEvent event) {
                    if(event.getMessage().getContentRaw().equals("!slash")) {
                        if(!(event.isWebhookMessage() || event.getAuthor().isBot())) {
                            Constants.LOGGER.info("Reloading slash command for " + event.getGuild().getName());
                            event.getChannel()
                                    .asTextChannel()
                                    .sendMessage("Registering slash command")
                                    .queue();
                            Main.register(event.getGuild().getIdLong());
                            event.getChannel()
                                    .asTextChannel()
                                    .sendMessage("Done!")
                                    .queue();
                        }
                    }
                }
            };
    /**
     * help command
     */
    public static final EventListener HELP =
            new CommandListener("help",
                    event -> event.getHook().sendMessage(
                            """
                                    <-----------------------Keith's bot Help menu------------------------> \s
                                    /help -> get Helps\s
                                    /ping -> get Ping\s
                                    /hello -> say hello to you! \s
                                    /message *message* -> say message ! \s
                                    /role -> send a message, when you retention that, you will get a role \s
                                    /invite -> get the invite link \s
                                    <-------------------------------------------------------------------->
                                    """).queue());

}

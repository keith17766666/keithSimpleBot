package dev.keith.listeners;

import dev.keith.constant.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;


/**
 * A better event listener for slash command.
 * @see CommandHelper
 */
public class CommandListener implements EventListener {
    /**
     * The name.
     */
    private final String command;
    /**
     * the handler for the event.
     */
    private final Consumer<SlashCommandInteractionEvent> consumer;
    /**
     * the helper.
     */
    public static CommandHelper helper = CommandHelper.getInstance();
    /**
     * the listeners for method {@code register(JDA jda)}
     */
    private static final ArrayList<CommandListener> listeners = new ArrayList<>();

    /**
     * Simple init.
     * @param command the name
     * @param consumer the handler
     */
    public CommandListener(String command, Consumer<SlashCommandInteractionEvent> consumer) {
        this.command = command;
        this.consumer = consumer;
        listeners.add(this);
    }

    /**
     * The event handle method.
     * @param genericEvent the event.
     * @see EventListener
     */
    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof SlashCommandInteractionEvent event) {
            if (helper.checkMessage(command, event)) {
                Constants.LOGGER.debug("Commnad " + event.getName()
                        + " is called by " + event.getUser().getName() + " in guild "
                        + Objects.requireNonNull(event.getGuild()).getName());
                consumer.accept(event);
            }
        }
    }

    /**
     * Register all slash command.
     * @param jda the JDA.
     */
    public static void register(JDA jda) {
        for(CommandListener listener : listeners) {
            jda.addEventListener(listener);
        }
    }
}

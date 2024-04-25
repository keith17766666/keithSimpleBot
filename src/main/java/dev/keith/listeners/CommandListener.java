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

public class CommandListener implements EventListener {
    private final String command;
    private final Consumer<SlashCommandInteractionEvent> consumer;
    public static CommandHelper helper = CommandHelper.getInstance();
    private static final ArrayList<CommandListener> listeners = new ArrayList<>();
    public CommandListener(String command, Consumer<SlashCommandInteractionEvent> consumer) {
        this.command = command;
        this.consumer = consumer;
        listeners.add(this);
    }
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
    public static void register(JDA jda) {
        for(CommandListener listener : listeners) {
            jda.addEventListener(listener);
        }
    }
}

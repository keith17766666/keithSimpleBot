package dev.keith.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandHelper {
    private CommandHelper() {}
    private static final CommandHelper instance = new CommandHelper();

    public static CommandHelper getInstance() {
        return instance;
    }
    public boolean checkMessage(String name, SlashCommandInteractionEvent event) {
        if(event.getUser().isBot()) {
            return false;
        }
        return event.getName().equals(name);
    }
    public boolean checkMessage(SlashCommandInteractionEvent event) {
        return !event.getUser().isBot();
    }
}

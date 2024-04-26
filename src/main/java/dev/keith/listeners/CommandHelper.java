package dev.keith.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


/**
 * A helper for commands.
 * @see CommandListener
 */
public class CommandHelper {
    /**
     * Don't let anyone init the class.
     */
    private CommandHelper() {}

    /**
     * The only way to get instance.
     */
    private static final CommandHelper instance = new CommandHelper();

    /**
     * Get the instance.
     * @return a helper
     */
    public static CommandHelper getInstance() {
        return instance;
    }

    /**
     * Check the message is bot and it is equal the name.
     * @param name the slash name
     * @param event the event
     * @return Can run the event handler
     * @see CommandListener
     */
    public boolean checkMessage(String name, SlashCommandInteractionEvent event) {
        if(event.getUser().isBot()) {
            return false;
        }
        return event.getName().equals(name);
    }

    /**
     * A simply checking.
     * @param event the event
     * @return Can run the event handler
     * @see CommandListener
     */
    public boolean checkMessage(SlashCommandInteractionEvent event) {
        return !event.getUser().isBot();
    }
}

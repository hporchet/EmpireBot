package fr.ttanesque.empirebot.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * The interface for basic Message command (delete, modify...).
 */
public interface IbasicCommand extends Runnable {

    /**
     * Get the regex for this command.
     * @implSpec Create the regex foe dtect the command in message
     * @return the regex
     */
    Pattern patternConstructor();

    /**
     * Get the name of the command.
     * @implSpec return the name
     * @return the name
     */
    String getName();

    /**
     * Get the description of the command.
     * @implSpec return the description
     * @return the description
     */
    String getDescription();

    /**
     * Get the help text of the command.
     * @implSpec return the help message
     * @return the help text
     */
    String getHelp();

    /**
     * Check if the message match with the command.
     * @param m
     * @return {@link Boolean}
     */
    default boolean checkMatch(Matcher m) {
        return m.matches();
    }

    /**
     * Prepare the object to be runnable.
     * @implSpec treat your message
     * @param m
     * @param message
     */
    void setRunnable(Matcher m, MessageReceivedEvent message);
}

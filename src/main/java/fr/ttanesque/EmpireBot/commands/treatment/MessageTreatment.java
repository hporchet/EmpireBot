package fr.ttanesque.EmpireBot.commands.treatment;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;

/**
 * The abstract class who describe the treatment of an command define
 * methods for the extended classes to use.
 */
public abstract class MessageTreatment {
    /**
     * The matcher who contains the command.
     */
    private final Matcher text;

    /**
     * The message.
     */
    private final MessageReceivedEvent eventMessage;

    /**
     * The constructor.
     *
     * @param text the matcher get with regex in {@link fr.ttanesque.EmpireBot.commands.Command}
     * @param eventMessage the message.
     */
    public MessageTreatment(final Matcher text, final MessageReceivedEvent eventMessage) {
        this.text = text;
        this.eventMessage = eventMessage;
    }

    /**
     * Verify if the channel is text type and the user is not a bot.
     * @return the truth
     */
    protected boolean checkBotChannel() {
        return eventMessage.isFromType(ChannelType.TEXT)
        && !eventMessage.getAuthor().isBot();
    }

    /**
     * Verify if user has the needed permission.
     * @param permissionEnum the permission
     * @return the truth
     */
    protected boolean checkUserPemission(final Permission permissionEnum) {
        return eventMessage.getGuild()
                        .getSelfMember()
                        .hasPermission(permissionEnum);
    }

    /**
     * Getter for the matcher.
     * @return the matcher
     */
    public Matcher getText() {
        return text;
    }

    /**
     * Getter for the Message.
     * @return the message
     */
    public MessageReceivedEvent getEventMessage() {
        return eventMessage;
    }
}

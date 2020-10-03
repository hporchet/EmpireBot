package fr.ttanesque.empirebot.commands.admin.treatment;

import java.util.regex.Matcher;

import fr.ttanesque.empirebot.commands.treatment.MessageTreatment;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MoveMsgTreatment extends MessageTreatment implements Runnable {

    /**
     * The constructor.
     *
     * @param text         the matcher get with regex in {@link Clean}
     * @param eventMessage the message.
     */
    public MoveMsgTreatment(Matcher text, MessageReceivedEvent eventMessage) {
        super(text, eventMessage);
    }

    @Override
    public void run() {
        MessageChannel channel = this.getEventMessage().getChannel();

        User userAction = this.getEventMessage().getAuthor();

        // check if format is good
        String url = this.getText().group(1);
        String messageId = this.getMessageId(url);
        if (this.checkUserPemission(Permission.MESSAGE_MANAGE)
                                        && this.isDiscordAppUrl(url)) {

            // get channel to move mentionned in message
            MessageChannel channelToMove = this.getEventMessage()
                                                .getMessage()
                                                .getMentionedChannels()
                                                .get(0);
            if (channelToMove == null)
                this.error("Channel not found !");

            // get message to move mentionned in url
            Message messageToMove = channel.retrieveMessageById(messageId)
                                            .complete();

            if (messageToMove == null)
                this.error("Message not found !");

            // get the user
            User userMove = messageToMove.getAuthor();

            this.embedSend(channelToMove, "Moved by ",
                userAction, userMove, messageToMove.getContentRaw());

            //delete old message
            messageToMove.delete().queue();
            this.getEventMessage().getMessage().delete().queue();
        } else {
            this.error("you don't have the right to make this.");
        }
    }

}

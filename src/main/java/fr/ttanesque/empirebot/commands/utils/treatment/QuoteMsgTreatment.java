package fr.ttanesque.empirebot.commands.utils.treatment;

import java.util.regex.Matcher;

import fr.ttanesque.empirebot.commands.treatment.MessageTreatment;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class QuoteMsgTreatment extends MessageTreatment implements Runnable {

    /**
     * The constructor.
     *
     * @param text         the matcher get with regex in {@link Clean}
     * @param eventMessage the message.
     */
    public QuoteMsgTreatment(Matcher text, MessageReceivedEvent eventMessage) {
        super(text, eventMessage);
    }

    @Override
    public void run() {
        MessageChannel channel = this.getEventMessage().getChannel();

        User userAction = this.getEventMessage().getAuthor();

        // check if format is good
        String url = this.getEventMessage().getMessage().getContentRaw();
        String messageId = this.getMessageId(url);
        
            // get message to move mentionned in url
            Message messageToQuote = channel.retrieveMessageById(messageId)
                                            .complete();

            if (messageToQuote == null)
                this.error("Message not found !");

            // get the user
            User userMove = messageToQuote.getAuthor();

            this.embedSend(channel, "Quoted by ",
                userAction, userMove, messageToQuote.getContentRaw());

            this.getEventMessage().getMessage().delete().queue();

    }

}

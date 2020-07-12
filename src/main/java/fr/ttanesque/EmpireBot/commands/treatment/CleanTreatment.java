package fr.ttanesque.EmpireBot.commands.treatment;

import fr.ttanesque.EmpireBot.commands.Clean;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Execute the treatment for the clean command.
 */
public class CleanTreatment extends MessageTreatment implements Runnable {

    /**
     * The cap for an all demand to delete message.
     */
    private static final int ALL_NUMBER = 100;

    /**
     * The constructor.
     *
     * @param text         the matcher get with regex in {@link Clean}
     * @param eventMessage the message.
     */
    public CleanTreatment(final Matcher text, final MessageReceivedEvent eventMessage) {
        super(text, eventMessage);
    }

    @Override
    public void run() {
        if (this.checkBotChannel()) {
            if (this.checkUserPemission(Permission.MESSAGE_MANAGE)) {

                // get the channel and the argument of the metods
                MessageChannel channel = eventMessage.getChannel();
                String arg = this.text.group(1);

                // get the number to delete
                int toDelete = ALL_NUMBER;
                if (!arg.equals("all")) {
                    toDelete = Integer.parseInt(arg) + 1;
                }

                // delete with an iterator
                Iterator<Message> iterator = channel.getIterableHistory().iterator();
                while (iterator.hasNext() && toDelete != 0) {
                    iterator.next().delete().queue();
                    toDelete--;
                }
            }
        }
    }
}

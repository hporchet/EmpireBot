package fr.ttanesque.empirebot.commands.admin;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ttanesque.empirebot.Config;
import fr.ttanesque.empirebot.commands.treatment.MessageTreatment;
import fr.ttanesque.empirebot.commands.IbasicCommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * The class who delete an number of message given in arguments.
 */
public class Clean extends MessageTreatment implements IbasicCommand {

    /**
     * The cap for an all demand to delete message.
     */
    private static final int ALL_NUMBER = 100;

    /**
     * Basic constructor.
     */
    public Clean() {
        super(null, null);
    }

    @Override
    public String getDescription() {
        return "delete the number of message given in arguments";
    }

    @Override
    public String getHelp() {
        return "clean <number> || all";
    }

    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public Pattern patternConstructor() {
        // create the regex
        String prefix = "";
        try {
            prefix = Config.getConfigInstance().getInitConf().getPrefix();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regex = prefix + this.getName() + " (\\d*|all)";
        return Pattern.compile(regex);
    }

    @Override
    public void setRunnable(final Matcher m, final MessageReceivedEvent message) {
        this.setEventMessage(message);
        this.setText(m);
    }

    @Override
    public void run() {
        if (this.checkBotChannel() && this.checkUserPemission(Permission.MESSAGE_MANAGE)) {
            // get the channel and the argument of the methods
            final MessageChannel channel = this.getEventMessage().getChannel();
            final String arg = this.getText().group(1);

            // get the number to delete
            int toDelete = ALL_NUMBER;
            if (!arg.equals("all")) {
                toDelete = Integer.parseInt(arg) + 1;
            }

            List<Message> messages = channel.getHistoryBefore(
                this.getEventMessage().getMessageId(),
                toDelete
                ).complete()
                 .getRetrievedHistory();

            channel.purgeMessages(messages);
            this.getEventMessage().getMessage().delete().complete();
        }
    }

}

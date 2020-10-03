package fr.ttanesque.empirebot.commands.utils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ttanesque.empirebot.commands.IbasicCommand;
import fr.ttanesque.empirebot.commands.treatment.MessageTreatment;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Command ask to bot to delete the message after x seconds.
 */
public class DelTimeMessage extends MessageTreatment implements IbasicCommand {

    /**
     * Default delay.
     */
    private static final int DEFAULT_DELAY = 30;

    /**
     * Constructor.
     */
    public DelTimeMessage() {
        super(null, null);
    }

    @Override
    public void run() {
        int delay = DEFAULT_DELAY;
        if (this.getText().groupCount() == 2) {
            delay = Integer.parseInt(this.getText().group(2).replace(" ", ""));
        }

        this.getEventMessage().getMessage().delete().completeAfter(delay, TimeUnit.SECONDS);

    }

    @Override
    public Pattern patternConstructor() {
        return Pattern.compile(".*(&del)( \\d*)?");
    }

    @Override
    public String getName() {
        return "delete_timer";
    }

    @Override
    public String getDescription() {
        return "delete a message after the given or default time ";
    }

    @Override
    public String getHelp() {
        return "your message &del <secondes> seconde is optionnal";
    }

    /**
     * Prepare the object to be runnable.
     * @param m
     * @param message
     */
    public void setRunnable(final Matcher m, final MessageReceivedEvent message) {
        this.setEventMessage(message);
        this.setText(m);
    }

}

package fr.ttanesque.empirebot.commands.utils;

import java.util.regex.Matcher;

import javax.annotation.Nonnull;

import fr.ttanesque.empirebot.commands.Command;
import fr.ttanesque.empirebot.commands.utils.treatment.QuoteMsgTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class QuoteMsg extends Command {

    public QuoteMsg() {
        super("", "", "");

        this.constructRegex("https:\\/\\/discordapp\\.com\\/channels\\/(\\d*)\\/(\\d*)\\/(\\d*)");
    }

    @Override
    public void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (m.matches()) {
            Thread thread = new Thread(new QuoteMsgTreatment(m, event));
            thread.start();
        }
    }

    
    
}

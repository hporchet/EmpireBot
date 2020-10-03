package fr.ttanesque.empirebot.commands.utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;

import fr.ttanesque.empirebot.Config;
import fr.ttanesque.empirebot.commands.Command;
import fr.ttanesque.empirebot.commands.utils.treatment.GoogleTreatment;

import java.io.IOException;
import java.util.regex.Matcher;

public class Google extends Command {
    /**
     * The basic constructor for an commands.
     */
    public Google() {
        super(
                "google",
                "search with google",
                "generate google search link use google <your research>"
        );

        //create the regex
        String prefix = "";
        try {
            prefix = Config.getConfigInstance().getInitConf().getPrefix();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regex = prefix + this.getName() + " (.+)";
        this.constructRegex(regex);
    }

    @Override
    public final void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (m.matches()) {
            Thread thread = new Thread(new GoogleTreatment(m, event));
            thread.start();
        }
    }
}

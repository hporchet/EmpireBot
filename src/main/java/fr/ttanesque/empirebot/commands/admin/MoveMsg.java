package fr.ttanesque.empirebot.commands.admin;

import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nonnull;

import fr.ttanesque.empirebot.Config;
import fr.ttanesque.empirebot.commands.Command;
import fr.ttanesque.empirebot.commands.admin.treatment.MoveMsgTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Class for test the embed message.
 */
public class MoveMsg extends Command {

    /**
     * Constructor.
     */
    public MoveMsg() {
        super(
            "mv",
            "bouge un message dans un autre channel",
            "mv <lien message> <channel>"
            );

        //create the regex
        String prefix = "";
        try {
            prefix = Config.getConfigInstance().getInitConf().getPrefix();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regex = prefix + this.getName() + " (.*) (.*)";
        this.constructRegex(regex);
    }

    @Override
    public final void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (m.matches()) {
            Thread thread = new Thread(new MoveMsgTreatment(m, event));
            thread.start();
        }
    }

}

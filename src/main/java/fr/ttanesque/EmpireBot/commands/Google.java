package fr.ttanesque.EmpireBot.commands;

import fr.ttanesque.EmpireBot.Config;
import fr.ttanesque.EmpireBot.commands.treatment.GoogleTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;
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
            prefix = Config.configInstance.getInitConf().getPrefix();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regex = prefix + this.getName() + " (.+)";
        this.constructRegex(regex);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (m.matches()) {
            Thread thread = new Thread(new GoogleTreatment(m, event));
            thread.start();
        }
    }
}

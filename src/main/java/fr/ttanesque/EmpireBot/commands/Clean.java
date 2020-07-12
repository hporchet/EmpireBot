package fr.ttanesque.EmpireBot.commands;

import fr.ttanesque.EmpireBot.Config;
import fr.ttanesque.EmpireBot.commands.treatment.CleanTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.regex.Matcher;

/**
 * The class who delete an number of message given in arguments.
 */
public class Clean extends Command {

    /**
     * The constructor of the clean commands.
     */
    public Clean() {
        super(
                "clean",
                "delete the number of message given in arguments",
                "clean <number>"
        );

        //create the regex
        String prefix = "";
        try {
            prefix = Config.configInstance.getInitConf().getPrefix();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String regex = prefix + this.getName() + " (\\d|all)";
        this.constructRegex(regex);

    }

    @Override
    public void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (m.matches()) {
            Thread thread = new Thread(new CleanTreatment(m, event));
            thread.start();
        }
    }
}

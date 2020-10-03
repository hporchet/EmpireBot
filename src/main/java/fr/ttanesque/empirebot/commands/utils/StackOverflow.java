package fr.ttanesque.empirebot.commands.utils;

import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nonnull;

import fr.ttanesque.empirebot.Config;
import fr.ttanesque.empirebot.commands.Command;
import fr.ttanesque.empirebot.commands.utils.treatment.StackOverflowTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StackOverflow extends Command {
	public StackOverflow() {
        super(
            "StackOverFlow",
            "create a url for an research with stackoverflow.",
            "votre_recherche"
                + " \"words here\" exact phrase "
                + "[le-tag] search within a tag"
                + "user:pseudo search by author"
                + "score:le_score_min"
                + "answers:nb_of_answer minimum of answer"
                + "isaccepted:yes search within status"
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
            Thread thread = new Thread(new StackOverflowTreatment(m, event));
            thread.start();
        }
    }
    
}

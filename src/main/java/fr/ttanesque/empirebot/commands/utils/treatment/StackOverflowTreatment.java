package fr.ttanesque.empirebot.commands.utils.treatment;

import java.util.regex.Matcher;

import fr.ttanesque.empirebot.commands.treatment.MessageTreatment;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StackOverflowTreatment extends MessageTreatment implements Runnable {

    public StackOverflowTreatment(Matcher text, MessageReceivedEvent eventMessage) {
        super(text, eventMessage);
    }

    @Override
    public void run() {
        if (this.checkBotChannel()) {
            String recherche = "https://stackoverflow.com/search?q=";
            recherche += this.getText().group(1).replace(' ', '+');

            this.getEventMessage().getChannel().sendMessage(recherche).queue();
        }

    }
    
}

package fr.ttanesque.EmpireBot.commands.treatment;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;

public class GoogleTreatment extends MessageTreatment implements Runnable {

    /**
     * The constructor.
     *
     * @param text         the matcher get with regex in {@link fr.ttanesque.EmpireBot.commands.Google}
     * @param event the message.
     */
    public GoogleTreatment(Matcher text, MessageReceivedEvent event) {
        super(text, event);
    }

    @Override
    public void run() {
        if (this.checkBotChannel()) {
            String recherche = "https://www.google.com/search?q=";
            recherche += text.group(1).replace(' ', '+');

            eventMessage.getChannel().sendMessage(recherche).queue();
        }
    }
}

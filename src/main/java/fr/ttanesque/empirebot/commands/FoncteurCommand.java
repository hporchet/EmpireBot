package fr.ttanesque.empirebot.commands;

import java.util.regex.Matcher;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Basic class for basic operation with message Realisate by an
 *  {@link IbasicCommand}.
 */
public class FoncteurCommand extends Command {

    /**
     * The object who treat thet command.
     */
    private IbasicCommand foncteurpp;

    /**
     * The constructor.
     * @param foncteur The object who treat process the message.
     */
    public FoncteurCommand(final IbasicCommand foncteur) {
        super(foncteur.getName(),
                foncteur.getDescription(),
                foncteur.getHelp()
            );

        this.foncteurpp = foncteur;
        this.pattern = foncteur.patternConstructor();
    }

    @Override
    public final void onMessageReceived(@Nonnull final MessageReceivedEvent event) {
        // create the matcher
        Matcher m = pattern.matcher(event.getMessage().getContentRaw());
        if (this.foncteurpp.checkMatch(m)) {
            this.foncteurpp.setRunnable(m, event);
            Thread thread = new Thread(this.foncteurpp);
            thread.start();
        }
    }

}

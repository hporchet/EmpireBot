package fr.ttanesque.empirebot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

import fr.ttanesque.empirebot.commands.utils.*;
import fr.ttanesque.empirebot.commands.admin.*;
import fr.ttanesque.empirebot.commands.FoncteurCommand;

import java.io.IOException;

/**
 * the main class who start the bot and register the listener.
 */
public final class Bot {

    /**
     * The main methods.
     * @param args the argument
     * @throws LoginExceptionnot connected
     * @throws InterruptedException InterruptedException
     * @throws IOException config...
     */
    public static void main(final String[] args)
    throws LoginException, InterruptedException, IOException {
        //start bot 1 shard
        JDA jda = JDABuilder
                .createDefault(Config.getConfigInstance().getInitConf().getToken())
                .build();
        // we wait the jda isready
        jda.addEventListener(new FoncteurCommand(new DelTimeMessage()));
        jda.addEventListener(new Clean());
        jda.addEventListener(new Google());
        jda.addEventListener(new StackOverflow());
        jda.addEventListener(new MoveMsg());
        jda.addEventListener(new QuoteMsg());
    }

    /**
     * Basic constructor.
     */
    private Bot() {
    }
}

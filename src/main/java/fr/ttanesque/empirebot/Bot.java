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
     */
    public static void main(final String[] args) {
        //start bot 1 shard
        JDA jda = null;
        try {
            jda = JDABuilder.createDefault(Config.getConfigInstance().getInitConf().getToken()).build();
        } catch (LoginException | IOException e) {
            e.printStackTrace();
        }

        assert jda != null;
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

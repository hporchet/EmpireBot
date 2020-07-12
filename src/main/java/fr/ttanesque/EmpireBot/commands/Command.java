package fr.ttanesque.EmpireBot.commands;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Pattern;

/**
 * Generic command class who contains the needed information for an basic command.
 */
public abstract class Command extends ListenerAdapter {

    /**
     * The name of the commands.
     */
    private String name;

    /**
     * The description of the command string.
     */
    private String description;

    /**
     * The help string.
     */
    private String help;

    /**
     * The regext pattern;
     */
    protected Pattern pattern;

    /**
     * The basic constructor for an commands.
     * @param name the name of this commands
     * @param description the description message
     * @param help the help message
     */
    public Command(String name, String description, String help) {
        this.name = name;
        this.description = description;
        this.help = help;
    }

    /**
     * Construct an new regex Pattern stock in pattern.
     * @param regex the regex string
     */
    protected void constructRegex(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    /**
     * Getter of name value.
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of description value.
     * @return the description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of help value.
     * @return the help of the command
     */
    public String getHelp() {
        return help;
    }
}

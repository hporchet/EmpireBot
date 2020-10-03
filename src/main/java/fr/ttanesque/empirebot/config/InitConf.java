package fr.ttanesque.empirebot.config;

/**
 * this object contain the basic config for start the bot.
 */
public class InitConf {
    /**
     * The token of the bot.
     */
    private String token;
    /**
     * The prefix the bot use.
     */
    private String prefix;

    /**
     * The constructor.
     * @param token the token
     * @param prefix the command prefix
     */
    public InitConf(final String token, final String prefix) {
        this.token = token;
        this.prefix = prefix;
    }

    /**
     * The default constructor.
     */
    public InitConf() {
    }

    /**
     * Getter of token.
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter of token.
     * @param newToken the new token
     */
    public void setToken(final String newToken) {
        this.token = newToken;
    }

    /**
     * Getter of prefix.
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Setter of prefix.
     * @param newPrefix the new prefix
     */
    public void setPrefix(final String newPrefix) {
        this.prefix = newPrefix;
    }
}

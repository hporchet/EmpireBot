package fr.ttanesque.empirebot.commands.treatment;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.MessageEmbed.Footer;
import net.dv8tion.jda.api.entities.MessageEmbed.Thumbnail;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The abstract class who describe the treatment of an command define
 * methods for the extended classes to use.
 */
public abstract class MessageTreatment {
    /**
     * The matcher who contains the command.
     */
    private Matcher text;

    /**
     * The message.
     */
    private MessageReceivedEvent eventMessage;

    /**
     * The constructor.
     *
     * @param text the matcher get with regex in {@link fr.ttanesque.empirebot.commands.Command}
     * @param eventMessage the message.
     */
    public MessageTreatment(final Matcher text, final MessageReceivedEvent eventMessage) {
        this.text = text;
        this.eventMessage = eventMessage;
    }

    /**
     * Verify if the channel is text type and the user is not a bot.
     * @return the truth
     */
    protected boolean checkBotChannel() {
        return eventMessage.isFromType(ChannelType.TEXT)
        && !eventMessage.getAuthor().isBot();
    }

    /**
     * Verify if user has the needed permission.
     * @param permissionEnum the permission
     * @return the truth
     */
    protected boolean checkUserPemission(final Permission permissionEnum) {
        return eventMessage.getGuild()
                        .getSelfMember()
                        .hasPermission(permissionEnum);
    }

    /**
     * Get specific Id from an url discordapp.
     * @param url the url
     * @param i => 2 guild 3 channel 4 message
     * @return id
     */
    private String geIdFromURL(final String url, final int i) {
        Pattern pattern = Pattern.compile("https:\\/\\/discordapp\\.com\\/channels\\/(\\d*)\\/(\\d*)\\/(\\d*)");
        Matcher match = pattern.matcher(url);

        if (match.matches()) {
            return match.group(i);
        }
        return "";
    }

    /**
     * Get specific Id from an url discordapp.
     * @param url the url
     * @param i => 2 guild 3 channel 4 message
     * @return id
     */
    public boolean isDiscordAppUrl(final String url) {
        Pattern pattern = Pattern.compile("https:\\/\\/discordapp\\.com\\/channels\\/(\\d*)\\/(\\d*)\\/(\\d*)");
        Matcher match = pattern.matcher(url);

        return match.matches();
    }

    public HttpURLConnection connection() {
        // create 
        URL get = null;
        try {
            get = new URL(this.getText().group(0));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // connect
        assert get != null;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) get.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert con != null;
        try {
            con.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        return con;
    }

    /**
     * Send embed message from existing message.
     * @param channel destination
     * @param action description of action for footer
     * @param userCall the user who call the command
     * @param userAuthor the author of message needed
     * @param title title of the embed message
     * @param desc the description
     */
    protected void embedSend(final MessageChannel channel, final String action,
    final User userCall, final User userAuthor, final String desc) {
        MessageEmbed embed = new MessageEmbed(
                "",
                userAuthor.getName(),
                desc,
                EmbedType.RICH,
                OffsetDateTime.now(),
                5057728,
                new Thumbnail(userAuthor.getEffectiveAvatarUrl(),
                                userAuthor.getEffectiveAvatarUrl(),
                                100,
                                100
                ),
                null,
                null,
                null,
                new Footer(action + userCall.getName(),
                        userCall.getEffectiveAvatarUrl(),
                        userCall.getEffectiveAvatarUrl()
                        ),
                null,
                null
                );

                channel.sendMessage(embed).queue();
    }

    /**
     * Send error message.
     * @param text the error text
     */
    protected void error(final String text) {
        MessageChannel channel = this.eventMessage.getChannel();

        channel.sendMessage(text).submit().thenCompose(
            (m) ->  m.delete().submitAfter(30, TimeUnit.SECONDS)
        ).whenComplete((s, error) -> {
            if (error != null) 
                error.printStackTrace();
        }) ;
    }

    /**
     * Get the guild id from an discord app url
     * @return id
     */
    public String getGuildId(final String url) {
        return this.geIdFromURL(url, 2);
    }

    /**
     * Get the channel id from an discord app url
     * @return id
     */
    public String getChannelId(final String url) {
        return this.geIdFromURL(url, 3);
    }

    /**
     * Get the message id from an discord app url
     * @return id
     */
    public String getMessageId(final String url) {
        return this.geIdFromURL(url, 3);
    }

    /**
     * Getter for the matcher.
     * @return the matcher
     */
    public Matcher getText() {
        return text;
    }

    /**
     * Getter for the Message.
     * @return the message
     */
    public MessageReceivedEvent getEventMessage() {
        return eventMessage;
    }

    /**
     * Set the text command.
     * @param text new matcher
     */
    protected void setText(Matcher text) {
        this.text = text;
    }

    /**
     * Set the new eventMessage.
     * @param eventMessage the new event
     */
    protected void setEventMessage(MessageReceivedEvent eventMessage) {
        this.eventMessage = eventMessage;
    }

    
}

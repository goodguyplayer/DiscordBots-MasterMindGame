import models.FileReader;
import models.ListenerCommands;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

/**
 * Main class, the one that calls the bot and make it run.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:
    - Class created

 */
public class Main {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader("discordtoken");

        DiscordApi api = new DiscordApiBuilder().setToken(fileReader.getFileContent())
                .setIntents(Intent.GUILDS, Intent.GUILD_MESSAGES, Intent.DIRECT_MESSAGES)
                .login().join();

        api.addListener(new ListenerCommands());


    }

}

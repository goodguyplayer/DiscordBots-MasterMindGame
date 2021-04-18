import models.DiscordBotSettings;
import models.FileReader;
import models.ListenerCommands;
import org.javacord.api.DiscordApi;

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
        DiscordBotSettings discordBotSettings = new DiscordBotSettings();

        DiscordApi api = discordBotSettings.botSettings(fileReader.getFileContent());


        api.addListener(new ListenerCommands());


    }

}

import models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;

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
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Starting bot DiscordBots-MasterMindGame");

        FileReader fileReader = new FileReader("discordtoken");

        logger.info("Succesfully loaded token");

        DiscordBotSettings discordBotSettings = new DiscordBotSettings();
        DiscordApi api = discordBotSettings.botSettings(fileReader.getFileContent());

        logger.info("Succesfully set settings");

        api.addListener(new ListenerCommands());

        logger.info("Succesfully set listener");

    }

}

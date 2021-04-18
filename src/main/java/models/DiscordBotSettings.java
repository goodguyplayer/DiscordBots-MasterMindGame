package models;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

/**
 * A class made to simply put the bot specifications in a different place. Nothing special, really.
 * Mostly to help me visualize and isolate any issues.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:

 */
public class DiscordBotSettings {

    /**
     * Define settings and log bot in.
     * @param token required token to log bot.
     * @return api for further usage
     */
    public DiscordApi botSettings(String token) {
        return new DiscordApiBuilder()
                    .setToken(token)
                    .setIntents(Intent.GUILDS, Intent.GUILD_MESSAGES, Intent.DIRECT_MESSAGES)
                    .login()
                    .join();
    }
}

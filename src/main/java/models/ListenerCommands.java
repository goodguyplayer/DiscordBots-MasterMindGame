package models;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * Class made to list the commands the bot responds to.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:
    - Implemented MessageCreaterListener
    - Added method commandHelp

 */
public class ListenerCommands implements MessageCreateListener {

    /**
     * Main method, list possible combinations and replies to them adequately.
     * @param event
     * Nathan (goodguyplayer)
     */
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

    }

    /**
     * method meant to describe what the help command does.
     * @param event
     * @author Nathan (goodguyplayer)
     */
    private void commandHelp(MessageCreateEvent event){
        if (event.getMessageContent().equalsIgnoreCase("!help")){
            new MessageBuilder()
                    .append("Well hello there! \n")
                    .append("You called for help, so I'll teach ")
                    .append("you", MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                    .append(" how to use me!")
                    .append("(lewd)\n", MessageDecoration.SPOILER)
                    .append("\n")
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Help list")
                            .setDescription("!start - Will start the game\n" +
                                    "!tutorial - Will give you the tutorial of the game \n" +
                                    "!help - Will summon this again!\n" +
                                    ""))
                    .append("Hope that helps!")
                    .send(event.getChannel());
        }
    }
}

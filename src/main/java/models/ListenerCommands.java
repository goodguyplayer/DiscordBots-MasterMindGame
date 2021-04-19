package models;

import DAO.GameDAO;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * Class made to list the commands the bot responds to.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.2
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.2.:
    - Created method commandInvite
    - Added commandInvite to onMessageCreate
    - Created method commandTutorial
    - Added commandTutorial to onMessageCreate
    - Made bot ignore his own messages

Version 0.1.:
    - Forgot to add commandHelp to onMessageCreate

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
        if (!event.getMessageAuthor().isYourself()){
            commandHelp(event);
            commandInvite(event);
            commandTutorial(event);
        }
    }

    /**
     * method meant to describe what the help command does.
     * @param event
     * @author Nathan (goodguyplayer)
     */
    private void commandHelp(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!help")) {
            new MessageBuilder()
                    .append("Well hello there! \n")
                    .append("You called for help, so I'll teach ")
                    .append("you", MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                    .append(" how to use me! ")
                    .append("(lewd)\n", MessageDecoration.SPOILER)
                    .append("Hope that helps!")
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Help list")
                            .setDescription("!start - Will start the game\n" +
                                    "!tutorial - Will give you the tutorial of the game \n" +
                                    "!help - Will summon this again!\n" +
                                    "!invite - I'll send you a link to invite me (so I can rule the world more!)" +
                                    ""))
                    .send(event.getChannel());
        }
    }

    /**
     * method meant to represent the invite command. Says the link
     * Todo.: Find a way to automatically add the link instead of having to type it in here
     * @param event
     * @author Nathan (goodguyplayer)
     */
    private void commandInvite(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!invite")) {
            new MessageBuilder()
                    .append("Here's my link! \n")
                    .append("https://discord.com/oauth2/authorize?client_id=833148362408722442&scope=bot&permissions=0")
                    .send(event.getChannel());

        }
    }

    /**
     * method meant to represent the tutorial command. Says the tutorial for the game
     * @param event
     */
    private void commandTutorial(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!tutorial")) {
            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Tutorial for Mastermind")
                            .setDescription("If you want, https://en.wikipedia.org/wiki/Mastermind_(board_game)")
                            .addField("How does it work?", "First, I generate a random code of 4 letters. From a to f. It's your job to guess those letters! \n" +
                                    "Don't worry about sending the letter in caps or not, I'll understand it. I just won't consider letters that go after f or numbers. Or extra letters \n" +
                                    "Once you get the code right, I'll show your score. Golf score rules, the lower the better!")
                            .addField("Commands.:","!try{attempt} - I'll take the \"attempt\" and check it. remember, only between a and f (including those), 4 letters. \n" +
                                    "!currscore - I'll show your current punctuation!\n" +
                                    "!giveup - Disappointing. Dishonorable. Disgusting.\n")
                    )
                    .send(event.getChannel());
        }

    }

    /**
     * Creates a game session and store in the database
     * @param event
     */
    private void commandCreateGameSession(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!start")) {

            // TODO.: See if instance doesn't already exist.

            new MessageBuilder()
                    .append("Starting a game session...")
                    .append("I'm thinking of a word, 4 letters.")
                    .append("Can you guess it?")
                    .send(event.getChannel());
            Player player = new Player(event.getMessageAuthor().getName(), event.getServer().toString());
            GameSession gameSession = new GameSession();
            gameSession.createSession(player);

            GameDAO dao = new GameDAO();
            dao.create(gameSession);

        }
    }

    private void commandTestCode(MessageCreateEvent event) {
        if (event.getMessageContent().contains("!try{")) {

        }
    }

    // TODO.: Create range of allowed characters to test
    private boolean isAllowedCharacters(String text) {
        if (!text.contains("a")) {

        }
        return false;
    }

    // TODO.: See if instance is in database

    // TODO.: Get instance in database

    // TODO.: Remove instance from database
}

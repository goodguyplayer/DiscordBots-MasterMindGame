package models;

import DAO.GameDAO;
import DAO.ScoreDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.List;
import java.util.Locale;

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

    private static final Logger logger = LogManager.getLogger(ListenerCommands.class);

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
            commandCreateGameSession(event);
            commandTestCode(event);
            commandGiveUp(event);
            commandGetScore(event);
        }
    }

    /**
     * method meant to describe what the help command does.
     * @param event
     * @author Nathan (goodguyplayer)
     */
    private void commandHelp(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("~help")) {
            new MessageBuilder()
                    .append("Well hello there! \n")
                    .append("You called for help, so I'll teach ")
                    .append("you", MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                    .append(" how to use me! ")
                    .append("(lewd)\n", MessageDecoration.SPOILER)
                    .append("Hope that helps!")
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Help list")
                            .setDescription("~start - Will start the game\n" +
                                    "~tutorial - Will give you the tutorial of the game \n" +
                                    "~help - Will summon this again!\n" +
                                    "~invite - I'll send you a link to invite me (so I can rule the world more!)\n" +
                                    "~score - Get to see the scoreboard (of your server)! Only the most recent games are saved" +
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
        if (event.getMessageContent().equalsIgnoreCase("~invite")) {
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
        if (event.getMessageContent().equalsIgnoreCase("~tutorial")) {
            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Tutorial for Mastermind")
                            .setDescription("If you want, https://en.wikipedia.org/wiki/Mastermind_(board_game)")
                            .addField("How does it work?", "First, I generate a random code of 4 letters. From a to f. It's your job to guess those letters! \n" +
                                    "Don't worry about sending the letter in caps or not, I'll understand it. I just won't consider letters that go after f or numbers. Or extra letters \n" +
                                    "Once you get the code right, I'll show your score. Golf score rules, the lower the better!")
                            .addField("Commands.:","~try{attempt} - I'll take the \"attempt\" and check it. remember, only between a and f (including those), 4 letters. \n" +
//                                    "~currscore - I'll show your current punctuation!\n" +
                                    "~giveup - Disappointing. Dishonorable. Disgusting.\n")
                    )
                    .send(event.getChannel());
        }

    }

    /**
     * Creates a game session and store in the database
     * @param event
     */
    private void commandCreateGameSession(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("~start")) {
            ScoreDAO score = new ScoreDAO();
            if (!isInScoreDatabase(event.getMessageAuthor().getName())) {
                score.create(new Player(event.getMessageAuthor().getName(), event.getServer().toString()));
            }

            if(isInGameDatabase(event.getMessageAuthor().getName())){
                new MessageBuilder()
                        .append("Whoops, you are already in a game, silly\n")
                        .append("Use ~try{code} to test a code instead!")
                        .send(event.getChannel());
            } else {
                new MessageBuilder()
                        .append("Starting a game session...\n")
                        .append("I'm thinking of a word, 4 letters.\n")
                        .append("Can you guess it?")
                        .send(event.getChannel());
                Player player = new Player(event.getMessageAuthor().getName(), event.getServer().toString());
                GameSession gameSession = new GameSession();
                gameSession.createSession(player);

                GameDAO dao = new GameDAO();
                dao.create(gameSession);
            }
        }
    }

    private void commandTestCode(MessageCreateEvent event) {
        if (event.getMessageContent().contains("~try{")) {
            ScoreDAO score = new ScoreDAO();

            if (isInGameDatabase(event.getMessageAuthor().getName())){
                GameDAO dao = new GameDAO();
                GameSession game = dao.get("name LIKE '%"+ event.getMessageAuthor().getName() +"%'").get(0);
                if(game.playerAttempt(extractCode(event.getMessageContent()))) {
                    new MessageBuilder()
                            .append("Congrats! You got the code!\n")
                            .append("Your score is " + game.getPlayer().getScore())
                            .send(event.getChannel());
                    dao.delete(game);
                    if(isScoreLower(game.getPlayer())) {
                        score.update(game.getPlayer());
                    }
                } else {
                    new MessageBuilder()
                            .append("Hmm... " + extractCode(event.getMessageContent()) + " is not quite right, " + game.getPlayer().getName() + "...\n")
                            .append("There are " + game.getCorrect() + " letters that matches the code and are in the right position.\n")
                            .append("There are " + game.getIncode() + " letters that matches the code but are in the wrong position.\n")
                            .append("There are " + game.getWrong() + " letters that don't match the code.")
                            .send(event.getChannel());
                    dao.update(game);

                }
            }

        }
    }

    private void commandGiveUp(MessageCreateEvent event) {
        if (event.getMessageContent().contains("~giveup")) {
            if (isInGameDatabase(event.getMessageAuthor().getName())){
                GameDAO dao = new GameDAO();
                GameSession game = dao.get("name LIKE '%"+ event.getMessageAuthor().getName() +"%'").get(0);
                new MessageBuilder()
                        .append("I'm sad at the fact that you're giving up.\n")
                        .append("But hey, hopefully you'll play again")
                        .send(event.getChannel());
                dao.delete(game);
            }

        }
    }

    private void commandGetScore(MessageCreateEvent event) {
        ScoreDAO score = new ScoreDAO();
        if (event.getMessageContent().contains("~score")) {
            new MessageBuilder()
                    .append("Here's the server score!")
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Score")
                            .setDescription(scoreBoard(score.get("guild LIKE '%" +  event.getServer() +"%'"))))
                    .send(event.getChannel());
        }

    }

    private String scoreBoard(List<Player> players) {
        String toreturn = "";
        for (Player player: players) {
            toreturn += "User.: " + player.getName() + " - Score.: " + player.getScore() + "\n";
        }
        return toreturn;
    }

    private String extractCode(String text) {
        String toreturn = text.replace("~try{", "");
        toreturn = toreturn.replace("}", "");
        return toreturn.toLowerCase();
    }

    private char convertNumberChar(int number) {
        return (char)number;
    }

//    private boolean checkLetterMatch(String attempt, char letter) {
//        for (int i = 0; i < 4; i++){
//            if (attempt.charAt(i) == letter) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // TODO.: Create range of allowed characters to test
//    private boolean isAllowedCharacters(String text) {
//        for (int i = 103; i < 123; i++) {
//            if (checkLetterMatch()) {
//
//            }
//        }
//        return false;
//    }

    /**
     * Sees whether user is in the game database.
     * @param name name of user
     * @return true to whether they are, false if they are not.
     */
    private boolean isInGameDatabase(String name) {
        GameDAO dao = new GameDAO();
        List<GameSession> game = dao.get("name LIKE '%"+ name +"%'");
        if (game.size() != 0) {
            return true;
        }
        return false;
    }


    private boolean isInScoreDatabase(String name) {
        ScoreDAO dao = new ScoreDAO();
        List<Player> players = dao.get("name LIKE '%"+ name +"%'");
        if (players.size() != 0) {
            return true;
        }
        return false;
    }

    private boolean isScoreLower(Player player) {
        ScoreDAO dao = new ScoreDAO();
        List<Player> players = dao.get("name LIKE '%"+ player.getName() +"%'");
        if (players.get(0).getScore() > player.getScore()) {
            return true;
        }
        return false;
    }
}

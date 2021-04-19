package models;

import java.util.ArrayList;

/**
 * class meant to represent a game session of the game Mastermind.
 * It should ask the player for an input of 4 letters, compare with the code and loop it until both are correct.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.1
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.:
    - Class created

 */
public class GameSession {
    Player player;
    Code code = new Code();
    int correct = 0;
    int incode = 0;
    int wrong = 0;

    public String playerAttempt(String attempt) {
        player.increaseScore();
        return verifyAttempt();
    }

    private String verifyAttempt() {
        if (isCodeBroken()) {
            return "Congratulations, you broke the code!\n" +
                    "Original code.: " + code.getCode() +"\n" +
                    "Attempts.: " + player.getScore();
        } else {
            String toreturn = "There are " + correct + "  letters that matches the code and are in the right position.\n" +
                    "There are " + incode + "  letters that matches the code  but are in the wrong position.\n" +
                    "There are " + wrong + "  letters that doesn't match the code.";
            resetCounters();
            return toreturn;
        }
    }

    /**
     * Method that gets correct, incode and wrong, printing each to the screen
     * @author Nathan (goodguyplayer)
     */
    private void printCurrent() {
        System.out.println("There are " + correct + "  letters that matches the code and are in the right position.");
        System.out.println("There are " + incode + "  letters that matches the code  but are in the wrong position.");
        System.out.println("There are " + wrong + "  letters that doesn't match the code.");
    }

    /**
     * Method that reset the counters
     * @author Nathan (goodguyplayer)
     */
    private void resetCounters() {
        correct = 0;
        incode = 0;
        wrong = 0;
    }

    /**
     * Method that sees whether correct == 4
     * @author Nathan (goodguyplayer)
     */
    private boolean isCodeBroken() {
        if (correct == 4){
            return true;
        }
        return false;
    }

    public Player getPlayer(){
        return player;
    }

    public String getPlayerName(){
        return player.getName();
    }
}

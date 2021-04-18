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

    public GameSession(String name) {
        player = new Player(name);
    }

    public String playerAttempt(String attempt) {
        player.setAttempt(attempt);
        player.increaseScore();
        verifyLetters();
        return verifyAttempt();
    }

    private String verifyAttempt() {
        if (isCodeBroken()) {
            return "Congratulations, you broke the code!\n" +
                    "Original code.: " + code.getCode() +"\n" +
                    "Attempts.: " + player.getAttempt();
        } else {
            String toreturn = "There are " + correct + "  letters that matches the code and are in the right position.\n" +
                    "There are " + incode + "  letters that matches the code  but are in the wrong position.\n" +
                    "There are " + wrong + "  letters that doesn't match the code.";
            resetCounters();
            return toreturn;
        }
    }

    /**
     * method that checks whether the letter at the given position matches the letter in the code.
     * @param position int that defines the position.
     * @return true if the letter match, false if it doesn't.
     * @author Nathan (goodguyplayer)
     */
    private boolean checkLetterMatch(int position) {
        if (player.getAttempt().charAt(position) == code.getCode().charAt(position)) {
            return true;
        }
        return false;
    }

    /**
     * method that checks whether the letter at the given position is in the code.
     * @param position
     * @return
     * @author Nathan (goodguyplayer)
     */
    private boolean checkInCode(int position) {
        if (code.getCode().indexOf(player.getAttempt().charAt(position)) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Method that verifies the letters based on whether they match, are in the right position or not.
     * @author Nathan (goodguyplayer)
     */
    private void verifyLetters() {
        for (int i = 0; i < 4; i++) {
            if(checkLetterMatch(i)) {
                correct++;
            } else if(checkInCode(i)) {
                incode++;
            } else {
                wrong++;
            }
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

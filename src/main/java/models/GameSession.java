package models;

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
    Code code;
    int correct = 0;
    int incode = 0;
    int wrong = 0;

    /**
     * Obtain player attempt, increase score.
     * @param attempt
     * @return true if correct, false if wrong.
     */
    public boolean playerAttempt(String attempt) {
        player.increaseScore();
        verifyLetters(attempt);
        return (correct == 4) ? true : false;
    }

    /**
     * Load a session as a class.
     * @param player
     * @param code
     * @return
     */
    public GameSession loadSession(Player player, Code code) {
        this.player = player;
        this.code = code;
        return this;
    }

    public void createSession(Player player) {
        this.player = player;
        this.code = new Code();
    }

    private boolean checkLetterMatch(int position, String attempt) {
        if (attempt.charAt(position) == code.getCode().charAt(position)) {
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
    private boolean checkInCode(int position, String attempt) {
        if (code.getCode().indexOf(attempt.charAt(position)) != -1) {
            return true;
        }
        return false;
    }

    private void verifyLetters(String attempt) {
        for (int i = 0; i < 4; i++) {
            if(checkLetterMatch(i, attempt)) {
                correct++;
            } else if(checkInCode(i, attempt)) {
                incode++;
            } else {
                wrong++;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

    public Code getCode() {
        return code;
    }

    public int getCorrect() {
        return correct;
    }

    public int getIncode() {
        return incode;
    }

    public int getWrong() {
        return wrong;
    }
}

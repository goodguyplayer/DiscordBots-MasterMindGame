package models;

import java.util.ArrayList;
import java.util.HashMap;

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
    String localcode;
    int correct = 0;
    int incode = 0;
    int wrong = 0;
    HashMap<Character, Integer> correctLetter = new HashMap<Character, Integer>();
    ArrayList<Integer> matchingPositions = new ArrayList<Integer>();

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
        if (attempt.charAt(position) == code.getCode().charAt(position)){
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
        if (code.getCodeAppearances().get(attempt.charAt(position)) != null) {
            return true;
        }
        return false;
    }

    private boolean wasLetterSeenBefore(char letter){
        if (correctLetter.get(letter) != null) {
            return true;
        }
        return false;
    }

    private void updateCorrectLetter(char letter){
        if (correctLetter.get(letter) != null){
            correctLetter.put(letter, correctLetter.get(letter) + 1);
        } else {
            correctLetter.put(letter, 1);
        }
    }

    private void calculateInCode(int position, String attempt) {
        if (!wasLetterSeenBefore(attempt.charAt(position))){
            if (code.getCodeAppearances().get(attempt.charAt(position)) > incode) {
                incode++;
            }
        }
    }

    private ArrayList<Integer> listMatchingPositions(String code, String attempt){
        for (int i = 0; i < 4; i ++) {
            if (code.charAt(i) == attempt.charAt(i)) {
                matchingPositions.add(i);
            }
        }
        return matchingPositions;
    }

    private String removeFromStringCorrect(String source, String change){
        String output = "";
        for (int i = 0; i < 4; i++) {
            if ((source.charAt(i) != change.charAt(i))) {
                output += source.charAt(i);
            }
        }
        return output;
    }

    private void calculateCorrect(String remainder) {
        correct = 4 - remainder.length();
    }

    private String removeFromStringMatching(String source, String change) {
        String output = "";

        for (char letter: source.toCharArray()) {
            if (change.indexOf(letter) == -1) {
                output += letter;
                wrong++;
            } else {
                if (code.getCodeAppearances().get(letter) > incode + 1) {
                    incode++;
                }
            }
        }
        return output;
    }

    private void calculateWrong() {
        if (correct != 4) {
            wrong = 4 - (incode + correct);
        }
    }

    //TODO - Make this better to look at.
    //TODO - Remove unused methods
    private void verifyLetters(String attempt){
        String localattempt = attempt;
        localcode = removeFromStringCorrect(code.getCode(), attempt);
        localattempt = removeFromStringCorrect(attempt, code.getCode());
        System.out.println("Localattempt - " + localattempt);
        calculateCorrect(localattempt);
        localattempt = removeFromStringMatching(localcode, localattempt);

        //calculateRest(localattempt, localcode);
    }

    private void oldverifyLetters(String attempt) {
        for (int i = 0; i < 4; i++) {
            if(checkInCode(i, attempt)) {
                if (checkLetterMatch(i, attempt)){
                    updateCorrectLetter(attempt.charAt(i));
                    correct++;
                } else {
                    calculateInCode(i, attempt);
                }
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

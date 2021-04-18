package models;

/**
 * class meant to represent a game session of the game Mastermind.
 * It should ask the player for an input of 4 letters, compare with the code and loop it until both are correct.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.1
 * @since 2021-04-17
 */

/*
Changelog.:

Version 0.1.:
    - Added method verifyLetters()
    - Added method printCurrent()
    - Added method resetCounters()
    - Added method isCodeBroken()

Version 0.:
    - Class created
    - Added vars
    - Added gameSession method
    - Added checkLetterMatch method
    - Added checkInCode method
 */
public class Session {
    Player player = new Player();
    Code code = new Code();
    int correct = 0;
    int incode = 0;
    int wrong = 0;


    /**
     * Represents a game session of mastermind.
     * Ask player input, check to see if it matches. If it does, exit and sout the score.
     * Else, add to score and repeat same steps.
     * @author Nathan (goodguyplayer)
     */
    public void gameSession(){
        boolean status = true;
        while (status){
            System.out.println("Please input 4 letters. (Excess letters will be ignored)");
            player.setAttempt();
            player.increaseScore();

            verifyLetters();
            printCurrent();

            if(isCodeBroken()){
                status = false;
            }

            resetCounters();

        }
        System.out.println("Congratulations, you got the code.");
        System.out.println("Your score was of " + player.getScore());

    }

    /**
     * method that checks whether the letter at the given position matches the letter in the code.
     * @param position int that defines the position.
     * @return true if the letter match, false if it doesn't.
     * @author Nathan (goodguyplayer)
     */
    private boolean checkLetterMatch(int position){
        if (player.getAttempt().charAt(position) == code.getCode().charAt(position)){
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
    private boolean checkInCode(int position){
        if (code.getCode().indexOf(player.getAttempt().charAt(position)) != -1){
            return true;
        }
        return false;
    }

    /**
     * Method that verifies the letters based on whether they match, are in the right position or not.
     * @author Nathan (goodguyplayer)
     */
    private void verifyLetters(){
        for (int i = 0; i < 4; i++) {
            if(checkLetterMatch(i)){
                correct++;
            } else if(checkInCode(i)){
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
    private void printCurrent(){
        System.out.println("There are " + correct + "  letters that matches the code and are in the right position.");
        System.out.println("There are " + incode + "  letters that matches the code  but are in the wrong position.");
        System.out.println("There are " + wrong + "  letters that doesn't match the code.");
    }

    /**
     * Method that reset the counters
     * @author Nathan (goodguyplayer)
     */
    private void resetCounters(){
        correct = 0;
        incode = 0;
        wrong = 0;
    }

    /**
     * Method that sees whether correct == 4
     * @author Nathan (goodguyplayer)
     */
    private boolean isCodeBroken(){
        if (correct == 4){
            return true;
        }
        return false;
    }

}

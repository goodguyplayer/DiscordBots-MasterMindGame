package models;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class meant to represent the code instance in the game session.
 * Should randomly generate a code of 4 letter on initialization and return it whenever called.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0
 * @since 2021-04-17
 */

/*
Changelog.:

Version 0.1.:
    - Changed random number generation range to 102.
        - Limit it to 6 letters, like how it was in the original game with colors..

Version 0.:
    - class created
    - Added var
    - Added constructor
    - Added method for random number generation
    - Added method to convert number to letter.
    - Added method to create the code
    - Added getter

 */
public class Code {
    String code = new String();


    /**
     * Constructor for Code. Once initialized, the constructor will call the method to generate a random code.
     * @author Nathan (goodguyplayer)
     */
    public Code() {
        code = createCode();
    }

    /**
     * method that generates random numbers, from 97 to 102, ascii.
     * @return A random int that goes from 97 to 102
     * @author Nathan (goodguyplayer)
     */
    private int randomNumber(){
        //return ThreadLocalRandom.current().nextInt(97, 122 + 1);
        return ThreadLocalRandom.current().nextInt(97, 102 + 1);
    }

    /**
     * method that converts a random number to char.
     * The range is to lowercase letters only.
     * @param randomnumber A random number generated by randomNumber
     * @return the char which the number represents
     * @author Nathan (goodguyplayer)
     */
    private char convertNumberChar(int randomnumber){
        return (char)randomnumber;
    }

    /**
     * method that creates the code of 4 letters, using the methods randomNumber and convertNumberChar.
     * @return {String} code
     */
    private String createCode(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            str.append(convertNumberChar(randomNumber()));
        }
        return str.toString();
    }

    /**
     * Getter for code
     * @return {String} code
     */
    public String getCode() {
        return code;
    }
}

package models;

import java.util.Scanner;

/**
 * Class meant to represent the player. It should, at minimum, hold the score and attempts.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.3
 * @since 2021-04-17
 */

/*
Changelog.:

Version 0.3
    - Removed variable attempt and methods related to it.

Version 0.2
    - Added variable string name
    - Added getter for name

Version 0.1
    - updated setAttempt.
        - Format string to lowercase

Version 0
    - Added variables
    - Added constructor
    - Added increaseScore
    - Added getScore
    - Added getAttempt
    - Added setAttempt
 */
public class Player {
    private String name;
    private String guild;
    private int score;

    /**
     * Constructor. Once class is created, start the score and attempt.
     * @author Nathan (goodguyplayer)
     */
    public Player(String name, String guild) {
        score = 0;
        this.name = name;
        this.guild = guild;
    }

    public void PlayerFull(String name, String guild, int score) {
        this.name = name;
        this.guild = guild;
        this.score = score;
    }

    /**
     * method that simply increases the score by 1, to avoid touching the variable directly.
     * @author Nathan (goodguyplayer)
     */
    public void increaseScore() {
        score +=1;
    }

    /**
     * getter that returns the score of the player
     * @return {int} score  The score of the player.
     * @author Nathan (goodguyplayer)
     */
    public int getScore() {
        return score;
    }

    /**
     * getter for the player name
     * @return
     */
    public String getName() {
        return name;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }
}

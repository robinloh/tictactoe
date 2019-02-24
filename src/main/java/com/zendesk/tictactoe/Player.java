package com.zendesk.tictactoe;

/**
 * Player.java
 * This class stores the properties of each player in the game.
 */
public class Player {

    // Name of the player
    private String name;

    // Player's piece - Assumed to be single character only
    private String piece;

    // Number of successful turns taken by the player.
    private int noOfTurns;


    // Constructor - Setting up Player's name, piece and playerIndex number.
    public Player(String name, int i) {
        this.name = name;
        setPiece(i);
        this.noOfTurns = 0;
    }

    /**
     * Returns the name of the player
     * @return name - The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's piece
     * @return piece - The player's piece
     */
    public String getPiece() {
        return piece;
    }

    /**
     * Returns the number of successful turns taken by the player
     * @return noOfTurns - The number of successful turns taken by the player.
     */
    public int getTurns() {
        return this.noOfTurns;
    }

    /**
     * Increments the number of successful turns taken by the player by one.
     */
    public void updateTurn() {
        this.noOfTurns++;
    }

    /**
     * Sets player's piece according to the playerIndex.
     * Assumption: The piece can only contain a single character.
     *
     * @param i
     */
    private void setPiece(int i) {

        switch (i) {

            case 1:
                this.piece = "x";
                break;

            case 2:
                this.piece = "o";
                break;
        }

    }

}

package com.zendesk.tictactoe;

/**
 *  Box.java
 *  This class stores the properties of each cell in the board.
 */
public class Box {

    // Determines if the cell has been updated before. Default - false
    private boolean isUpdated;

    // Records the player's piece (or the index by default if the box was untouched)
    private String piece;


    // Constructor for Box objects - by default.
    public Box(int index) {
        this.piece = Integer.toString(index);
        this.isUpdated = false;
    }

    // Constructor for Box objects - with the player's piece.
    public Box(String piece) {
        this.piece = piece;
        this.isUpdated = true;
    }

    /**
     * Returns if the box is updated with a player's piece
     * @return true if the box is updated, else false
     */
    public boolean getIsUpdated() {
        return isUpdated;
    }

    /**
     * Returns the piece stored in the box.
     * @return piece - The piece stored in the box.
     */
    public String getPiece() {
        return piece;
    }
}

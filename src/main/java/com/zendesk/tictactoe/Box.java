package com.zendesk.tictactoe;

/**
 *  Box.java
 *  This class stores the properties of each cell in the board.
 */
public class Box {

    private boolean isUpdated;      // Determines if the cell has been updated before. Default - false
    private String piece;           // Records the player's piece (or the index by default if the box was untouched)


    public Box(int index) {
        this.piece = Integer.toString(index);
        this.isUpdated = false;
    }

    public Box(String piece) {
        this.piece = piece;
        this.isUpdated = true;
    }

    public boolean getIsUpdated() {
        return isUpdated;
    }

    public String getPiece() {
        return piece;
    }
}

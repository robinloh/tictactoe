package com.zendesk.tictactoe;

public class Box {

    private boolean isUpdated;
    private char piece;

    public Box(int index) {
        this.piece = (char) (index + '0');
        this.isUpdated = false;
    }

    public Box(char piece) {
        this.piece = piece;
        this.isUpdated = true;
    }

    public boolean getIsUpdated() {
        return isUpdated;
    }

    public char getPiece() {
        return piece;
    }
}

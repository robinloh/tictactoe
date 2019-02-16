package com.zendesk.tictactoe;

public class Player {

    private String name;

    private char piece;

    public Player(String name, int i) {
        this.name = name;
        setPiece(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getPiece() {
        return piece;
    }

    private void setPiece(int i) {

        switch (i) {

            case 1:
                this.piece = 'X';
                break;

            case 2:
                this.piece = 'O';
                break;
        }

    }

}

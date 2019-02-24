package com.zendesk.tictactoe;

public class Player {

    private String name;
    private String piece;
    private int noOfTurns;

    public Player(String name, int i) {
        this.name = name;
        setPiece(i);
        this.noOfTurns = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPiece() {
        return piece;
    }

    public int getTurns() {
        return this.noOfTurns;
    }

    public void updateTurn() {
        this.noOfTurns++;
    }

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

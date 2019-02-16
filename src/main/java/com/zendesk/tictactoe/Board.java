package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.BoxValueException;
import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;

public class Board {

    private int boardSize = 3;
    private int emptyBoxes = (int) Math.pow(boardSize, 2);
    private static Box[][] board;

    public Board() {
        initBoard(boardSize);
    }

//    public Board(int boardSize) {
//        this.boardSize = boardSize;
//        initBoard(boardSize);
//    }

    private void initBoard(int boardSize) {

        board = new Box[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Box(i * boardSize + j + 1);
            }
        }

        printBoard();
    }

    public void printBoard() {

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {

                System.out.print(" " + board[i][j].getPiece() + " ");

                if (j != this.boardSize - 1) {
                    System.out.print(Utilities.VERTICAL_BORDER);
                }

            }

            if (i != this.boardSize - 1) {
                System.out.print(Utilities.HORIZONTAL_BORDER);
            } else {
                System.out.println();
            }
        }

    }

    public int getEmptyBoxes() {
        return emptyBoxes;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public static Box[][] getBoard() {
        return board;
    }

    public static void setBoard(Box[][] board) {
        Board.board = board;
    }

    public void updateBoard(int value, char piece) throws IndexOutOfBoundException, BoxValueException {

        if (value < 1 || value > Math.pow(boardSize, 2)) {
            throw new IndexOutOfBoundException(Messages.INVALID_INDEX_MESSAGE);
        }

        int xValue = ((value - 1) / boardSize);
        int yValue = (value + boardSize - 1) % boardSize;

        if (!board[xValue][yValue].getIsUpdated()) {
            board[xValue][yValue] = new Box(piece);
            emptyBoxes--;
        } else {
            throw new BoxValueException(Messages.BOX_VALUE_EXISTS_MESSAGE);
        }

    }
}

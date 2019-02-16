package com.zendesk.tictactoe;

public class Checker {

    public static boolean checkBoard(Board board) {
        return checkFull(board) || checkHorizontal(board) || checkVertical(board) || checkDiagonal(board);
    }

    private static boolean checkFull(Board board) {
        return board.getEmptyBoxes() < 1;
    }

    private static boolean checkDiagonal(Board board) {
        return false;
    }

    private static boolean checkVertical(Board board) {
        return false;
    }

    private static boolean checkHorizontal(Board board) {
        return false;
    }
}

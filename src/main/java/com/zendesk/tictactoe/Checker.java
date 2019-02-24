package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;
import com.zendesk.tictactoe.exceptions.InvalidReferenceException;

public class Checker {

    private static final int N = 3;

    private static int index;
    private static int row;
    private static int column;

    public Checker(int index) {
        this.index = index;
    }

    public boolean checkBoard(String piece, Board board) throws InvalidReferenceException {
        return checkDiagonal(this.index, piece, board) || checkFullBoard(board) || checkHorizontal(this.index, piece, board) || checkVertical(this.index, piece, board);
    }

    private boolean checkFullBoard(Board board) {
        return board.getEmptyBoxes() < 1;
    }

    private boolean checkHorizontal(int index, String piece, Board board) {
        row = (index - 1) / board.getBoardSize();
        column = (index - 1) % board.getBoardSize();

        int leftLimit = 0;

        if (column >= N) {
            leftLimit = column - N + 1;
        }

        int rightLimit = (column + N - 1 >= board.getBoardSize() ? board.getBoardSize() - 1 : column + N - 1);

        while (leftLimit <= rightLimit) {

            String retrievedPiece = board.getBoard()[row][leftLimit].getPiece();

            if (retrievedPiece.equals(piece)) {

                int count = 1;
                int tempIndex = leftLimit + 1;

                while (tempIndex <= rightLimit) {

                    retrievedPiece = board.getBoard()[row][tempIndex].getPiece();

                    if (count == N) {
                        return true;
                    } else if (!(retrievedPiece.equals(piece))) {
                        return false;
                    } else {
                        count++;
                    }

                    tempIndex++;
                }
            }

            leftLimit++;
        }

        return false;
    }

    private boolean checkVertical(int index, String piece, Board board) {

        row = (index - 1) / board.getBoardSize();
        column = (index - 1) % board.getBoardSize();

        int leftLimit = 0;

        if (row >= N) {
            leftLimit = row - N + 1;
        }

        int rightLimit = (row + N - 1 >= board.getBoardSize() ? board.getBoardSize() - 1 : row + N - 1);

        while (leftLimit <= rightLimit) {

            String retrievedPiece = board.getBoard()[leftLimit][column].getPiece();

            if (retrievedPiece.equals(piece)) {

                int count = 1;
                int tempIndex = leftLimit + 1;

                while (tempIndex <= rightLimit) {

                    retrievedPiece = board.getBoard()[tempIndex][column].getPiece();

                    if (count == N) {
                        return true;
                    } else if (!(retrievedPiece.equals(piece))) {
                        return false;
                    } else {
                        count++;
                    }

                    tempIndex++;
                }
            }

            leftLimit++;
        }

        return false;

    }

    private boolean checkDiagonal(int index, String piece, Board board) {
        return checkLeftDiagonal(index, piece, board) || checkRightDiagonal(index, piece, board);
    }

    private boolean checkLeftDiagonal(int index, String piece, Board board) {
        row = (index - 1) / board.getBoardSize();
        column = (index - 1) % board.getBoardSize();

        int refRow = row + Math.min(board.getBoardSize() - 1 - row, column);
        int refCol = column - Math.min(board.getBoardSize() - 1 - row, column);

        while (refRow >= 0 && refCol < board.getBoardSize()) {

            String retrievedPiece = board.getBoard()[refRow][refCol].getPiece();

            if (retrievedPiece.equals(piece)) {

                int count = 1;
                int tempRefRow = refRow - 1;
                int tempRefCol = refCol + 1;

                while (tempRefRow >= 0 && tempRefCol < board.getBoardSize()) {

                    retrievedPiece = board.getBoard()[tempRefRow][tempRefCol].getPiece();

                    if (count == N) {
                        return true;
                    } else if (!(retrievedPiece.equals(piece))) {
                        return false;
                    } else {
                        count++;
                    }

                    tempRefRow--;
                    tempRefCol++;

                }
            }

            refRow--;
            refCol++;
        }

        return false;
    }


    private boolean checkRightDiagonal(int index, String piece, Board board) {
        row = (index - 1) / board.getBoardSize();
        column = (index - 1) % board.getBoardSize();

        int refRow = row - Math.min(column, row);
        int refCol = column - Math.min(column, row);

        while (refCol < board.getBoardSize() && refRow < board.getBoardSize()) {

            String retrievedPiece = board.getBoard()[refRow][refCol].getPiece();

            if (retrievedPiece.equals(piece)) {

                int count = 1;
                int tempRefRow = refRow + 1;
                int tempRefCol = refCol + 1;

                while (tempRefRow < board.getBoardSize() && tempRefCol < board.getBoardSize()) {

                    retrievedPiece = board.getBoard()[tempRefRow][tempRefCol].getPiece();

                    if (count == N) {
                        return true;
                    } else if (!(retrievedPiece.equals(piece))) {
                        return false;
                    } else {
                        count++;
                    }

                    tempRefRow++;
                    tempRefCol++;

                }
            }

            refRow++;
            refCol++;
        }

        return false;
    }

}

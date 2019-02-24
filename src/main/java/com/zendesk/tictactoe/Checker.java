package com.zendesk.tictactoe;

/**
 * Checker.java
 * Checks if the board has satisfied a winning / draw criteria
 */
public class Checker {

    // Length of the pieces in a line to win
    private static final int N = 3;

    // Latest piece placed on the board
    private static String latestPiece;

    // Row number with respect to the latestPieceIndex
    private static int row;

    // Column number with respect to the latestPieceIndex
    private static int column;


    // Constructor - with latestPieceIndex and boardSize
    public Checker(int latestPieceIndex, String latestPiece, int boardSize) {
        this.latestPiece = latestPiece;
        this.row = (latestPieceIndex - 1) / boardSize;
        this.column = (latestPieceIndex - 1) % boardSize;
    }

    /**
     * Determine if the board satisfies a winning / draw criteria.
     * N consecutive pieces in a horizontal, vertical or diagonal row.
     *
     * @param board - Board at the current state
     * @return true - if the board satisifies a winning / draw criteria, else false
     */
    public boolean checkBoard(Board board) {

        return checkFullBoard(board)
                || checkHorizontal(board)
                || checkVertical(board)
                || checkDiagonal(board);
    }

    /**
     * Determines if the board is in the draw state, where all the boxes are occupied with player's pieces.
     *
     * @param board - Board in the current state
     * @return true if the board is in the draw state, else false.
     */
    private boolean checkFullBoard(Board board) {
        return board.getEmptyBoxes() < 1;
    }

    /**
     * Checks if the horizontal row satisfies the winning criteria.
     *
     * @param board - Board in the current state
     * @return true if the horizontal row satisfies the winning criteria, else false
     */
    private boolean checkHorizontal(Board board) {
        return checkLine(column, board, Utilities.HORIZONTAL);
    }

    /**
     * Checks if the vertical row satisfies the winning criteria.
     *
     * @param board - Board in the current state
     * @return true if the vertical row satisfies the winning criteria, else false
     */
    private boolean checkVertical(Board board) {
        return checkLine(row, board, Utilities.VERTICAL);
    }

    /**
     * Logically checks if a horizontal / vertical row satisfies the winning criteria.
     *
     * @param reference - The row/column which is not varying throughout the checking process.
     * @param board - The board in the current state.
     * @param type - The horizontal / vertical row being used.
     * @return true if the line satisfies the winning criteria, else false.
     */
    private boolean checkLine(int reference, Board board, String type) {

        // Determine the left limit of the line with the index given
        int leftLimit = 0;

        // Limit the left limit so that it satisfies the N elements in a row criteria
        if (reference >= N) {
            leftLimit = reference - N + 1;
        }

        // Determine the right limit of the line with the index given, considering the boundaries of the board
        int rightLimit = (reference + N - 1 >= board.getBoardSize() ? board.getBoardSize() - 1 : reference + N - 1);

        // Iterate through each box in the line within the left and right limit
        while (leftLimit <= rightLimit) {

            // Retrieve the piece in the box
            String retrievedPiece = retrievePiece(board, row, column, leftLimit, type);

            // Check if the piece is the same as the latest piece placed by the last player.
            if (retrievedPiece.equals(latestPiece)) {

                // Count the number of consecutive last pieces.
                int count = 1;

                // Have a separate index to tabulate count.
                int tempIndex = leftLimit + 1;

                // Iterate through the line to tabulate count.
                while (tempIndex <= rightLimit) {

                    // Retrieve the piece from the tempIndex
                    retrievedPiece = retrievePiece(board, row, column, tempIndex, type);

                    // Check if the consecutive piece is still the same as latestPiece
                    if (!(retrievedPiece.equals(latestPiece))) {

                        // The consecutive piece is different, so it cannot form a winning line.
                        return false;

                    } else {

                        // Increment count as there is a consecutive piece which is the same as latestPiece
                        count++;

                    }

                    // Check if the count is the same as N.
                    if (count == N) {

                        // There is a winning line.
                        return true;
                    }

                    // Increment tempIndex to continue checking the potential winning line.
                    tempIndex++;
                }
            }

            // Increment leftLimit to continue checking the line.
            leftLimit++;

        }

        return false;
    }

    /**
     * Retrieve the piece with the given row, column, leftLimit and type.
     *
     * @param board - Board of the current state
     * @param row - Row of the given piece
     * @param column - Column of the given piece
     * @param reference - Reference of the given line
     * @param type - Horizontal / Vertical Line
     * @return piece
     */
    private String retrievePiece(Board board, int row, int column, int reference, String type) {

        switch (type) {

            case Utilities.HORIZONTAL:
                return board.getBoard()[row][reference].getPiece();

            case Utilities.VERTICAL:
                return board.getBoard()[reference][column].getPiece();

            case Utilities.DIAGONAL_DOWN:
            case Utilities.DIAGONAL_UP:
                return board.getBoard()[row][column].getPiece();

        }

        return null;
    }

    /**
     * Checks if either left or right diagonal satisfies the winning criteria.
     *
     * @param board - Board of the current state
     * @return true if the left or right diagonal satisfies the winning criteria, else false.
     */
    private boolean checkDiagonal(Board board) {

        // Check both left and right diagonals satisfy the winnign criteria.
        return checkLeftDiagonal(board) || checkRightDiagonal(board);
    }

    /**
     * Checks if the left diagonal satisfy the winning criteria.
     *
     * @param board - Board of the current state.
     * @return true if the left diagonal satisfy the winning criteria, else false.
     */
    private boolean checkLeftDiagonal(Board board) {

        int refRow = row + Math.min(board.getBoardSize() - 1 - row, column);
        int refCol = column - Math.min(board.getBoardSize() - 1 - row, column);

        while (refRow >= 0 && refCol < board.getBoardSize()) {

            String retrievedPiece = retrievePiece(board, refRow, refCol, 0, Utilities.DIAGONAL_DOWN);

            if (retrievedPiece.equals(this.latestPiece)) {

                int count = 1;
                int tempRefRow = refRow - 1;
                int tempRefCol = refCol + 1;

                while (tempRefRow >= 0 && tempRefCol < board.getBoardSize()) {

                    retrievedPiece = retrievePiece(board, tempRefRow, tempRefCol, 0, Utilities.DIAGONAL_DOWN);

                    if (!(retrievedPiece.equals(this.latestPiece))) {
                        return false;
                    } else {
                        count++;
                    }

                    if (count == N) {
                        return true;
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

    /**
     * Checks if the right diagonal satisfy the winning criteria.
     *
     * @param board - Board of the current state.
     * @return true if the right diagonal satisfy the winning criteria, else false.
     */
    private boolean checkRightDiagonal(Board board) {

        int refRow = row - Math.min(column, row);
        int refCol = column - Math.min(column, row);

        while (refCol < board.getBoardSize() && refRow < board.getBoardSize()) {

            String retrievedPiece = retrievePiece(board, refRow, refCol, 0, Utilities.DIAGONAL_UP);

            if (retrievedPiece.equals(this.latestPiece)) {

                int count = 1;
                int tempRefRow = refRow + 1;
                int tempRefCol = refCol + 1;

                while (tempRefRow < board.getBoardSize() && tempRefCol < board.getBoardSize()) {

                    retrievedPiece = retrievePiece(board, tempRefRow, tempRefCol, 0, Utilities.DIAGONAL_UP);

                    if (!(retrievedPiece.equals(this.latestPiece))) {
                        return false;
                    } else {
                        count++;
                    }

                    if (count == N) {
                        return true;
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

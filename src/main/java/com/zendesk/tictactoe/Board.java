package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.BoxPieceException;
import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;

public class Board {

    // Size of the board
    private int boardSize;

    // Number of empty boxes left in the board
    private int emptyBoxes;

    // 2D array representation of the board
    private static Box[][] board;


    // Constructor - with board size
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.emptyBoxes = this.boardSize * this.boardSize;
        initBoard(boardSize);
    }

    /**
     * Return the number of empty boxes left in the board
     *
     * @return emptyBoxes - Number of empty boxes left in the board
     */
    public int getEmptyBoxes() {
        return this.emptyBoxes;
    }

    /**
     * Return the board size
     * @return boardSize - board size
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Return the current board
     *
     * @return board - The current board
     */
    public static Box[][] getBoard() {
        return board;
    }

    /**
     * Sets index to each box in the board. (By default)
     *
     * @param boardSize - Size of the board
     */
    private void initBoard(int boardSize) {

        board = new Box[boardSize][boardSize];

        // Set the indexes to each box in the board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Box(i * boardSize + j + 1);
            }
        }

        // Print board after initialising the indexes
        printBoard();
    }

    /**
     *  Prints the entire board in the current state.
     */
    public void printBoard() {

        for (int i = 0; i < this.boardSize; i++) {

            // Print out the entire row
            String row = getRowFromBoard(board[i]);
            System.out.println(row);

            // Print the horizontal border
            printHorizontalBorder(i, row);
        }
    }

    /**
     * Print horizontal border given the row number and the row in String format.
     *
     * @param i - Row number
     * @param row - Row in String format
     */
    private void printHorizontalBorder(int i, String row) {

        if (i != this.boardSize - 1) {
            for (int j = 0; j < row.length() - 1; j++) {
                System.out.print(Utilities.HORIZONTAL);
            }
        }

        System.out.println();
    }

    /**
     * Retrieves the contents in each cell for a row and output as a String.
     *
     * @param boxes - The row given to retrieve each box.
     * @return row - The row of the cell in String format.
     */
    private String getRowFromBoard(Box[] boxes) {

        String row = "";

        for (int j = 0; j < this.boardSize; j++) {

            // Append the contents in each box (with spaces beside) to the row
            row += Utilities.SPACE + getBoxPieceInString(boxes[j]) + Utilities.SPACE;

            // Print vertical space or vertical border.
            if (j != this.boardSize - 1) {
                row += Utilities.VERTICAL;
            } else {
                row += Utilities.SPACE;
            }

        }
        return row;
    }

    /**
     * Generates the box piece in String format.
     * Every index are made of the same odd length.
     * Every piece (if it is updated in the box) is made centralised.
     * Assumption: Player's piece is a single character.
     *
     * @param box - Box to retrieve the piece
     * @return pieceInString - The box piece in String format.
     */
    private String getBoxPieceInString(Box box) {

        // The box piece in String format - for output
        String pieceInString = "";

        // Determine the length of the value in box
        int pieceLength = (int) Math.log10(Math.pow(this.boardSize, 2));

        // Make the length of the index even, for the loop conditions below.
        if (pieceLength % 2 == 1) {
            pieceLength++;
        }

        // Determine if the box is updated.
        if (!box.getIsUpdated()) {

            // The index is in the box, instead of the player's piece.
            int tempIndex = (int) Math.log10(Double.parseDouble(box.getPiece()));

            // Append zeros in front of the indexes so that every index is of the same odd length
            for (int k = tempIndex; k < pieceLength; k++) {
                pieceInString += "0";
            }

            // Append the index of the box to the string
            pieceInString += box.getPiece();

        } else {

            // One of the player's piece is in the box, instead of the index by default.
            pieceInString = box.getPiece();

            // Centralise the player's piece, by putting spaces beside the piece.
            for (int k = 0; k < pieceLength / 2; k++) {
                pieceInString = Utilities.SPACE + pieceInString + Utilities.SPACE;
            }

        }
        return pieceInString;
    }

    /**
     * Update the board in the respective box based on the current player's piece and the given index.
     *
     * @param index - Given index by the player
     * @param piece - Current player's piece
     * @throws IndexOutOfBoundException - The index given by the player cannot be found on the board.
     * @throws BoxPieceException - A piece exists in the box (i.e. the box is already updated)
     */
    public void updateBoard(int index, String piece) throws IndexOutOfBoundException, BoxPieceException {

        // Throw IndexOutOfBoundException if the index cannot be found on the board.
        if (index < 1 || index > Math.pow(this.boardSize, 2)) {
            throw new IndexOutOfBoundException(Messages.INVALID_INDEX_MESSAGE);
        }

        int rowValue = ((index - 1) / this.boardSize);
        int columnValue = (index + this.boardSize - 1) % this.boardSize;

        // Determine if the box is updated
        if (!board[rowValue][columnValue].getIsUpdated()) {

            // Update the player's piece on the board
            board[rowValue][columnValue] = new Box(piece);

            // Reduce the number of available boxes on the board
            this.emptyBoxes--;

        } else {

            // Throw BoxPieceException if the box has been updated with a player's piece.
            throw new BoxPieceException(Messages.BOX_VALUE_EXISTS_MESSAGE);

        }

    }
}

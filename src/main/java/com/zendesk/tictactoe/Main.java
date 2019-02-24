package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.BoxPieceException;
import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;
import com.zendesk.tictactoe.exceptions.InvalidReferenceException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Main.java
 *  This class is the start of the program.
 */
public class Main {

    private static final int NO_OF_PLAYERS = 2;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * This is the main method for the class.
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.startGame();           // Starts the game proper.
        sc.close();
    }

    /**
     * This method provides the logical flow of the whole game.
     */
    private void startGame() {
        Player[] players = initPlayers();   // Initialise all players.
        Board board = initBoard();          // Set up a clean board for the game.

        int playerIndex = 0;                // Player index for taking turns throughout the whole game.

        while (true) {

            Player player = players[playerIndex];   // Retrieve player based on playerIndex.

            try {
                int boardIndex = getBoardIndexFromPlayer(player);   // Get board index from player.

                board.updateBoard(boardIndex, player.getPiece());   // Update board based on board index.
                board.printBoard();     // Prints board at current state.

                Checker checker = new Checker(boardIndex);   // Define checker object to check winning state.

                // Check if any winning criteria is satisfied
                if (checker.checkBoard(player.getPiece(), board)) {

                    // Draw if the board size is even and each player took equal number of turns
                    if (board.getBoardSize() % 2 == 0 && player.getTurns() * NO_OF_PLAYERS == board.getBoardSize()) {
                        System.out.printf(Messages.DRAW_MESSAGE);
                        break;
                    } else {
                        System.out.printf(Messages.WINNING_MESSAGE, player.getName());  // The player wins the game
                        break;
                    }
                }

                player.updateTurn();    // Increment number of turns for the current player by 1.
                playerIndex = (playerIndex + 1) % NO_OF_PLAYERS;    // Switch to the next player's turn in the queue.

            } catch (InputMismatchException ie) {
                System.out.println(Messages.INVALID_INDEX_MESSAGE);     // Invalid board index retrieved from player.
                sc.nextLine();
            } catch (IndexOutOfBoundException | BoxPieceException | InvalidReferenceException be) {
                System.out.println(be.getMessage());    // Other invalid exceptions encountered during the game process.
            }
        }
    }

    /**
     * Takes in board index as input from current player.
     * @param player - Current player
     * @return the board index as integer.
     */
    private int getBoardIndexFromPlayer(Player player) {
        System.out.printf(Messages.PLACE_PIECE, player.getName(), player.getPiece());
        return sc.nextInt();
    }

    /**
     * Sets up all players for the game and output to Player 1D-array.
     * @return Player[] array
     */
    private Player[] initPlayers() {

        Player[] players = new Player[NO_OF_PLAYERS];

        for (int i = 1; i <= NO_OF_PLAYERS; i++) {
            System.out.printf(Messages.ENTER_NAME, i);
            players[i-1] = new Player(sc.nextLine(), i);
        }

        return players;
    }

    /**
     * Creates a clean board of board size at least size 3.
     * @return Board - Clean board of at least size 3
     */
    private Board initBoard() {

        int boardSize;

        while (true) {

            try {

                System.out.println(Messages.INPUT_BOARD_SIZE);

                // Keep getting input until the board size is at least 3.
                while ((boardSize = sc.nextInt()) < 3) {
                    System.out.println(Messages.INVALID_BOARD_SIZE_RANGE_MESSAGE);
                    System.out.println(Messages.INPUT_BOARD_SIZE);
                }

                break;

            } catch (InputMismatchException ie) {
                System.out.println(Messages.INVALID_BOARD_SIZE_MESSAGE);
                sc.nextLine();
            }
        }

        return new Board(boardSize);    // Create a clean board of board size at least size 3.
    }

}


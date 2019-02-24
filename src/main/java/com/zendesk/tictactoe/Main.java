package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.BoxPieceException;
import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;

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
     *
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {

        // Remove static from all methods.
        Main main = new Main();

        // Starts the game proper.
        main.startGame();

        // Close the Scanner object.
        sc.close();
    }

    /**
     * This method provides the logical flow of the whole game.
     */
    private void startGame() {

        // Initialise all players.
        Player[] players = initPlayers();

        // Set up a clean board for the game.
        Board board = initBoard();

        // Player index for taking turns throughout the whole game.
        int playerIndex = 0;

        while (true) {

            // Retrieve player based on playerIndex.
            Player player = players[playerIndex];

            try {

                // Get latest piece index from player.
                int latestPieceIndex = getBoardIndexFromPlayer(player);

                // Update board based on latest piece index.
                board.updateBoard(latestPieceIndex, player.getPiece());

                // Prints board at current state.
                board.printBoard();

                // Define checker object to check winning state.
                Checker checker = new Checker(latestPieceIndex, player.getPiece(), board.getBoardSize());

                // Check if any winning criteria is satisfied
                if (checker.checkBoard(board)) {

                    // Check if the game is a draw, instead of a player winning
                    if (board.getBoardSize() % 2 == 0 && player.getTurns() * NO_OF_PLAYERS == board.getBoardSize()) {

                        // Draw if the board size is even and each player took equal number of turns
                        System.out.printf(Messages.DRAW_MESSAGE);
                        break;

                    } else {

                        // The player wins the game
                        System.out.printf(Messages.WINNING_MESSAGE, player.getName());
                        break;
                    }
                }

                // Increment number of turns for the current player by 1.
                player.updateTurn();

                // Switch to the next player's turn in the queue.
                playerIndex = (playerIndex + 1) % NO_OF_PLAYERS;

            } catch (InputMismatchException ie) {

                // Invalid board index retrieved from player.
                System.out.println(Messages.INVALID_INDEX_MESSAGE);
                sc.nextLine();

            } catch (IndexOutOfBoundException | BoxPieceException be) {

                // Other invalid exceptions encountered during the game process.
                System.out.println(be.getMessage());
            }
        }
    }

    /**
     * Takes in board index as input from current player.
     *
     * @param player - Current player
     * @return the board index as integer.
     */
    private int getBoardIndexFromPlayer(Player player) {
        System.out.printf(Messages.PLACE_PIECE, player.getName(), player.getPiece());
        return sc.nextInt();
    }

    /**
     * Sets up all players for the game and output to Player 1D-array.
     *
     * @return Player[] array - All players
     */
    private Player[] initPlayers() {

        // Define a data structure to store all players.
        Player[] players = new Player[NO_OF_PLAYERS];

        // Enter player's name and sets up the player in the players array.
        for (int i = 1; i <= NO_OF_PLAYERS; i++) {
            System.out.printf(Messages.ENTER_NAME, i);
            players[i-1] = new Player(sc.nextLine(), i);
        }

        return players;
    }

    /**
     * Creates a clean board of board size at least size 3.
     *
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

                // Board size given is not valid. e.g. String input
                System.out.println(Messages.INVALID_BOARD_SIZE_MESSAGE);
                sc.nextLine();
            }
        }

        // Create a clean board of board size at least size 3.
        return new Board(boardSize);
    }

}


package com.zendesk.tictactoe;

import com.zendesk.tictactoe.exceptions.BoxValueException;
import com.zendesk.tictactoe.exceptions.IndexOutOfBoundException;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 *
 */
public class Main {

    private static final int NO_OF_PLAYERS = 2;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.startGame();
        sc.close();
    }

    private void startGame() {
        Player[] players = initPlayers();
        Board board = new Board();
        int playerIndex = 0;

        while (!Checker.checkBoard(board)) {
            Player player = players[playerIndex];
            while (true) {
                try {
                    System.out.printf(Messages.PLACE_PIECE, player.getName(), player.getPiece());
                    int index = sc.nextInt();
                    board.updateBoard(index, player.getPiece());
                    break;
                } catch (InputMismatchException ie) {
                    System.out.println(Messages.INVALID_INDEX_MESSAGE);
                    sc.nextLine();
                } catch (IndexOutOfBoundException | BoxValueException be) {
                    System.out.println(be.getMessage());
                }
            }
            playerIndex = (playerIndex + 1) % NO_OF_PLAYERS;
            board.printBoard();
        }
    }

    private Player[] initPlayers() {

        Player[] players = new Player[NO_OF_PLAYERS];

        for (int i = 1; i <= NO_OF_PLAYERS; i++) {

            System.out.printf(Messages.ENTER_NAME, i);
            players[i-1] = new Player(sc.nextLine(), i);
        }

        return players;
    }


}


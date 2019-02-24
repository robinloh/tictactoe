package com.zendesk.tictactoe;

/**
 * Messages.java
 * Contains the string representation of the output messages in the game.
 */
public class Messages {

    // For initial game setup
    public static final String PLACE_PIECE = "%s, choose a box to place an '%s' into:\n";
    public static final String ENTER_NAME = "Enter name for Player %d:\n";
    public static final String INPUT_BOARD_SIZE = "Enter size of board:";

    // For ultimate game outcome
    public static final String WINNING_MESSAGE = "Congratulations %s! You have won.\n";
    public static final String DRAW_MESSAGE = "This match is a draw.\n";

    // For any error exception messages
    public static final String INVALID_INDEX_MESSAGE = "\nSORRY, INVALID INDEX, PLEASE TRY AGAIN\n";
    public static final String INVALID_BOARD_SIZE_RANGE_MESSAGE = "\nSORRY, THE BOARD SIZE SHOULD BE AT LEAST 3. PLEASE TRY AGAIN\n";
    public static final String BOX_VALUE_EXISTS_MESSAGE = "\nSORRY, THE VALUE ALREADY EXISTS ACCORDING TO THE INDEX GIVEN. PLEASE TRY AGAIN\n";
    public static final String INVALID_BOARD_SIZE_MESSAGE = "\nSORRY, THE BOARD SIZE IS INVALID. PLEASE TRY AGAIN\n";
}

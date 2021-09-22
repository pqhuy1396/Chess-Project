package chess.view;

import chess.model.Board;

/**
 * Interface for the different output methods
 */
public interface Views  {
    /**
     * Method for displaying the board
     * @param board the board to be displayed
     */
    void print(Board board);

    /**
     * Prints a message
     */
    void printMessage(String s);
}

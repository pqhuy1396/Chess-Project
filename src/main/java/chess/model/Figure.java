package chess.model;
/**
 * Figure Model Interface that represents a Figure
 */
public interface Figure {
    int type =0;

    /**
     * Returns the Symbol of the Figure
     * @return Returns the specific String Code for the Symbol
     */
    String print();

    /**
     * Returns the Colour of the Figure
     * @return Returns the Colour. White: True ; Black: False
     */
     boolean isColour() ;

    /**
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
     boolean checkMove(int[] order);

    /**
     * The Unicode String Method
     * @return The Unicode for the current Figure
     */
    String print1();

    /**
     * Returns the Type of the Figure
     *
     * @return Returns a specific Number representing this Figure
     */
    int getType();

    /**
     * Sets the Type of the Figure
     * @param i number of type
     */
    void setType(int i);

    /**
     * Checks if a special Move is valid
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return Returns true when the move is valid
     */
    boolean specialMove(int[] order, Board board);

    /**
     * method to get the value of a figure
     * @return returns the value in int
     */
    int getValue();
}

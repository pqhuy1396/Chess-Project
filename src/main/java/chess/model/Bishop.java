package chess.model;
/**
 * Bishop Model Class that represents a Bishop
 */
public class Bishop implements Figure {
    private boolean colour;
    public int type = 2;
    /**
     * Creates a new Bishop Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public Bishop(boolean colour){
        this.colour = colour;
    }

    /**
     * Constructor for a copy of another bishop figure
     * @param bishop the figure to be copied
     */
    public Bishop(Figure bishop){
        this.colour=bishop.isColour();
        this.type=bishop.type;

    }

    /**
     * Returns the Symbol of the Bishop
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print(){
        if (colour){
            return "\u265D";
        }
        else {
            return "\u2657";
        }
    }

    /**
     * Returns the Type of the Figure
     * @return Returns a specific Number representing this Figure
     */
    @Override
    public int getType(){
        return this.type;
    }

    /**
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    @Override
    public boolean checkMove(int[] order) {
        //System.out.println("ich bin läufer");
        return Math.abs(order[0] - order[2]) == Math.abs(order[1] - order[3]);
    }
    /**
     * Returns the Colour of the Figure
     * @return Returns the Colour. White: True ; Black: False
     */
    @Override
    public boolean isColour(){
        return colour;
    }

    /**
     * There are no special Moves for this Figure
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return false because there are no special moves
     */
    @Override
    public boolean specialMove(int[] order, Board board) {
        return false;
    }

    /**
     * Sets the type
     * @param i the new Type
     */
    @Override
    public void setType(int  i) {
        this.type = i;
    }

    public void setColour(boolean colour){
        this.colour=colour;
    }
}
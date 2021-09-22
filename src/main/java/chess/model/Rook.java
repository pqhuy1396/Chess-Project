package chess.model;
/**
 * Rook Model Class that represents a Rook
 */
public class Rook implements Figure {
    private boolean colour;
    int type = 10;
    String Symbol;
    int value = 50;
    /**
     * Creates a new Rook Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public Rook(boolean colour){
        this.colour = colour;
    }

    /**
     * Returns the Symbol of the Rook
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print(){
        if (colour){

            //return "\u265C";
            return "R";
        }
        else {
            //return"\u2656";
            return "r";
        }
    }
    @Override
    public String print1(){
        if (!colour){

            return "\u265C";

        }
        else {
            return"\u2656";

        }
    }

    /**
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    @Override
    public boolean checkMove(int[] order) {
        //System.out.println("ich bin turm");
        return order[0] == order[2] || order[1] == order[3];
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
     * Returns the Type of the Figure
     * @return Returns a specific Number representing this Figure
     */
    @Override
    public int getType() {
        return this.type;
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

    /**
     * method to get the value of the rook
     * @return value in int
     */
    @Override
    public int getValue(){
        return value;
    }
}

package chess.model;
/**
 * Knight Model Class that represents a Knight
 */
public class Knight implements Figure{
    private boolean colour;
     int type = 3;
     int value = 30;
    String Symbol;

     /**
     * Creates a new Knight Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public Knight(boolean colour){
        this.colour = colour;
    }

    /**
     * Constructor for a copy of another knight figure
     * @param knight the figure to be copied
     */
    public Knight(Knight knight){
        this.colour=knight.colour;
        this.type=knight.type;
    }

    /**
     * Returns the Symbol of the Knight
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print(){
        if (colour){
            //return "\u265E";
            return "N";
        }
        else {
            //return "\u2658";
            return "n";
        }
    }
    @Override
    public String print1(){
        if (!colour){
            return "\u265E";

        }
        else {
            return "\u2658";

        }
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
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    @Override
    public boolean checkMove(int[] order) {
        //System.out.println("ich bin Springer");
        if ((order[1]-2==order[3] || order[1]+2==order[3]) && (order[0]-1==order[2] || order[0]+1==order[2])) {
            return true;
        } else{
            return (order[1] - 1 == order[3] || order[1] + 1 == order[3]) && (order[0] - 2 == order[2] || order[0] + 2 == order[2]);
        }
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

    /**
     * method to set the colour of the knight
     * @param colour wanted colour
     */
    public void setColour(boolean colour){
        this.colour=colour;
    }

    /**
     * method to get the value of the knight
     * @return the value in int
     */
    @Override
    public int getValue(){
        return value;
    }
}

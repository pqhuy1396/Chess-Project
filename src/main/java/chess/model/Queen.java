package chess.model;
/**
 * Queen Model Class that represents a Queen
 */
public class Queen implements Figure {
    int type = 5;
    private boolean colour;

    /**
     * Creates a new Queen Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public Queen(boolean colour){
        this.colour = colour;
    }

    /**
     * Constructor for a copy of another queen figure
     * @param queen the figure to be copied
     */
    public Queen(Queen queen){
        this.type=queen.type;
        this.colour=queen.colour;
    }
    /**
     * Returns the Symbol of the Queen
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print(){
        if (colour){
            return "\u265b";
        }
        else {
            return"\u2655";
        }
    }

    /**
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    @Override
    public boolean checkMove(int[] order) {
        //System.out.println("ich bin queen");
        if(order[0]==order[2]||order[1]==order[3]) {
            return true;
        }else{
            return Math.abs(order[0] - order[2]) == Math.abs(order[1] - order[3]);
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
}

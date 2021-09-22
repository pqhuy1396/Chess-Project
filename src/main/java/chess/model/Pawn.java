package chess.model;
/**
 * Pawn Model Class that represents a Pawn
 */
public class Pawn implements Figure {
boolean colour;
int type = 0;
int value = 10;
    int typeCopy = 0;
    String Symbol;

    /**
     * Creates a new Pawn Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public Pawn(boolean colour){
        this.colour = colour;
    }

    /**
     * Constructor for a copy of another pawn figure
     * @param pawn the figure to be copied
     */
    public Pawn(Pawn pawn){
        this.colour=pawn.colour;
        this.type=pawn.type;
    }

    /**
     * Returns the Symbol of the Pawn
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print() {
        if(colour) {
            //return "\u265F";
            return "P";
        }else {
            //return "\u2659";
            return "p";
        }
    }
    @Override
    public String print1() {
        if(!colour) {
            return "\u265F";

        }else {
            return "\u2659";

        }
    }

    /**
     * Checks if the Move is valid
     * @param order The complete Move expressed in an Array Form
     * @return Returns true when the move is valid
     */
    @Override
    public boolean checkMove(int[] order){
        if (colour&&order[0]==order[2]&&order[1]-1==order[3]) {
            return true;
        }
        else if (!colour&&order[0]==order[2]&&order[1]+1==order[3]) {
            return true;
        }
        else if (checkDouble(order)) {
            type = 20;
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * Checks whether the given move is a valid move going 2 spaces foreward
     * @param order The complete Move expressed in an Array Form
     * @return returns true if the move is valid
     */
    public boolean checkDouble(int[] order){
        if(colour&&order[0]==order[2]&&order[1]-2==order[3] && order[1] == 6){
            return true;
        }else{
            return !colour && order[0] == order[2] && order[1] + 2 == order[3] && order[1] == 1;
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
     * Sets the type
     * @param i the new Type
     */
    @Override
    public void setType(int  i) {
        this.type = i;
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
     * Special Moves for the Pawn includes capturing enemy figures, en-passant and pawn ascension
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return true if the special move is valid or if its just a normal move
     */
    @Override
    public boolean specialMove(int[] order, Board board) {
        if(captureWhite(order, board)) {
            return true;
            //capture black
        } else if(captureBlack(order, board)) {
            return true; }
        else if(enPassantWhite(order, board)) {
            return true;
            //capture black
        } else if(enPassantBlack(order, board)) {
            return true;
        } else if(board.squares[order[3]][order[2]].isEmpty()){
            return checkMove(order);
        }else{
            return false;
        }
    }

    /**
     * Returns whether the order is a valid capturing-move for a white pawn
     * @param order the order to be checked
     * @param board the chessboard on which the move is supposed to be executed
     * @return Whether the move is valid
     */
    public boolean captureBlack(int[] order, Board board){
        return !colour && order[1] + 1 == order[3] && (order[0] + 1 == order[2] || order[0] - 1 == order[2]) && !board.squares[order[3]][order[2]].isEmpty() && board.squares[order[3]][order[2]].isFigureColour();
    }

    /**
     * Returns whether the order is a valid capturing-move for a black pawn
     * @param order the order to be checked
     * @param board the chessboard on which the move is supposed to be executed
     * @return Whether the move is valid
     */
    public boolean captureWhite(int[] order, Board board){
        return colour && order[1] -1 == order[3] && (order[0] + 1 == order[2] || order[0] - 1 == order[2]) && !board.squares[order[3]][order[2]].isEmpty() && !board.squares[order[3]][order[2]].isFigureColour();
    }

    /**
     * Checks whether the move is a valid EnPassante move by a black pawn
     * @param order  The complete Move expressed in an Array Form
     * @param board  The current chessboard
     * @return Returns true when the EnPassante move is valid
     */
    public boolean enPassantBlack(int[] order, Board board){
        if(!colour && order[1]+1==order[3] && (order[0]+1==order[2] || order[0]-1==order[2]) &&!board.squares[order[3]-1][order[2]].isEmpty() && board.squares[order[3]-1][order[2]].getFigure().getType() == 20 && board.squares[order[3]-1][order[2]].isFigureColour()) {

            String history=board.getBeatenHistory()+board.getSquare(order[3]-1,order[2]).getFigure().print1()+" ";
            board.squares[order[3]-1][order[2]].removeFigure();

            board.setBeatenHistory(history);
            return true;
        }
        return false;
    }
    /**
     * Checks whether the move is a valid EnPassante move by a white pawn
     * @param order  The complete Move expressed in an Array Form
     * @param board  The current chessboard
     * @return Returns true when the EnPassante move is valid
     */
    public boolean enPassantWhite(int[] order, Board board){
        if(colour && order[1]-1==order[3] && (order[0]+1==order[2] || order[0]-1==order[2])&& !board.squares[order[3]+1][order[2]].isEmpty() && board.squares[order[3]+1][order[2]].getFigure().getType() == 20 && !board.squares[order[3]+1][order[2]].isFigureColour()) {
            String history=board.getBeatenHistory()+board.getSquare(order[3]+1,order[2]).getFigure().print1()+" ";
            board.squares[order[3]+1][order[2]].removeFigure();

            board.setBeatenHistory(history);
            return true;
        }
        return false;
    }

    /**
     * method to get the value of the pawn
     * @return value in int
     */
    @Override
    public int getValue(){
        return value;
    }

    /**
     * method to set the colour of the pawn
     * @param colour wanted colour
     */
    public void setColour(boolean colour) {
        this.colour=colour;
    }
}

package chess.model;
/**
 * King Model Class that represents a King
 */
public class King implements Figure {
    public int type = 40;
    String Symbol;
    private boolean colour;
    int value = 900;

    /**
     * Creates a new King Instance with one of the two Colours.
     * @param colour One of the two Colours in Boolean Form. White: True ; Black: False
     */
    public King(boolean colour){
        this.colour = colour;
    }

    /**
     * Constructor for a copy of another king figure
     * @param king the figure to be copied
     */
    public King(King king){
        this.type=king.type;
        this.colour=king.colour;
    }

    /**
     * Returns the Symbol of the King
     * @return Returns the specific String Code for the Symbol
     */
    @Override
    public String print(){
        if (colour){
            //return "\u265A";
            return "K";
        }
        else {
            //return "\u2654";
            return "k";
        }
    }
    @Override
    public String print1(){
        if (!colour){
            return "\u265A";

        }
        else {
            return "\u2654";

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
        //System.out.println("ich bin König");
        if(checkMoveTop(order)) {
            type = 4;
            return true;
        } else if(checkMoveMid(order)) {
            type = 4;
            return true;
        } else if (checkMoveBot(order)) {
            type = 4;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the order is for valid movement to the top of the king
     * @param order The complete Move expressed in an Array Form
     * @return returns true if the king could move to that position
     */
    public boolean checkMoveTop(int[] order){
        return order[1]-1 == order[3] && (order[0]-1 == order[2] ||order[0] == order[2] ||order[0]+1 == order[2]);
    }

    /**
     * Checks whether the order is for valid movement to the sides of the king
     * @param order The complete Move expressed in an Array Form
     * @return returns true if the king could move to that position
     */
    public boolean checkMoveMid(int[] order){
        return order[1] == order[3] && (order[0]-1 == order[2] || order[0]+1 == order[2]);
    }

    /**
     * Checks whether the order is for valid movement to the bottom of the king
     * @param order The complete Move expressed in an Array Form
     * @return returns true if the king could move to that position
     */
    public boolean checkMoveBot(int[] order){
        return order[1]+1 == order[3] && (order[0]-1 == order[2] ||order[0] == order[2] ||order[0]+1 == order[2]);
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
     * Special Move for the King is the Rochade
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return true when the Rochade is viable
     */
    @Override
    public boolean specialMove(int[] order, Board board) {
        //small rochade
        if(checkSmallCastling(order, board)) {
            Figure figure = board.squares[order[3]][order[2]+1].getFigure();
            board.squares[order[3]][order[2]+1].removeFigure();
            board.squares[order[3]][order[2]-1].setFigure(figure);
            return true;
        } else if(checkLargeCastling(order, board)) {
            Figure figure = board.squares[order[3]][order[2]-2].getFigure();
            board.squares[order[3]][order[2]-2].removeFigure();
            board.squares[order[3]][order[2]+1].setFigure(figure);
            return true;
        } else {
            return checkMove(order);
        }
    }

    /**
     * Checks whether the move is a valid small castling maneuver
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return returns true if the king can do that castling maneuver
     */
    public boolean checkSmallCastling(int[] order, Board board){
        return type == 40 && order[1] == order[3] && order[0]+2 == order[2] && board.squares[order[3]][order[2]-1].isEmpty() && board.squares[order[3]][order[2]].isEmpty() && !board.squares[order[3]][order[2]+1].isEmpty()&&board.squares[order[3]][order[2]+1].getFigure().getType() == 10;
    }

    /**
     * Checks whether the move is a valid large castling maneuver
     * @param order The complete Move expressed in an Array Form
     * @param board The current chessboard
     * @return returns true if the king can do that castling maneuver
     */
    public boolean checkLargeCastling(int[] order, Board board){
        return type == 40 && order[1] == order[3] && order[0]-2 == order[2] &&!(order[2]==0)&& board.squares[order[3]][order[2]-1].isEmpty() && board.squares[order[3]][order[2]+1].isEmpty() && board.squares[order[3]][order[2]].isEmpty() &&!board.squares[order[3]][order[2]-2].isEmpty()&& board.squares[order[3]][order[2]-2].getFigure().getType() == 10;
    }

    /**
     * Sets the type
     * @param i the new Type
     */
    @Override
    public void setType(int  i) {
        this.type = i;
    }

    /***
     * method to set colour of the King
     * @param colour the wanted colour
     */
    public void setColour(Boolean colour){
        this.colour=colour;
}

    /**
     * method to get the value of the king
     * @return value in int
     */
    @Override
    public int getValue(){
        return value;
    }
}

package chess.model;

/**
 * the History Objekt which stores the Board, the move in int[] and in String and the turn
 */
public class History {
    Board board;
    int[] move;
    String MoveAsString;
    boolean turn;
    public History(Board board, int[] move,String s,boolean turn){
        this.board = board;
        this.move = move;
        this.MoveAsString =s;
        this.turn = turn;
    }

    /**
     * the getter method for the board object
     * @return the board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * the getter method for the int[] move
     * @return the move
     */
    public int[] getMove(){
        return move;
    }

    /**
     * the getter method for the move in String
     * @return
     */
    public String getMoveAsString(){
        return MoveAsString;
    }



    /**
     * The getter for the turn
     * @return the turn
     */

    public boolean isTurn() {
        return turn;
    }

}

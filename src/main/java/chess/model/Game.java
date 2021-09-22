package chess.model;


import chess.controller.ConsoleInput;
import chess.controller.Scanner;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;




/**
 * Game Model Class that represents a Game
 */
public class Game {
    boolean player=true;
    boolean canControlFirstPlayer = true;
    boolean canControlSecondPlayer = true;
    public DataInputStream in;
    public DataOutputStream out;
    Board board;
    Scanner scanner;
    ConsoleInput ConsoleInput;
    Player player1;
    Player player2;
    LinkedList<History> historyList;


    /**
     * Game Constructor for Network Game
     * @param player1 The First Player
     * @param player2 The Second Player
     * @param canControlPlayer The Variable that determines which Player can be controlled
     * @param in The DataInputStream
     * @param out The DataOutputStream
     */
    public Game(Player player1, Player player2, boolean canControlPlayer, DataInputStream in, DataOutputStream out){
        this(player1, player2);
        if(canControlPlayer) {
            canControlSecondPlayer = false;
        } else {
            canControlFirstPlayer = false;
        }
        this.in = in;
        this.out = out;
        this.historyList = new LinkedList<>();

        historyList.add(new History(new Board(),null,null,true));

    }
    /**
     * Control player 1
     */
    public boolean isCanControlFirstPlayer() {
        return canControlFirstPlayer;
    }

    /**
     * Control player 2
     */
    public boolean isCanControlSecondPlayer() {
        return canControlSecondPlayer;
    }

    /**
     * Initializes a Game by creating a Board and setting up an Console Input.
     * @param player1  player 1
     * @param player2  player 2
     */
    public Game(Player player1, Player player2){
    this.player1= player1;
    this.player2= player2;
    this.board= new Board();
    this.ConsoleInput = new ConsoleInput();
    this.scanner = new Scanner();
    player1.setColour(true);
    player2.setColour(false);
    this.historyList = new LinkedList<>();

    historyList.add(new History(new Board(),null,null,true));

    }

    public void setHistoryList(LinkedList list){
        this.historyList= list;
}



    /**
     * Updates the Player Variable which indicates the Player who is allowed to move.
     */
    public void updateTurn(){
        player= !player;
}

/**
 * The Getter for the HistoryList
 * @return The current history list
 */
public LinkedList<History> getHistory(){
    return historyList;

}
    /**
     * Checks if a Move is allowed.
     * @param order The complete Move expressed in an Array Form
     * @param moveBoard board for this move
     * @return this move is true or false
     */
    public static boolean allowedMove(int[] order,Board moveBoard) {
        if (moveBoard.squares[order[1]][order[0]].getFigure().getType() == 0 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 4 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 40 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 20) {
            return moveBoard.squares[order[1]][order[0]].getFigure().specialMove(order, moveBoard);
        } else {
            return moveBoard.squares[order[1]][order[0]].allowedFigureMove(order);
        }
    }

    /**
     * Resets the en-Passant for all Pawns by using checkMove
     * @param order array for reset
     */
    public void resetEnPassant(int[] order) {
        int i = 0;
        while(i < 8) {
            if(i != order[2]) {
                if(!board.squares[3][i].isEmpty() && board.squares[3][i].getFigure().getType() == 20 ) {
                    board.squares[3][i].getFigure().setType(0);
                }
                if (!board.squares[4][i].isEmpty() && board.squares[4][i].getFigure().getType() == 20){
                    board.squares[4][i].getFigure().setType(0);
                }
            }


            i++;
        }
    }



    /**
     * Method returning which players turn it is
     * @return The player whose turn it is
     */
    public boolean isTurn(){
        return player;
    }


    /**
     * The Setter for the turn of the Players
     * @param turn The new turn
     */
    public void setTurn(boolean turn){
        this.player = turn;
    }
    /**
     * method to get the Board of the actual game
     * @return the board
     */
    public Board getBoard(){
        return board;
    }
    public void setBoard(Board board){
        this.board=new Board(board);
    }

    /**
     * method to get the player1 (white) of the gtame
     * @return the white player
     */
    public Player getPlayer1(){
        return player1;
    }
    /**
     * method to get the player2 (black) of the gtame
     * @return the black player
     */
    public Player getPlayer2(){
        return player2;
    }



}

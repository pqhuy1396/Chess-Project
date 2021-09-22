package chess.model;

import chess.controller.ConsoleInput;

import java.util.Arrays;

/**
 * Game Model Class that represents a Game
 */
public class Game {
    boolean player=true;
    Board board;
    Movement movement;
    ConsoleInput ConsoleInput;


    /**
     * Initializes a Game by creating a Board and setting up an Console Input.
     */
    public Game(){

    this.board= new Board();
    this.ConsoleInput = new ConsoleInput();
    this.movement = new Movement();

}




    /**
     * Checks if the Positions in the Move are allowed.
     * @param order The complete Move expressed in an Array Form
     * @return Returns true if all Positions in the Move are viable.
     */
    public boolean allowedPosition(int[] order,boolean forPlayer, Board positionBoard){
        if(!positionBoard.squares[order[1]][order[0]].isEmpty()) {
            if (positionBoard.squares[order[3]][order[2]].isEmpty()) {
                //System.out.println("ziel nicht belegt");
                return positionBoard.squares[order[1]][order[0]].isFigureColour() == forPlayer;
            } else if (!positionBoard.squares[order[3]][order[2]].isEmpty() && positionBoard.squares[order[3]][order[2]].isFigureColour() != forPlayer) {
                //System.out.println("ich bin belegt");
                return positionBoard.squares[order[1]][order[0]].isFigureColour() == forPlayer;
            } else {
                //System.out.println("ich bin vielleicht belegt");

                return false;
            }
        }
        else {
            return false;
        }

}

    /**
     * Updates the Player Variable which indicates the Player who is allowed to move.
     */
    public void updateTurn(){
        player= !player;
}

    /**
     * Checks if a Move is allowed.
     * @param order The complete Move expressed in an Array Form
     */
    public boolean allowedMove(int[] order,Board moveBoard) {
        if (moveBoard.squares[order[1]][order[0]].getFigure().getType() == 0 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 4 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 40 || moveBoard.squares[order[1]][order[0]].getFigure().getType() == 20) {
            return moveBoard.squares[order[1]][order[0]].getFigure().specialMove(order, moveBoard);
        } else {
            return moveBoard.squares[order[1]][order[0]].allowedFigureMove(order);
        }
    }
    /**
     * Executes a Move by saving the Figure on a new Square and removing it from its old Position.
     * @param order The complete Move expressed in an Array Form
     */
    public Figure[] executeMove(int[] order) {
        int[] q = {4, 0};
        int[] w = {6, 0};
        int[] e = {5, 5};
        int[] r = {5, 3};
        int[] t = {0, 0};

        Figure figure = board.squares[order[1]][order[0]].getFigure();


        Figure[] reMove = new Figure[2];
        reMove[0] = board.squares[order[1]][order[0]].getFigure();
        reMove[1] = board.squares[order[3]][order[2]].getFigure();
        board.squares[order[1]][order[0]].removeFigure();
        if (!board.squares[order[3]][order[2]].isEmpty()) {
            board.beatenFigures.add(board.squares[order[3]][order[2]].getFigure());
        }
        board.squares[order[3]][order[2]].removeFigure();
        board.squares[order[3]][order[2]].setFigure(figure);

        return reMove;
    }
    /**
     * Prints the board in the Console
     */
    public void print(){
        board.print();
    }

    /**
     * Runs the Game
     */
    public void runGame(){
        //print();
        //executeMove(ConsoleInput.convertCommand(ConsoleInput.listenInput()));


        print();
        //if (isCheck()){
        //    System.out.println("!Check");}
        int[] ar;
        String s ;
        s = ConsoleInput.listenInput();
        if (ConsoleInput.validListRequest(s)) {
            if (board.beatenFigures.isEmpty()){
                System.out.println("none");
            }else {
                for (Figure fig : board.beatenFigures) {
                    System.out.print(fig.print());
                }
                System.out.println("");
            }
        }else if(!ConsoleInput.validCommand(s)) {
            System.out.println("!Invalid move");
        } else {

            ar = ConsoleInput.convertCommand(s);

            if (allowedPosition(ar,player,board)&& allowedMove(ar,board) && movement.freePath(board.squares[ar[1]][ar[0]].getFigure().getType(), ar, board)) {

                Figure[] reMove = executeMove(ar);
                gameExecution(ar, reMove, s);
            } else {
                System.out.println("!Move not allowed");
            }
        }

    }


    /**
     * Executes the movement on the board
     * @param ar the move in int-Array form
     * @param reMove
     * @param s the move in String form
     */
    public void gameExecution(int[] ar, Figure[] reMove, String s){
        if(isCheck(player,board)) {
            //board.beatenFigures.remove(board.beatenFigures.size());
            board.squares[ar[1]][ar[0]].setFigure(reMove[0]);
            board.squares[ar[3]][ar[2]].setFigure(reMove[1]);
            System.out.println("!Move not allowed");
        }
        else if(isCheck(!player,board)) {
            if(canRelease(!player)) {
                if(board.squares[ar[3]][ar[2]].getFigure().getType() == 10) {
                    board.squares[ar[3]][ar[2]].getFigure().setType(1);
                }
                System.out.println("!" + s);
                System.out.println("Check");
                if(board.squares[ar[3]][ar[2]].getFigure().getType() == 10) {
                    board.squares[ar[3]][ar[2]].getFigure().setType(1);
                }
                resetEnPassant(ar);
                updateTurn();
            }
            else {
                System.out.println("!" + s);
                System.out.println("Checkmate");
            }
        }
        else{
            if(board.squares[ar[3]][ar[2]].getFigure().getType() == 10) {
                board.squares[ar[3]][ar[2]].getFigure().setType(1);
            }
            System.out.println("!" + s);
            if(board.squares[ar[3]][ar[2]].getFigure().getType() == 10) {
                board.squares[ar[3]][ar[2]].getFigure().setType(1);
            }
            board.pawnAscension(board,s);
            resetEnPassant(ar);
            updateTurn();
        }
    }

    /**
     * Resets the en-Passant for all Pawns by using checkMove
     */
    void resetEnPassant(int[] order) {
        int i = 0;
        int[] ar = {0,0,7,7};
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
     * Returns true when the players king stands in check
     * @param player The player whose king is checked
     * @return True when the king stands in check
     */
    public boolean isCheck(Boolean player, Board checkBoard ){
        boolean[][] list = generateThreatenedSpaces(checkBoard, !player);
        int[] king = getPlayerKingPosition(player,checkBoard);
        return list[king[1]][king[0]];
    }
    /**
     * method to get the position of the king of the current player
     * @return The position of the king
     */
    public int[] getPlayerKingPosition(boolean player,Board board){
        int[] position = new int[2];
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                if (!board.squares[i][j].isEmpty() && board.squares[i][j].isFigureColour() == player && (board.squares[i][j].getFigure().getType() == 4|| board.squares[i][j].getFigure().getType() == 40)) {

                    position[0] = j;
                    position[1] = i;
                    return position;
                }

            }
        }
        return position;
    }

    /**
     *  Generates a 2D Array wich denotes all spaces threatened by one colour
     * @param board board which contains the pieces
     * @param colour colour of threatening pieces
     * @return a 2D Array denoting wich spaces are threatened
     */
    public boolean[][] generateThreatenedSpaces(Board board, Boolean colour){
        boolean[][] threatenedSpaces = new boolean[8][8];
        for (boolean[] threatenedSpace : threatenedSpaces) {
            Arrays.fill(threatenedSpace, false);
        }
        for (int j = 0; j < 8;j++ ) {
            for (int i=0;i<8;i++){
                if (!board.squares[j][i].isEmpty() && colour == board.squares[j][i].getFigure().isColour()) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            int[] tempOrder = {i, j, l, k};
                            threatenedSpaces[k][l] = checkThreat(tempOrder,board, threatenedSpaces);
                        }
                    }
                }
            }
        }

        return threatenedSpaces;
    }

    /**
     * Checks whether a figure threatens a space
     * @param tempOrder An order giving the position of the figure and the space being tested
     * @return Returns true if the space is threatened by the figure
     */
    public boolean checkThreat(int[] tempOrder,Board board, boolean[][] threatenedSpaces){
        if (!board.squares[tempOrder[1]][tempOrder[0]].isEmpty()) {
           int tempType = board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType();
            if ( board.squares[tempOrder[1]][tempOrder[0]].threatMove(tempOrder,tempType,board) && movement.freePath(board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType(), tempOrder, board)) {
                return board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType() != 0 && board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType() != 20;
            } else{
                if (board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType() == 0 && !board.squares[tempOrder[1]][tempOrder[2]].isEmpty() && board.squares[tempOrder[1]][tempOrder[2]].getFigure().getType() != 20 && board.squares[tempOrder[1]][tempOrder[0]].getFigure().specialMove(tempOrder, board)) {
                    board.squares[tempOrder[1]][tempOrder[0]].getFigure().setType(tempType);
                    return true;
                }
                else if(board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType() == 0 && !board.squares[tempOrder[3]][tempOrder[2]].isEmpty() &&  board.squares[tempOrder[1]][tempOrder[0]].getFigure().specialMove(tempOrder, board)) {
                    board.squares[tempOrder[1]][tempOrder[0]].getFigure().setType(tempType);
                    return true;
                }else if(board.squares[tempOrder[1]][tempOrder[0]].getFigure().getType() == 0 && !board.squares[tempOrder[3]][tempOrder[2]].isEmpty() &&  board.squares[tempOrder[1]][tempOrder[0]].getFigure().specialMove(tempOrder, board)) {
                    board.squares[tempOrder[1]][tempOrder[0]].getFigure().setType(tempType);
                    return true;
                }
            }
        }
        return threatenedSpaces[tempOrder[3]][tempOrder[2]];
    }

    /**
     * Whether a player can "escape" from chess
     * @param forPlayer the player
     * @return whether the player can escape
     */
    public boolean canRelease(boolean forPlayer){
        Board testBoard1 = new Board(board);


        for(int i=0;i<8;i++){
            for(int j = 0; j<8;j++){
                if(!testBoard1.squares[j][i].isEmpty()&&testBoard1.squares[j][i].getFigure().isColour()==forPlayer){
                    //System.out.println("player figure exists");
                    for(int k=0;k<8;k++){
                        for(int l=0;l<8;l++){
                            Board testBoard2=new Board(testBoard1);
                            int[] tempMove={i,j,k,l};
                            int temptype = testBoard2.squares[j][i].getFigure().getType();
                            if(allowedPosition(tempMove,forPlayer,testBoard2) && testBoard2.squares[tempMove[1]][tempMove[0]].threatMove(tempMove,temptype,testBoard2)&& movement.freePath(testBoard2.squares[tempMove[1]][tempMove[0]].getFigure().getType(), tempMove, testBoard2)){
                                //System.out.println("player figure move exists"+i+j+k+l);

                                testExecute(testBoard2,tempMove);
                                if(!isCheck(forPlayer,testBoard2)){
                                    //System.out.println("player figure escape move exists"+i+j+k+l);
                                    //testBoard2.print();
                                    testBoard1.squares[j][i].getFigure().setType(temptype);
                                    return true;
                                }
                                testBoard1.squares[j][i].getFigure().setType(temptype);
                            }
                        }



                    }
                }

            }
        }

        return false;
    }

    /**
     * Moves a figure on a copied board
     * @param testBoard the copied board
     * @param move the move the figure should be doing
     */
    public void testExecute(Board testBoard, int[] move){
        Figure moveFigure;
        moveFigure = testBoard.squares[move[1]][move[0]].getFigure();
        testBoard.squares[move[1]][move[0]].removeFigure();
        testBoard.squares[move[3]][move[2]].setFigure(moveFigure);
    }



}

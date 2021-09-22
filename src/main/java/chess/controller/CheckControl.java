package chess.controller;

import chess.model.Board;
import chess.model.Figure;


import java.util.Arrays;

/**
 * Class which got methods to detect a check situation
 */
public class CheckControl {




    /**
     *  Generates a 2D Array wich denotes all spaces threatened by one colour
     * @param board board which contains the pieces
     * @param colour colour of threatening pieces
     * @return a 2D Array denoting wich spaces are threatened
     */
    public static boolean[][] generateThreatenedSpaces(Board board, Boolean colour){
        boolean[][] threatenedSpaces = new boolean[8][8];
        for (boolean[] threatenedSpace : threatenedSpaces) {
            Arrays.fill(threatenedSpace, false);
        }
        for (int j = 0; j < 8;j++ ) {
            for (int i=0;i<8;i++){
                if (!board.getSquare(j,i).isEmpty() && colour == board.getSquare(j,i).getFigure().isColour()) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            int[] tempOrder = {i, j, l, k};
                            threatenedSpaces[k][l] = checkThreat(tempOrder,board)||threatenedSpaces[k][l];
                        }
                    }
                }
            }
        }

        return threatenedSpaces;
    }


    /**
     *
     * @param tempOrder the move for which is tested if it threatens a space
     * @param board the board for which it is tested
     * @return return true if the move threatens a space or if its already threatened
     */
    public static boolean checkThreat(int[] tempOrder, Board board){
        Board testBoard = new Board(board);
        if ( testBoard.getSquare(tempOrder[1],tempOrder[0]).allowedFigureMove(tempOrder) && Scanner.freePath(testBoard.getSquare(tempOrder[1],tempOrder[0]).getFigure().getType(), tempOrder, testBoard)) {
            if (board.getSquare(tempOrder[1],tempOrder[0]).getFigure().getType() != 0 && board.getSquare(tempOrder[1],tempOrder[0]).getFigure().getType() != 20) {
                return true;
            }
            else{
                return checkPawnThreat(tempOrder,testBoard);
            }

        }
        else{
            return checkPawnThreat(tempOrder,testBoard);
        }

    }


    /**
     *Checks whether a pawn can threaten a space
     * @param tempOrder An order giving the position of the figure and the space being tested
     * @param board the board where the move is tested
     * @return Returns true if the space is threatened by the figure
     */
    public static boolean checkPawnThreat(int[] tempOrder, Board board){
        return (board.getSquare(tempOrder[1],tempOrder[0]).getFigure().getType() == 0||board.getSquare(tempOrder[1],tempOrder[0]).getFigure().getType() == 20) && !board.getSquare(tempOrder[3],tempOrder[2]).isEmpty() &&  board.getSquare(tempOrder[1],tempOrder[0]).getFigure().specialMove(tempOrder, board);
    }



    /**
     * Generates an array containing all spaces threatened by a figure
     * @param board board on which the figure is
     * @param i the x coordinate of the figure for which the list is generated
     * @param j the y coordinate of the figure for which the list is generated
     * @return the boolean array containing which positions are threatened by the figure
     */
    public static boolean[][] generateReachableSpaces(Board board, int i, int j){
        Board testBoard = new Board(board);
        boolean[][] threatenedSpaces = new boolean[8][8];
        for (boolean[] threatenedSpace : threatenedSpaces) {
            Arrays.fill(threatenedSpace, false);
        }
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                int[] tempOrder = {i, j, l, k};
                threatenedSpaces[k][l] = checkReach(tempOrder,testBoard);
            }
        }
        return threatenedSpaces;
    }

    /**
     * The method to check if a move is valid including check situations
     * @param order the move for which is checked if it can get executed
     * @param board the board on which it is tested
     * @return return true if the move could get executed
     */
    public static boolean checkReach(int[] order, Board board) {
        Board testBoard = new Board(board);
        if(testBoard.getSquare(order[1], order[0]).getFigure().getType()==40||testBoard.getSquare(order[1], order[0]).getFigure().getType()==4 || testBoard.getSquare(order[1], order[0]).getFigure().getType()==0|| testBoard.getSquare(order[1], order[0]).getFigure().getType()==20) {
            return checkSpecialMoveReach(order,testBoard);
        }
        else {
            return checkFigureMoveReach(order, testBoard);
        }
    }

    /**
     * The method which checks if the figures which got a specialMove can execute this move (Pawn, King)
     * @param order the move containing start and goal coordinates
     * @param board the board where it is checked
     * @return true if the move is valid on this board
     */
    public static boolean checkSpecialMoveReach(int[] order, Board board){
        Board testBoard = new Board(board);
        if( !testBoard.getSquare(order[1], order[0]).isEmpty()&& Scanner.allowedPosition(order, testBoard.getSquare(order[1], order[0]).getFigure().isColour(), testBoard) && testBoard.getSquare(order[1], order[0]).getFigure().specialMove(order,testBoard) && Scanner.freePath(testBoard.getSquare(order[1], order[0]).getFigure().getType(), order, testBoard)){
            testExecute(testBoard,order);
            return !CheckControl.isCheck(testBoard.getSquare(order[3], order[2]).getFigure().isColour(),testBoard);
        }
        else{
            return false;
        }
    }
    /**
     * The method which checks if the figures with a usual move can execute this move (rook, knight, bishop, queen)
     * @param order the move containing start and goal coordinates
     * @param board the board where it is checked
     * @return true if the move is valid on this board
     */
    public static boolean checkFigureMoveReach(int[] order, Board board){
        Board testBoard = new Board(board);
        if( !testBoard.getSquare(order[1], order[0]).isEmpty()&& Scanner.allowedPosition(order, testBoard.getSquare(order[1], order[0]).getFigure().isColour(), testBoard) && testBoard.getSquare(order[1], order[0]).getFigure().checkMove(order) && Scanner.freePath(testBoard.getSquare(order[1], order[0]).getFigure().getType(), order, testBoard)){
            testExecute(testBoard,order);
            return !CheckControl.isCheck(testBoard.getSquare(order[3], order[2]).getFigure().isColour(),testBoard);
        }
        else{
            return false;
        }
    }



    /**
     * The method to get the position of the king for the white or the black player
     * @param player the player for which the king is searched
     * @param board the board where is looked for the king
     * @return position of the king with same colour as the player
     */
    public static int[] getPlayerKingPosition(boolean player, Board board){
        int[] position = new int[2];
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                if (!board.getSquare(i,j).isEmpty() && board.getSquare(i,j).isFigureColour() == player && (board.getSquare(i,j).getFigure().getType() == 4|| board.getSquare(i,j).getFigure().getType() == 40)) {

                    position[0] = j;
                    position[1] = i;
                    return position;
                }

            }
        }
        return position;
    }

    /**
     * Method to detect a check situation
     * @param player The player whose king is checked
     * @param checkBoard True when the king stands in check
     * @return true when the players king stands in check
     */
    public static boolean isCheck(Boolean player, Board checkBoard ){
        boolean[][] list = generateThreatenedSpaces(checkBoard, !player);
        int[] king = getPlayerKingPosition(player, checkBoard);
        return list[king[1]][king[0]];
    }

    /**
     * Whether a player can "escape" from chess
     * @param forPlayer the player
     * @param board the board
     * @return true if the player can make a move without having a check situation for himself at the end
     */
    public  static boolean canRelease(boolean forPlayer, Board board){
        for(int i=0;i<8;i++){
            for(int j = 0; j<8;j++){
                if(!board.getSquare(j,i).isEmpty()&&board.getSquare(j,i).getFigure().isColour()==forPlayer){
                    for(int k=0;k<8;k++){
                        for(int l=0;l<8;l++){
                            int[] tempMove={i,j,k,l};
                            if (checkReach(tempMove,board)){
                                return true;
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
    public static void testExecute(Board testBoard, int[] move){
        Figure moveFigure;
        moveFigure = testBoard.getSquare(move[1],move[0]).getFigure();
        testBoard.getSquare(move[1],move[0]).removeFigure();

        testBoard.getSquare(move[3],move[2]).setFigure(moveFigure);
    }

}

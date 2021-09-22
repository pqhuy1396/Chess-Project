package chess.controller;

import chess.model.Board;

/**
 * Contains methods for checking whether figures are in the way of movement
 */
public class Scanner {
    /**
     * method ScanDiagonalUp checks if squares from start position (x,y) in direction from downside left to upside right over distance d are empty
     * @param d distance between start and goal coordinates
     * @param x start x-coordinate
     * @param y start y-coordinate
     * @param board method scans squares on this board
     * @return returns true if diagonal path is not blocked
     */
    public static boolean scanDiagonalUp(int d, int x, int y, Board board) {
        int i = 1;
        while (i < (d)) {
            if (!board.getSquare(y - i,x + i).isEmpty()) {


                return false;
            }
            i++;


        }
        return true;
    }
    /**
     * method scanDiagonalDown checks if squares from start position (x,y) in direction from upside left to downside right over distance d are empty
     * @param d distance between start and goal coordinates
     * @param x start x-coordinate
     * @param y start y-coordinate
     * @param board method scans squares on this board
     * @return returns true if diagonal path is not blocked
     */
    public static boolean scanDiagonalDown(int d, int x, int y, Board board) {
        int i = 1;
        while (i < (d)) {
            if (!board.getSquare(y +i,x + i).isEmpty()) {


                return false;
            }
            i++;


        }
        return true;
    }

    /**
     * method scanStraightY checks if squares between start position (c,a) and end position (c,b) are empty
     * @param c constant x level
     * @param a start y coordinate
     * @param b end y coordinate
     * @param board method scans squares on this board
     * @return true if straight path is not blocked
     */
    public static boolean scanStraightY(int c, int a, int b, Board board){
        int i = 1;
        while(i<(a-b)) {

            if (!board.getSquare(a-i,c).isEmpty()) {


                return false;
            }
            i++;
        }
        return true;
    }
    /**
     * method scanStraightX checks if squares between start position (a,c) and end position (b,c) are empty
     * @param c constant y level
     * @param a start x coordinate
     * @param b end x coordinate
     * @param board method scans squares on this board
     * @return true if straight path is not blocked
     */
    public static boolean scanStraightX(int c, int a, int b, Board board){
        int i = 1;
        while(i<(a-b)) {

            if (!board.getSquare(c,a-i).isEmpty()) {


                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Scans path in the directions in which Pawn can move
     * @param order scans for this order
     * @param board on this board
     * @return true if path not blocked
     */
    public static boolean pawnScanner(int[] order, Board board){
        if (order[1] > order[3]) {
            return scanStraightY(order[0], order[1], order[3], board);

        } else if (order[1] < order[3]) {
            return scanStraightY(order[0], order[3], order[1], board);

        } else {
            return true;
        }
    }
    /**
     * Scans path in the directions in which Rook can move
     * @param order scans for this order
     * @param board on this board
     * @return true if path not blocked
     */
    public static boolean rookScanner(int[]order, Board board){
        if (order[0] < order[2] && order[1] == order[3]) {
            return scanStraightX(order[1], order[2], order[0], board);
        }
        else if (order[0] > order[2] && order[1] == order[3]) {
            return scanStraightX(order[1], order[0], order[2], board);
        }
        else if (order[1] > order[3] && order[0] == order[2]) {
            return scanStraightY(order[0], order[1], order[3], board);
        }
        else if (order[1] < order[3] && order[0] == order[2]) {
            return scanStraightY(order[0], order[3], order[1], board);
        }
        else {
            return true;
        }



    }
    /**
     * Scans path in the directions in which Bishop can move
     * @param order scans for this order
     * @param board on this board
     * @return true if path not blocked
     */
    public static boolean bishopScanner(int[] order, Board board){

        //korrekt c8-e6
        if (order[0] < order[2] && order[1] < order[3]) {
            return scanDiagonalDown(Math.abs(order[0]-order[2]), order[0], order[1], board);

        }
        //korrekt c1-e3
        else if (order[0] < order[2] && order[1] > order[3]) {
            return scanDiagonalUp(Math.abs(order[0]-order[2]),order[0],order[1], board);
        }
        //korrekt c8-a6
        else if (order[0] > order[2] && order[1] < order[3]) {
            return scanDiagonalUp(Math.abs(order[0]-order[2]),order[2],order[3], board);
        }
        //korerekt c1-a3
        else if (order[0] > order[2] && order[1] > order[3]) {
            return scanDiagonalDown(Math.abs(order[0]-order[2]),order[2 ],order[3], board);
        }
        else {
            return true;

        }

    }
    /**
     * Scans path in the directions in which Queen can move. The scanner for Queen is a combination of rookScanner and bishopScanner
     * @param order scans for this order
     * @param board on this board
     * @return true if path not blocked
     */
    public static boolean queenScanner(int[] order, Board board){
        return rookScanner(order, board) && bishopScanner(order, board);
    }



    /**
     * calls the right Method for each Figure to check if path is free.
     * Path for Knight can never be blocked
     * @param board on this board
     * @param figureType type this figure
     * @param order scans for this order
     * @return Scans path in the directions
     */
    public static boolean freePath(int figureType,int[] order, Board board) {

        switch (figureType) {
            case 0:
            case 20:
                return pawnScanner(order, board);
            case 1:
            case 10:
                return rookScanner(order, board);
            case 2:
                return bishopScanner(order, board);
            case 3:
                return true;
            case 5:
                return queenScanner(order, board);

        }
        return true;
    }
    /**
     * Checks if the Positions in the Move are allowed.
     * @param order The complete Move expressed in an Array Form
     * @param forPlayer this player
     * @param positionBoard position of board
     * @return Returns true if all Positions in the Move are viable.
     */
    public static boolean allowedPosition(int[] order,boolean forPlayer, Board positionBoard){
        if(!positionBoard.getSquare(order[1],order[0]).isEmpty()) {
            if (positionBoard.getSquare(order[3],order[2]).isEmpty()) {
                //System.out.println("ziel nicht belegt");
                return positionBoard.getSquare(order[1],order[0]).isFigureColour() == forPlayer;
            } else if (!positionBoard.getSquare(order[3],order[2]).isEmpty() && positionBoard.getSquare(order[3],order[2]).isFigureColour() != forPlayer) {
                //System.out.println("ich bin belegt");
                return positionBoard.getSquare(order[1],order[0]).isFigureColour() == forPlayer;
            } else {
                //System.out.println("ich bin vielleicht belegt");

                return false;
            }
        }
        else {
            return false;
        }

    }

}

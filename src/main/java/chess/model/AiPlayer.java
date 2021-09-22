package chess.model;

import chess.controller.CheckControl;
import chess.controller.GuiVar;
import chess.controller.Scanner;

/**
 * The class for the simple Ai
 */
public class AiPlayer implements Player {
    boolean colour;


    static String move = "aa-aa";
    static boolean Gui = false;
    @Override
    public boolean isColour() {
        return colour;
    }

    public void setGui(boolean gui) {
        Gui = gui;
    }


    @Override
    public void setColour(boolean set) {
        this.colour=set;
    }


    /**
     * calculates the best Turn a Player can do
     * @return the move in String
     */
    @Override
    public String getNextMove(Game actualGame) {
        int[] bestMove ;


        StringBuilder sb = new StringBuilder(move);
        // if(Gui && move != "aa-aa") {
        //     tempMove = move;
        //     move = "aa-aa";
        //     System.out.println("was passiert hier");
        //     return tempMove;

        //} else {
        if(GuiVar.getDepth()==0){
            bestMove=calcBestMove(actualGame);
            sb.setCharAt(0 , convertXtoChar(bestMove[0]));
            sb.setCharAt(1 , convertYtoChar(bestMove[1]));
            sb.setCharAt(3 , convertXtoChar(bestMove[2]));
            sb.setCharAt(4 , convertYtoChar(bestMove[3]));
            move =sb.toString();
            System.out.println("best move converted is"+move);
            return move;
        }
        Board testBoard = new Board(actualGame.getBoard());
        bestMove=minmax(testBoard, GuiVar.getDepth(),-10000,10000,colour);
        //bestMove=calcBestMove(actualGame);
        sb.setCharAt(0 , convertXtoChar(bestMove[0]));
        sb.setCharAt(1 , convertYtoChar(bestMove[1]));
        sb.setCharAt(3 , convertXtoChar(bestMove[2]));
        sb.setCharAt(4 , convertYtoChar(bestMove[3]));
        move =sb.toString();
        System.out.println("best move converted is"+move);
        return move;
        // }




    }

    /**
     * the Minmax function with alpha beta pruning
     * @param board the board where is searched
     * @param depth the depth for the algorthm
     * @param alpha the max value init -inf
     * @param beta the lowest value init + inf
     * @param player the colour for which is searched
     * @return the best found move for the player
     */
    //tiefe initial nicht null !
    public int[] minmax(Board board, int depth,int alpha, int beta, boolean player) {

        if (depth == 0) {

            int a = calcCheckValue(board,colour);
            //System.out.println(a);
            int[] move = new int[]{2, 2, 2, 4, a};
            return move;
        }
        else if (player == colour) {

            return maxSearch(board,player,depth,alpha,beta);
        }
        else {
            return minSearch(board,player, depth, alpha,beta);


        }
    }

    /**
     * The maximizing Search
     * @param board on this board
     * @param player maximazing for this player (ai)
     * @param depth the actual depth
     * @param alpha the max value found till now
     * @param beta the min value found till now
     * @return the move and the value of the minimized move
     */
    public int[] maxSearch(Board board, boolean player, int depth , int alpha ,int beta){

        if(beta<=alpha){
            int[] breakmove2 = new int[]{0, 0, 0, 0, alpha};
            return breakmove2;

        }

        int[] returnMove = new int[5];
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (!board.getSquare(i, j).isEmpty() && board.getSquare(i, j).getFigure().isColour() == player) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {

                            int[] toEvalMove = new int[]{j, i, k, l, 0};
                            int[] evaluation;

                            if (Scanner.allowedPosition(toEvalMove, player, board) && Game.allowedMove(toEvalMove, board) && Scanner.freePath(board.getSquare(toEvalMove[1], toEvalMove[0]).getFigure().getType(), toEvalMove, board)) {
                                Board testBoard2 = new Board(board);
                                aiMoveExecute(testBoard2,toEvalMove);
                                if(CheckControl.isCheck(player,board)){
                                    testBoard2=null;
                                    break;
                                }
                                evaluation = minmax(testBoard2, depth - 1,alpha,beta, !player);
                                testBoard2=null;
                                if (evaluation[4]>alpha){
                                    returnMove[0] = j;
                                    returnMove[1] = i;
                                    returnMove[2] = k;
                                    returnMove[3] = l;
                                    alpha=evaluation[4];
                                    returnMove[4] = alpha;
                                }

                            }
                        }
                    }
                }


            }

        }

        return returnMove;
    }

    /**
     * Algorith to search min value for the ai
     * @param board the actual board
     * @param player the player (not the ai)
     * @param depth the actual depth
     * @param alpha the max found value
     * @param beta the min found value
     * @return returns the minimizing move and the value
     */
    public int[] minSearch(Board board, boolean player, int depth , int alpha ,int beta){
        int[] returnMove = new int[5];
        if(beta<=alpha){
            int[] breakmove = new int[]{0, 0, 0, 0, beta};
            return  breakmove;
        }
        for (int j = 0; j < 8; j++) {

            for (int i = 0; i < 8; i++) {
                if (!board.getSquare(i, j).isEmpty() && board.getSquare(i, j).getFigure().isColour() == player) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            int[] toEvalMove = new int[]{j, i, k, l, 0};
                            int[] evaluation;

                            if (Scanner.allowedPosition(toEvalMove, player, board) && Game.allowedMove(toEvalMove, board) && Scanner.freePath(board.getSquare(toEvalMove[1], toEvalMove[0]).getFigure().getType(), toEvalMove, board)) {
                                Board testBoard1 = new Board(board);
                                aiMoveExecute(testBoard1, toEvalMove);
                                if (CheckControl.isCheck(player, testBoard1)) {
                                    testBoard1=null;
                                    break;
                                }
                                evaluation = minmax(testBoard1, depth - 1, alpha, beta, !player);
                                testBoard1=null;
                                if (evaluation[4] < beta) {
                                    returnMove[0] = j;
                                    returnMove[1] = i;
                                    returnMove[2] = k;
                                    returnMove[3] = l;
                                    beta = evaluation[4];
                                    returnMove[4] = beta;
                                }

                            }
                        }
                    }
                }

            }
        }

        return returnMove;
    }

    /**
     * Execute method for the AI
     * @param testBoard the Board on which the move is exevuted
     * @param toEvalMove the Move
     */
    public void aiMoveExecute(Board testBoard, int[] toEvalMove){
        testBoard.getSquare(toEvalMove[3], toEvalMove[2]).setFigure(testBoard.getSquare(toEvalMove[1], toEvalMove[0]).getFigure());
        testBoard.getSquare(toEvalMove[1], toEvalMove[0]).removeFigure();
        testBoard.pawnAscension(testBoard, "aaaaa");
    }

    /**
     * converts the int value of the board into an integer for the 2d array
     * @param x the x value of the coordinate in int
     * @return the x value of the coordinate in int for the 2d array
     */
    public char convertXtoChar(int x){
        switch(x){
            case 0:
                return 'a';
            case 1:
                return 'b';
            case 2:
                return 'c';
            case 3:
                return 'd';
            case 4:
                return 'e';
            case 5:
                return 'f';

            case 6:
                return 'g';

            case 7:
                return 'h';

        }
        return 'x';
    }

    /**
     * converts the char value of y coordinate into an integer
     * @param y the y value of the coordinate in char
     * @return the y value of the coordinate in int
     */
    public char convertYtoChar(int y){
        switch(y){
            case 0:
                return '8';
            case 1:
                return '7';
            case 2:
                return '6';
            case 3:
                return '5';
            case 4:
                return '4';
            case 5:
                return '3';

            case 6:
                return '2';

            case 7:
                return '1';

        }
        return 'x';
    }

    /**
     * calculates the highest ranked move the ai-player can do
     * @param actualGame the game for which the move is calculated
     * @return an array containing the move coordinates
     */
    public int[] calcBestMove(Game actualGame) {
        int bestValue = -10000;
        int[] testMove;
        int[] bestMove = new int[5];
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (!actualGame.getBoard().getSquare(i,j).isEmpty()&&actualGame.getBoard().getSquare(i,j).getFigure().isColour() == colour){

                    int[] start = new int[]{j,i};
                    testMove = calcBestFigureMove(actualGame,start);
                    if(testMove[4]>bestValue) {
                        bestMove = testMove;
                        bestValue = testMove[4];
                    }
                    else if(testMove[4] == bestValue && Math.random()< 0.3){
                        bestMove = testMove;
                    }
                }
            }
        }
        int[] bestFigureMove = new int[]{bestMove[0],bestMove[1],bestMove[2],bestMove[3]};
        return bestFigureMove;
    }

    /**
     * Gets the Positions of a figure of the AI and calculates the highest ranked move for this figure
     * @param actualGame the game
     * @param start the Position of the Figure
     * @return returns the move with he highest rank
     */
    public int[] calcBestFigureMove(Game actualGame,int[] start) {
        int[] bestFigureMove = new int[5];
        int value = -10000;
        bestFigureMove[0]=start[0];
        bestFigureMove[1]=start[1];
        bestFigureMove[4]=value;
        int iterationValue;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Board testBoard = new Board(actualGame.getBoard());
                int[] move = new int[]{start[0], start[1], j, i};
                if (Scanner.allowedPosition(move, colour, testBoard)&&actualGame.allowedMove(move, testBoard)&& Scanner.freePath(testBoard.getSquare(move[1], move[0]).getFigure().getType(), move, testBoard)) {

                    testBoard.getSquare(move[3], move[2]).setFigure(actualGame.getBoard().getSquare(move[1], move[0]).getFigure());
                    testBoard.getSquare(move[1], move[0]).removeFigure();
                    testBoard.pawnAscension(actualGame.getBoard(), "aaaaa");


                    iterationValue=calcCheckValue(testBoard,colour);

                    if (iterationValue > bestFigureMove[4]) {
                        bestFigureMove[2] = j;
                        bestFigureMove[3] = i;
                        bestFigureMove[4] = iterationValue;
                    } else if (iterationValue == bestFigureMove[4] && Math.random() < 0.5) {
                        bestFigureMove[2] = j;
                        bestFigureMove[3] = i;

                    }


                }


            }

        }
        //System.out.println(bestFigureMove[0]+","+ bestFigureMove[1]+","+bestFigureMove[2]+","+bestFigureMove[3]+","+bestFigureMove[4]);
        return bestFigureMove;
    }

    /**
     * changes the rank for a move regarding check Situations
     * @param board the board
     * @return the calculated value in int
     */
    public int calcCheckValue(Board board,boolean player){
        if (CheckControl.isCheck(player,board))
            return -10000;
        if(CheckControl.isCheck(!player,board)&&!CheckControl.canRelease(!player,board))
            return 1000;
        int a=getBoardValue(player,board);
        if(CheckControl.isCheck(!player,board))
            a=a+5;
        return a;
    }

    /**
     * method to count the value of all figures for a player
     * @param playerColor the player who wants to count the value
     * @return value in int
     */
    public int getBoardValue(boolean playerColor,Board board) {
        int value = 0;

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (!board.getSquare(i,j).isEmpty()) {
                    if (board.getSquare(i,j).getFigure().isColour() == playerColor) {
                        value = value + board.getSquare(i,j).getFigure().getValue();
                    }
                    else {
                        value = value - board.getSquare(i,j).getFigure().getValue();
                    }
                }

            }

        }
        return value;
    }


}

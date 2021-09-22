package chess.view;

import chess.model.Board;

/**
 * Class for displaying the game in console
 */
public class ConsoleView implements Views {

    // public ConsoleView(){}


    /**
     * Prints the Field using the Print Method of the Squares
     */
    @Override
    public void print(Board board){
        for(int i = 0 ; i<8;i++){
            System.out.print(8-i+" ");
            for(int j = 0 ; j<8;j++){
                if(j<7) {
                    if (!board.getSquare(i,j).isEmpty()) {
                        System.out.print(board.getSquare(i,j).getFigure().print() + " ");
                    }else {
                        System.out.print("  ");
                    }
                }else {
                    if (!board.getSquare(i,j).isEmpty()) {
                        System.out.println(board.getSquare(i,j).getFigure().print());
                    }else {
                        System.out.println("");
                    }

                }
            }

        }
        System.out.println("  a b c d e f g h");


    }



    public  void printMessage(String s) {
        System.out.println(s);
    }


}
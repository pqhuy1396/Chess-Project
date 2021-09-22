package chess.model;


import java.util.LinkedList;
import java.util.List;

/**
 * Board Model Class that represents a Board
 */
public class Board {

    Square[][] squares = new Square[8][8];
    List<Figure> beatenFigures = new LinkedList();

    /**
     * Initializes the Board by creating Square Instances with the typical Start Set Up of Chess
     */
    public Board() {
        for (int j=0;j<8;j++) {
            for (int i = 0; i < 8; i++) {
                squares[i][j] = new Square();
            }
        }
        for (int j = 0; j < 8;j++ ) {
            squares[1][j] = new Square(true, new Pawn(false));


        }

        //rook positions
        squares[0][0] = new Square(true, new Rook(false));
        squares[0][7] = new Square(true, new Rook(false));

        //knight positions
        squares[0][1] = new Square(true, new Knight(false));
        squares[0][6] = new Square(true, new Knight(false));

        //bishop positions
        squares[0][2] = new Square(true, new Bishop(false));
        squares[0][5] = new Square(true, new Bishop(false));
        //queen position
        squares[0][3] = new Square(true, new Queen(false));
        //King position
        squares[0][4] = new Square(true, new King(false));


        for (int j = 0; j < 8; j++) {


            squares[6][j] = new Square(true, new Pawn(true));

        }
        //rooks
        squares[7][0] = new Square(true, new Rook(true));
        squares[7][7] = new Square(true, new Rook(true));
        //knights
        squares[7][1] = new Square(true, new Knight(true));
        squares[7][6] = new Square(true, new Knight(true));
        //bishops
        squares[7][2] = new Square(true, new Bishop(true));
        squares[7][5] = new Square(true, new Bishop(true));
        //queen
        squares[7][3] = new Square(true, new Queen(true));
        //King
        squares[7][4] = new Square(true, new King(true));

    }

    /**
     * Constructs a copy of an already existing board
     * @param board the board of which a copy is made
     */
    public Board(Board board) {

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                this.squares[i][j] = new Square(board.squares[i][j]);
            }

        }
    }



    /**
     * Prints the Field using the Print Method of the Squares
     */
    public void print(){
            for(int i = 0 ; i<8;i++){
                System.out.print(8-i+" ");
                for(int j = 0 ; j<8;j++){
                    if(j<7) {
                        System.out.print(squares[i][j].print() + " ");
                    }else {
                        System.out.println(squares[i][j].print());
                    }
                }

        }
        System.out.println(" a  b  c  d  e  f  g  h");


    }

    /**
     * This Method manages the Pawn-Ascension
     *
     * @param board The current board
     * @param s     The current Command in String Form
     */
    public void pawnAscension(Board board, String s) {
        int i = 0;

        while (i < 8) {
            if (!board.squares[7][i].isEmpty() && board.squares[7][i].getFigure().getType() == 0) {
                pawnAscensionBlack(board,s,i);
            } else if ( !board.squares[0][i].isEmpty() && board.squares[0][i].getFigure().getType() == 0) {
                pawnAscensionWhite(board,s,i);
            }
            i++;
        }

    }

    /**
     * Scans what figure the white pawn should transform into
     * @param board The current board
     * @param s The current Command in String Form
     * @param i The x coordinate of the pawn
     */
    public void pawnAscensionBlack(Board board, String s, int i){
        if (s.length() == 6) {
            switch (s.charAt(5)) {
                case 'q':
                    board.squares[7][i].setFigure(new Queen(false));
                case 'b':
                    board.squares[7][i].setFigure(new Bishop(false));
                case 'r':
                    board.squares[7][i].setFigure(new Rook(false));
                case 'n':
                    board.squares[7][i].setFigure(new Knight(false));
                default:
                    board.squares[7][i].setFigure(new Queen(false));
            }
        } else {
            board.squares[7][i].setFigure(new Queen(false));
        }
    }


    /**
     * Scans what figure the black pawn should transform into
     * @param board The current board
     * @param s The current Command in String Form
     * @param i The x coordinate of the pawn
     */
    public void pawnAscensionWhite(Board board, String s, int i){
        if (s.length() == 6) {
            switch (s.charAt(5)) {
                case 'Q':
                    board.squares[0][i].setFigure(new Queen(true));
                case 'B':
                    board.squares[0][i].setFigure(new Bishop(true));
                case 'R':
                    board.squares[0][i].setFigure(new Rook(true));
                case 'N':
                    board.squares[0][i].setFigure(new Knight(true));
                default:
                    board.squares[0][i].setFigure(new Queen(true));
            }
        } else {
            board.squares[0][i].setFigure(new Queen(true));
        }
    }

}

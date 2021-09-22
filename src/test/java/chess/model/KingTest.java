package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Create king and move
 */
public class KingTest {
    King kingBlack = new King(false);
    King kingWhite = new King(true);
    Board board = new Board();
    /**
     * Testing color of king
     */
    @Test
    public void testColor() {
        assertEquals(kingBlack.print(),"k");
        assertEquals(kingWhite.print(),"K");
    };
    /**
     * Testing move of white king with cross, straight, across and wrong move
     */
    @Test
    public void testCheckMove1() {
        int[] crossMove = {1,1,2,2};
        int[] straightMove = {1,2,1,3};
        int[] acrossMove = {1,2,2,2};
        int[] wrongMove = {1,2,3,3};
        int[] moveTop = {2,2,1,1};
        //cross move

        assertTrue(kingWhite.checkMove(crossMove));
        //straight move

        assertTrue(kingWhite.checkMove(straightMove));
        // across move

        assertTrue(kingWhite.checkMove(acrossMove));
        // wrong move

        assertFalse(kingWhite.checkMove(wrongMove));
        //move top

        assertTrue(kingWhite.checkMove(moveTop));
    }

    /**
     * Testing move of black king with cross, straight, across and wrong move
     */
    @Test
    public void testCheckMove2() {
        int[] crossMove = {1,1,2,2};
        int[] straightMove = {1,2,1,3};
        int[] acrossMove = {1,2,2,2};
        int[] wrongMove = {1,2,3,3};
        int[] moveTop = {2,2,1,1};
        //cross move
        assertTrue(kingBlack.checkMove(crossMove));

        //straight move
        assertTrue(kingBlack.checkMove(straightMove));

        // across move
        assertTrue(kingBlack.checkMove(acrossMove));

        // wrong move
        assertFalse(kingBlack.checkMove(wrongMove));

        //move top
        assertTrue(kingBlack.checkMove(moveTop));

    }

    /**
     * Initialisation
     */
    @BeforeEach
    public void init(){
        //boolean firstMove = true;
        for(int i = 0; i <= 7 ; i++){
            this.board.squares[0][i].removeFigure();
            this.board.squares[7][i].removeFigure();
        }
        this.board.squares[7][4] = new Square(false,kingBlack);
        this.board.squares[0][4]= new Square(true,kingWhite);

        this.board.squares[7][0] = new Square(true, new Rook(true));
        this.board.squares[7][7] = new Square(true, new Rook(true));

        this.board.squares[0][0] = new Square(true, new Rook(false));
        this.board.squares[0][7] = new Square(true, new Rook(false));

    }
    /**
     * Testing special move of king
     */
    @Test
    public void testSpecialMove() {
        //small castling black
        int[] smallCastlingBlack = {4,0,6,0};
        //large castling black
        int[] largeCastlingBlack = {4,0,2,0};
        //small castling white
        int[] smallCastlingWhite = {4,7,6,7};
        //large castling white
        int[] largeCastlingWhite = {4,7,2,7};
        //normal move
        int[] moveTop = {2,2,1,1};
        //check small castling
        assertTrue(kingWhite.specialMove(smallCastlingBlack,this.board));
        assertTrue(kingWhite.specialMove(largeCastlingBlack,this.board));
        assertTrue(kingBlack.specialMove(smallCastlingWhite,this.board));
        assertTrue(kingBlack.specialMove(largeCastlingWhite,this.board));
        assertEquals(kingWhite.specialMove(moveTop,this.board),kingWhite.checkMove(moveTop));
    }

    /**
     * Test for creating a copy of an existing King
     */
    @Test
    public void testCopyConstructor(){
        King king1 = new King(false);
        King king2 = king1;
        assertEquals(king1.isColour(),king2.isColour());
        king1.setColour(true);
        assertEquals(king1.isColour(),king2.isColour());
        king2=new King(king1);
        king2.setColour(false);
        assertNotEquals(king1.isColour(),king2.isColour());
    }

    /**
     * Test for Getter for the type variable
     */
    @Test
    public void testGetType(){
        King king1 = new King (false);
        assertEquals(king1.getType(),40);
        king1.setType(4);
        assertNotEquals(king1.getType(),40);
        assertEquals(king1.getType(),4);
    }
    /**
     * test for print method
     */
    @Test
    public void testPrint(){
        King king1 = new King(true);
        King king2 = new King(false);
        assertEquals(king1.print(),"K");
        assertEquals(king2.print(),"k");
    }
    /**
     * test for print1 method
     */
    @Test
    public void testPrint1(){
        King king1 = new King(true);
        King king2 = new King(false);
        assertEquals(king2.print1(),"\u265A");
        assertEquals(king1.print1(),"\u2654");


    }


}

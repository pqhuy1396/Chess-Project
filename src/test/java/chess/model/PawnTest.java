package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Testing the Example class with its arbitrary arithmetical computations
 */
public class PawnTest {
    /**
     * Create pawn and move
     */
    Pawn pawnBlack = new Pawn(false);
    Pawn pawnWhite = new Pawn(true);
    Board unblockedBoard = new Board();

    /**
     * Initialisation
     */
    @BeforeEach
    public void init() {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                unblockedBoard.squares[i][j].removeFigure();
            }
        }
    }
    /**
     * test color pawn
     */
    @Test
    public void testColor() {
        assertEquals(pawnBlack.print(),"\u2659");
        assertEquals(pawnWhite.print(),"\u265F");
    }
    /**
     * test move of pawn
     */
    @Test
    public void testCheckMove() {
        int[] straightTwoStepMoveWhite = {1,2,1,4};
        int[] straightOneStepMoveWhite = {1,2,1,3};
        int[] wrongMove = {1,2,3,4};
        int[] straightTwoStepMoveBlack = {2,3,2,1};
        int[] straightOneStepMoveBlack = {2,2,2,1};
        int[] checkDouble = {0,6,0,4};
        //test two step of pawn white go to straight
        //assertEquals(pawnBlack.checkMove(straightTwoStepMoveWhite),true);
        assertFalse(pawnWhite.checkMove(straightTwoStepMoveWhite));
        //test wrong move of pawn
        assertFalse(pawnBlack.checkMove(wrongMove));
        assertFalse(pawnWhite.checkMove(wrongMove));
        //test two step of pawn black go to straight
        assertFalse(pawnBlack.checkMove(straightTwoStepMoveBlack));
        //assertEquals(pawnWhite.checkMove(straightTwoStepMoveBlack),true);
        //test one step of pawn black go to straight
        assertFalse(pawnBlack.checkMove(straightOneStepMoveBlack));
        assertTrue(pawnWhite.checkMove(straightOneStepMoveBlack));
        //test one step of pawn white go to straight
        assertTrue(pawnBlack.checkMove(straightOneStepMoveWhite));
        assertFalse(pawnWhite.checkMove(straightOneStepMoveWhite));
        //checkDouble
        assertTrue(pawnWhite.checkMove(checkDouble));
        assertFalse(pawnBlack.checkMove(checkDouble));
    }

    /**
     * Test for creating a copy of an existing Pawn
     */
    @Test
    public void testCopyConstructor(){
        Pawn pawn1 = new Pawn(false);
        Pawn pawn2 = pawn1;
        assertEquals(pawn1.isColour(),pawn2.isColour());
        pawn1.setColour(true);
        assertEquals(pawn1.isColour(),pawn2.isColour());
        pawn2=new Pawn(pawn1);
        pawn2.setColour(false);
        assertNotEquals(pawn1.isColour(),pawn2.isColour());
    }

    /**
     * Test for Getter for the type variable
     */
    @Test
    public void testGetType(){
        Pawn pawn1 = new Pawn(false);
        assertEquals(pawn1.getType(),0);
        pawn1.setType(10);
        assertNotEquals(pawn1.getType(),0);
        assertEquals(pawn1.getType(),10);
    }

    /**
     * Test for the capture method
     */
    @Test
    public void testCapture(){
        Pawn pawn1 = new Pawn(true);
        Pawn pawn2 = new Pawn(false);
        Pawn pawn3 = new Pawn(false);
        unblockedBoard.squares[7][4].setFigure(pawn1);
        unblockedBoard.squares[6][3].setFigure(pawn2);
        unblockedBoard.squares[6][5].setFigure(pawn3);
        int[] move1 = new int[]{4,7,3,6};
        int[] move2 = new int[]{4,7,5,6};
        int[] move3 = new int[]{3,6,4,7};
        int[] move4 = new int[]{5,6,4,7};
        assertTrue(pawn1.captureWhite(move1,unblockedBoard));
        assertTrue(pawn1.captureWhite(move2,unblockedBoard));
        assertTrue(pawn2.captureBlack(move3,unblockedBoard));
        assertTrue(pawn3.captureBlack(move4,unblockedBoard));

        assertTrue(pawn1.specialMove(move1,unblockedBoard));
        assertTrue(pawn2.specialMove(move3,unblockedBoard));

    }

    /**
     * Test for the enPassant method
     */
    @Test
    public void testEnPassant(){
        int[] enPassantWhiteMove = {3,7,4,6};
        Pawn pawn1 = new Pawn(true);
        Pawn pawn2 = new Pawn(true);
        Pawn pawn3 = new Pawn(false);
        Pawn pawn4 = new Pawn(false);
        unblockedBoard.squares[3][7].setFigure(pawn1);
        unblockedBoard.squares[6][5].setFigure(pawn2);
        unblockedBoard.squares[2][1].setFigure(pawn3);
        unblockedBoard.squares[7][5].setFigure(pawn4);

        assertFalse(pawn2.enPassantWhite(enPassantWhiteMove,unblockedBoard));
        assertFalse(pawn4.enPassantBlack(enPassantWhiteMove,unblockedBoard));
    }

}


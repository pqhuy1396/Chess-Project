package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//test
/**
 * Testing the Example class with its arbitrary arithmetical computations
 */
public class KnightTest {
    Knight knightBlack = new Knight(false);
    Knight knightWhite = new Knight(true);

    /**
     * Testing color of knight
     */
    @Test
    public void testColor() {
        assertEquals(knightBlack.print(), "n");
        assertEquals(knightWhite.print(), "N");
    }

    /**
     * Testing move of knight
     */
    @Test
    public void testCheckMove1() {
        int[] straightMove = {1, 1, 2, 3};
        int[] acrossMove = {2, 5, 1, 3};
        int[] wrongMove = {1, 1, 2, 1};
        //test move of king go to straight
        assertTrue(knightBlack.checkMove(straightMove));
        assertTrue(knightWhite.checkMove(straightMove));
        //test across move of king
        assertTrue(knightBlack.checkMove(acrossMove));
        assertTrue(knightWhite.checkMove(acrossMove));

    }


    /**
     * Testing wrong moves of knight
     */
    @Test
    public void testCeckMoveWrong() {
        int[] straightMove = {1, 1, 2, 3};
        int[] acrossMove = {2, 5, 1, 3};
        int[] wrongMove = {1, 1, 2, 1};

        //test wrong move of king
        assertFalse(knightBlack.checkMove(wrongMove));
        assertFalse(knightWhite.checkMove(wrongMove));
    }

    /**
     * Test for creating a copy of an existing Knight
     */
    @Test
    public void testCopyConstructor() {
        Knight knight1 = new Knight(false);
        Knight knight2 = knight1;
        assertEquals(knight1.isColour(), knight2.isColour());
        knight1.setColour(true);
        assertEquals(knight1.isColour(), knight2.isColour());
        knight2 = new Knight(knight1);
        knight2.setColour(false);
        assertNotEquals(knight1.isColour(), knight2.isColour());
    }

    /**
     * Test for Getter for the type variable
     */
    @Test
    public void testGetType() {
        Knight knight1 = new Knight(false);
        assertEquals(knight1.getType(), 3);
        knight1.setType(4);
        assertNotEquals(knight1.getType(), 2);
        assertEquals(knight1.getType(), 4);
    }

    /**
     * Test for the specialMove method
     */
    @Test
    public void testSpecialMove() {
        Board board1 = new Board();
        int[] specialMove = new int[4];
        specialMove = new int[]{1, 2, 3, 4};
        Knight knight1 = new Knight(false);
        assertFalse(knight1.specialMove(specialMove, board1));

    }

    /**
     * test for print method
     */
    @Test
    public void testPrint() {
        Knight knight1 = new Knight(true);
        Knight knight2 = new Knight(false);
        assertEquals(knight2.print(), "n");
        assertEquals(knight1.print(), "N");
    }

    /**
     * test for print1 method
     */
    @Test
    public void testPrint1() {
        Knight knight1 = new Knight(true);
        Knight knight2 = new Knight(false);
        assertEquals(knight2.print1(), "\u265E");
        assertEquals(knight1.print1(), "\u2658");


    }



}

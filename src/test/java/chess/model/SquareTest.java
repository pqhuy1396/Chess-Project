package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the class Square
 */
public class SquareTest {
    Rook rook1 = new Rook(false);
    Bishop bishop1 = new Bishop(false);
    Square rookSquare = new Square(true, rook1);
    Square emptySquare = new Square();


    /**
     * Tests whether isEmpty works
     */
    @Test
    public void test1() {
        assertFalse(rookSquare.isEmpty());
        assertTrue(emptySquare.isEmpty());
    }

    /**
     * Tests whether getFigure works
     */
    @Test
    public void test2() {
        assertEquals(rook1, rookSquare.getFigure());
        assertNotEquals(bishop1, rookSquare.getFigure());
    }

    /**
     * Tests whether removeFigure works
     */
    @Test
    public void test3() {
        assertFalse(rookSquare.isEmpty());
        rookSquare.removeFigure();
        assertTrue(rookSquare.isEmpty());
    }

    /**
     * Tests whether isEmpty works for other figure
     */
    @Test
    public void test4() {
        assertFalse(rookSquare.isEmpty());
        assertTrue(emptySquare.isEmpty());
    }

    /**
     * Tests whether print works
     */
    @Test
    public void test6() {
        //Square square = new Square(rookSquare);
        assertEquals(emptySquare.print(), "  ");
        assertEquals(rookSquare.print(), "r");
    }

    /**
     * Tests whether allowedFigureMove for rook works
     */
    @Test
    public void test7() {
        int[] oder = {1, 3, 1, 5};
        assertTrue(rookSquare.allowedFigureMove(oder));
    }



    /**
     * Tests whether setFigure works for null figure
     */

    @Test
    public void test9() {
        assertFalse(rookSquare.isEmpty());
        rookSquare.setFigure(null);
        assertTrue(rookSquare.isEmpty());
    }
}
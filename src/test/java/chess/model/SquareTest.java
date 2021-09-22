package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the class Square
 */
public class SquareTest {
    Rook rook1 =new Rook(false);
    Bishop bishop1 =new Bishop(false);
    Square rookSquare = new Square(true, rook1);
    Square emptySquare = new Square();




    @Test
    public void test1(){
        assertFalse(rookSquare.isEmpty());
        assertTrue(emptySquare.isEmpty());
    }
    @Test
    public void test2(){
        assertEquals(rook1,rookSquare.getFigure());
        assertNotEquals(bishop1,rookSquare.getFigure());
    }
    @Test
    public void test3(){
        assertFalse(rookSquare.isEmpty());
        rookSquare.removeFigure();
        assertTrue(rookSquare.isEmpty());
    }
    @Test
    public void test4(){
        assertFalse(rookSquare.isEmpty());
        assertTrue(emptySquare.isEmpty());
    }
    @Test
    public void test5(){
        assertFalse(rookSquare.isEmpty());
        assertTrue(emptySquare.isEmpty());
    }
    @Test
    public void test6(){
        //Square square = new Square(rookSquare);
        assertEquals(emptySquare.print(),"  ");
        assertEquals(rookSquare.print(),"r");
    }
    @Test
    public void test7(){
        int[] oder =  {1,3,1,5};
        assertTrue(rookSquare.allowedFigureMove(oder));
    }
    @Test
    public void test8(){
        int[] oder =  {0,0,0,5};
        Board board = new Board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                board.squares[i][j].removeFigure();
            }
        }
        board.squares[0][0].setFigure(rook1);
        board.squares[1][1].setFigure(null);
        int temp = 10;
        assertTrue(rookSquare.threatMove(oder,temp,board));
    }
}

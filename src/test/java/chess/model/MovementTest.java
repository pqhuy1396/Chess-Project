package chess.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the class movement
 */
public class MovementTest {
    Movement movement = new Movement();
    Board blockedBoard= new Board();
    Board unblockedBoard = new Board();
    int[] testmove = new int[4];


    /**
     * Initialisation
     */
    @BeforeEach
    public void init() {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                blockedBoard.squares[i][j] = new Square(true,new Pawn(true));
                unblockedBoard.squares[i][j] = new Square(true,new Pawn(true));
            }
        }
        testmove[0]=4;
        testmove[1]=4;

    }

    /**
     * Tests correct behavior of scanStraightY in downside direction
     * and tests if Pawn Queen and rook use the right method to scan
     */
   @Test
    public void test1(){
       unblockedBoard.squares[5][4].removeFigure();
       unblockedBoard.squares[6][4].removeFigure();
       unblockedBoard.squares[7][4].removeFigure();
       assertTrue(movement.scanStraightY(4,7,4,unblockedBoard));
       assertFalse(movement.scanStraightY(4,7,4,blockedBoard));
       testmove[2]=4;
       testmove[3]=7;
       assertTrue(movement.freePath(0,testmove,unblockedBoard));
       assertTrue(movement.freePath(5,testmove,unblockedBoard));
       assertTrue(movement.freePath(10,testmove,unblockedBoard));

   }
    /**
     * Tests correct behavior of scanStraightY in upside direction
     * and tests if Pawn Queen and rook use the right method to scan
     */
    @Test
    public void test2(){
        unblockedBoard.squares[3][4].removeFigure();
        unblockedBoard.squares[2][4].removeFigure();
        unblockedBoard.squares[1][4].removeFigure();
        assertTrue(movement.scanStraightY(4,4,1,unblockedBoard));
        assertFalse(movement.scanStraightY(4,4,1,blockedBoard));
        testmove[2]=4;
        testmove[3]=1;
        assertTrue(movement.freePath(0,testmove,unblockedBoard));
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(10,testmove,unblockedBoard));


    }
    /**
     * Tests correct behavior of scanStraightX in right direction
     * and tests if Queen and rook use the right method to scan
     */
    @Test
    public void test3(){
        unblockedBoard.squares[4][5].removeFigure();
        unblockedBoard.squares[4][6].removeFigure();
        unblockedBoard.squares[4][7].removeFigure();
        assertTrue(movement.scanStraightX(4,7,4,unblockedBoard));
        assertFalse(movement.scanStraightX(4,7,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=4;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(10,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));


    }
    /**
     * Tests correct behavior of scanStraightX in left direction
     * and tests if Queen and rook use the right method to scan
     */
    @Test
    public void test4(){
        unblockedBoard.squares[4][3].removeFigure();
        unblockedBoard.squares[4][2].removeFigure();
        unblockedBoard.squares[4][1].removeFigure();
        assertTrue(movement.scanStraightX(4,4,1,unblockedBoard));
        assertFalse(movement.scanStraightX(4,4,1,blockedBoard));
        testmove[2]=1;
        testmove[3]=4;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(10,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalUp from upside right to downside left
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test5(){
        unblockedBoard.squares[5][3].removeFigure();
        unblockedBoard.squares[6][2].removeFigure();
        unblockedBoard.squares[7][1].removeFigure();
        assertTrue(movement.scanDiagonalUp(3,1,7,unblockedBoard));
        assertFalse(movement.scanDiagonalUp(3,1,7,blockedBoard));
        testmove[2]=1;
        testmove[3]=7;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(2,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalUp from downside left to upside right
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test6(){
        unblockedBoard.squares[3][5].removeFigure();
        unblockedBoard.squares[2][6].removeFigure();
        unblockedBoard.squares[1][7].removeFigure();
        assertTrue(movement.scanDiagonalUp(3,4,4,unblockedBoard));
        assertFalse(movement.scanDiagonalUp(3,4,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=1;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(2,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalDown from downside right to upside left
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test7(){
        unblockedBoard.squares[3][3].removeFigure();
        unblockedBoard.squares[2][2].removeFigure();
        unblockedBoard.squares[1][1].removeFigure();
        assertTrue(movement.scanDiagonalDown(3,1,1,unblockedBoard));
        assertFalse(movement.scanDiagonalDown(3,1,1,blockedBoard));
        testmove[2]=1;
        testmove[3]=1;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(2,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalDown from upside left to downside right
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test8(){
        testmove[2]=7;
        testmove[3]=7;

        unblockedBoard.squares[5][5].removeFigure();
        unblockedBoard.squares[6][6].removeFigure();
        unblockedBoard.squares[7][7].removeFigure();
        assertTrue(movement.scanDiagonalDown(3,4,4,unblockedBoard));
        assertFalse(movement.scanDiagonalDown(3,4,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=7;
        assertTrue(movement.freePath(5,testmove,unblockedBoard));
        assertTrue(movement.freePath(2,testmove,unblockedBoard));
        assertFalse(movement.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of freePath for Knight which should always return true
     */
    @Test
    public void test10(){
        testmove[2]=1;
        testmove[3]=1;
        assertTrue(movement.freePath(3,testmove,unblockedBoard));
        testmove[2]=7;
        testmove[3]=7;
        assertTrue(movement.freePath(3,testmove,unblockedBoard));
        testmove[2]=1;
        testmove[3]=7;
        assertTrue(movement.freePath(3,testmove,unblockedBoard));
        testmove[2]=7;
        testmove[3]=1;
        assertTrue(movement.freePath(3,testmove,unblockedBoard));
    }

}

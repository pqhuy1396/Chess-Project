package chess.controller;



import chess.model.Board;

import chess.model.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the class movement
 */
public class ScannerTest {
    Scanner scanner = new Scanner();
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
                blockedBoard.setSquare(i,j,new Pawn(true));
                unblockedBoard.setSquare(i,j, new Pawn(true));
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
        unblockedBoard.getSquare(5,4).removeFigure();
        unblockedBoard.getSquare(6,4).removeFigure();
        unblockedBoard.getSquare(7,4).removeFigure();
        assertTrue(scanner.scanStraightY(4,7,4,unblockedBoard));
        assertFalse(scanner.scanStraightY(4,7,4,blockedBoard));
        testmove[2]=4;
        testmove[3]=7;
        assertTrue(scanner.freePath(0,testmove,unblockedBoard));
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(10,testmove,unblockedBoard));

    }
    /**
     * Tests correct behavior of scanStraightY in upside direction
     * and tests if Pawn Queen and rook use the right method to scan
     */
    @Test
    public void test2(){
        unblockedBoard.getSquare(3,4).removeFigure();
        unblockedBoard.getSquare(2,4).removeFigure();
        unblockedBoard.getSquare(1,4).removeFigure();
        assertTrue(scanner.scanStraightY(4,4,1,unblockedBoard));
        assertFalse(scanner.scanStraightY(4,4,1,blockedBoard));
        testmove[2]=4;
        testmove[3]=1;
        assertTrue(scanner.freePath(0,testmove,unblockedBoard));
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(10,testmove,unblockedBoard));


    }
    /**
     * Tests correct behavior of scanStraightX in right direction
     * and tests if Queen and rook use the right method to scan
     */
    @Test
    public void test3(){
        unblockedBoard.getSquare(4,5).removeFigure();
        unblockedBoard.getSquare(4,6).removeFigure();
        unblockedBoard.getSquare(4,7).removeFigure();
        assertTrue(scanner.scanStraightX(4,7,4,unblockedBoard));
        assertFalse(scanner.scanStraightX(4,7,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=4;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(10,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));


    }
    /**
     * Tests correct behavior of scanStraightX in left direction
     * and tests if Queen and rook use the right method to scan
     */
    @Test
    public void test4(){
        unblockedBoard.getSquare(4,3).removeFigure();
        unblockedBoard.getSquare(4,2).removeFigure();
        unblockedBoard.getSquare(4,1).removeFigure();
        assertTrue(scanner.scanStraightX(4,4,1,unblockedBoard));
        assertFalse(scanner.scanStraightX(4,4,1,blockedBoard));
        testmove[2]=1;
        testmove[3]=4;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(10,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalUp from upside right to downside left
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test5(){
        unblockedBoard.getSquare(5,3).removeFigure();
        unblockedBoard.getSquare(6,2).removeFigure();
        unblockedBoard.getSquare(7,1).removeFigure();
        assertTrue(scanner.scanDiagonalUp(3,1,7,unblockedBoard));
        assertFalse(scanner.scanDiagonalUp(3,1,7,blockedBoard));
        testmove[2]=1;
        testmove[3]=7;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(2,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalUp from downside left to upside right
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test6(){
        unblockedBoard.getSquare(3,5).removeFigure();
        unblockedBoard.getSquare(2,6).removeFigure();
        unblockedBoard.getSquare(1,7).removeFigure();
        assertTrue(scanner.scanDiagonalUp(3,4,4,unblockedBoard));
        assertFalse(scanner.scanDiagonalUp(3,4,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=1;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(2,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalDown from downside right to upside left
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test7(){
        unblockedBoard.getSquare(3,3).removeFigure();
        unblockedBoard.getSquare(2,2).removeFigure();
        unblockedBoard.getSquare(1,1).removeFigure();
        assertTrue(scanner.scanDiagonalDown(3,1,1,unblockedBoard));
        assertFalse(scanner.scanDiagonalDown(3,1,1,blockedBoard));
        testmove[2]=1;
        testmove[3]=1;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(2,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of scanDiagonalDown from upside left to downside right
     * and tests if Queen and Bishop use the right method to scan
     */
    @Test
    public void test8(){
        testmove[2]=7;
        testmove[3]=7;

        unblockedBoard.getSquare(5,5).removeFigure();
        unblockedBoard.getSquare(6,6).removeFigure();
        unblockedBoard.getSquare(7,7).removeFigure();
        assertTrue(scanner.scanDiagonalDown(3,4,4,unblockedBoard));
        assertFalse(scanner.scanDiagonalDown(3,4,4,blockedBoard));
        testmove[2]=7;
        testmove[3]=7;
        assertTrue(scanner.freePath(5,testmove,unblockedBoard));
        assertTrue(scanner.freePath(2,testmove,unblockedBoard));
        assertFalse(scanner.freePath(5,testmove,blockedBoard));

    }
    /**
     * Tests correct behavior of freePath for Knight which should always return true
     */
    @Test
    public void test10(){
        testmove[2]=1;
        testmove[3]=1;
        assertTrue(scanner.freePath(3,testmove,unblockedBoard));
        testmove[2]=7;
        testmove[3]=7;
        assertTrue(scanner.freePath(3,testmove,unblockedBoard));
        testmove[2]=1;
        testmove[3]=7;
        assertTrue(scanner.freePath(3,testmove,unblockedBoard));
        testmove[2]=7;
        testmove[3]=1;
        assertTrue(scanner.freePath(3,testmove,unblockedBoard));
    }

}

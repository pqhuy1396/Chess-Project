package chess.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test Class for InputHandler
 */
public class InputHandlerTest {
    /**
     * initialize different variables
     */

    int[] validExecutable = {0, 7, 7, 0};

    int[] defaultExecutable = {0, 0, 0, 0};
    char number0 = '0';
    char number1 = '1';
    char number65 = 'A';
    String validMove = "a1-h8";
    String inValidMove = "j2-g5";
    String inValidMove1 = "j9-k9";
    String inValidCommand = "a2-bb3";


    /**
     * Testing the validX method
     */
    @Test
    public void test1() {
        assertTrue(InputHandler.validX('a'));
        assertFalse(InputHandler.validX('x'));
        assertFalse(InputHandler.validX(number0));
        assertFalse(InputHandler.validX(number65));
    }

    /**
     * Testing the validY method
     */
    @Test
    public void test2() {
        assertFalse(InputHandler.validY(number0));
        assertTrue(InputHandler.validY(number1));
        assertFalse(InputHandler.validY('a'));
        assertFalse(InputHandler.validY('x'));
        assertFalse(InputHandler.validY(number65));
    }

    /**
     * Testing the validPosition method
     */
    @Test
    public void test3() {
        assertTrue(InputHandler.validPosition('a', number1));
        assertFalse(InputHandler.validPosition(number1, 'a'));
    }

    /**
     * Testing the validMove method
     */
    @Test
    public void test4() {
        assertTrue(InputHandler.validMove(validMove));
        assertFalse(InputHandler.validMove(inValidMove));

    }

    /**
     * Testing the validCommand method
     */
    @Test
    public void test5() {
        assertTrue(InputHandler.validCommand(validMove));
        assertFalse(InputHandler.validCommand(inValidCommand));
        assertFalse(InputHandler.validCommand("validMove"));

    }

    /**
     * Testing the convertX method
     */
    @Test
    public void test6() {
        assertEquals(InputHandler.convertX('a'), 0);
        assertEquals(InputHandler.convertX('b'), 1);
        assertEquals(InputHandler.convertX('c'), 2);
        assertEquals(InputHandler.convertX('d'), 3);
        assertEquals(InputHandler.convertX('e'), 4);
    }

    /**
     * Testing the convertX method
     */
    @Test
    public void test7() {
        assertEquals(InputHandler.convertX('f'), 5);
        assertEquals(InputHandler.convertX('g'), 6);
        assertEquals(InputHandler.convertX('h'), 7);
        assertNotEquals(InputHandler.convertX('h'), 8);
    }

    /**
     * Test 1 for the convertY method
     */
    @Test
    public void test8() {
        assertEquals(InputHandler.convertY(number1), 7);
        assertEquals(InputHandler.convertY('2'), 6);
        assertEquals(InputHandler.convertY('3'), 5);
        assertEquals(InputHandler.convertY('4'), 4);
        assertEquals(InputHandler.convertY('5'), 3);

    }

    /**
     * Test 2 for the convertY method
     */
    @Test
    public void test9() {
        assertEquals(InputHandler.convertY('6'), 2);
        assertEquals(InputHandler.convertY('7'), 1);
        assertEquals(InputHandler.convertY('8'), 0);
        assertNotEquals(InputHandler.convertY('8'), 8);

    }

    /**
     * Testing the convertCommand method
     */
    @Test
    public void test10() {
        assertArrayEquals(InputHandler.convertCommand(validMove), validExecutable);
        assertArrayEquals(InputHandler.convertCommand(inValidMove1), defaultExecutable);
        assertArrayEquals(InputHandler.convertCommand(inValidMove1), defaultExecutable);

    }


    /**
     * Test 1 for valid inputs
     */
    @Test
    public void test11() {
        assertTrue(InputHandler.validTransform('Q'));
        assertTrue(InputHandler.validTransform('\u0000'));
        assertTrue(InputHandler.validTransform('N'));
        assertTrue(InputHandler.validTransform('R'));

    }

    /**
     * Test 2 for valid inputs
     */
    @Test
    public void test12() {
        assertTrue(InputHandler.validRedoRequest("redo"));
        assertTrue(InputHandler.validUndoRequest("undo"));
        assertTrue(InputHandler.validListRequest("beaten"));

    }

}

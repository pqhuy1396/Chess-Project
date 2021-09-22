package chess.controller;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the ConsoleInput Class
 */
public class ConsoleInputTest {

    /**
     * initialize different variables
     */
    char a = 'a';
    char b = 'b';
    char x = 'x';
    int[] validExecuteable = {0,7,7,0};
    int[]  defaultExecuteable = {0,0,0,0};
    char number0 = '0';
    char number1= '1';
    char number65 = 'A';
    String validMove ="a1-h8";
    String inValidMove = "j2-g5";
    String inValidMove1 = "j9-k9";
    String inValidCommand = "a2-bb3";
    ConsoleInput input = new ConsoleInput();

    /**
     * Testing the validX method
     */
    @Test
    public void test1(){
        assertTrue(input.validX(a));
        assertFalse(input.validX(x));
        assertFalse(input.validX(number0));
        assertFalse(input.validX(number65));
    }
    /**
     * Testing the validY method
     */
    @Test
    public void test2(){
        assertFalse(input.validY(number0));
        assertTrue(input.validY(number1));
        assertFalse(input.validY(a));
        assertFalse(input.validY(x));
        assertFalse(input.validY(number65));
    }
    /**
     * Testing the validPosition method
     */
    @Test
    public void test3(){
        assertTrue(input.validPosition(a,number1));
        assertFalse(input.validPosition(number1,a));
    }
    /**
     * Testing the validMove method
     */
    @Test
    public void test4(){
        assertTrue(input.validMove(validMove));
        assertFalse(input.validMove(inValidMove));

    }
    /**
     * Testing the validCommand method
     */
    @Test
    public void test5(){
        assertTrue(input.validCommand(validMove));
        assertFalse(input.validCommand(inValidCommand));
        assertFalse(input.validCommand("validMove"));

    }
    /**
     * Testing the convertX method
     */
    @Test
    public void test6(){
        assertEquals(input.convertX(a),0);
        assertEquals(input.convertX(b),1);
        assertEquals(input.convertX('c'),2);
        assertEquals(input.convertX('d'),3);
        assertEquals(input.convertX('e'),4);
    }
    /**
     * Testing the convertX method
     */
    @Test
    public void test7(){
        assertEquals(input.convertX('f'),5);
        assertEquals(input.convertX('g'),6);
        assertEquals(input.convertX('h'),7);
        assertNotEquals(input.convertX('h'),8);
    }
    /**
     * Test 1 for the convertY method
     */
    @Test
    public void test8(){
        assertEquals(input.convertY(number1),7);
        assertEquals(input.convertY('2'),6);
        assertEquals(input.convertY('3'),5);
        assertEquals(input.convertY('4'),4);
        assertEquals(input.convertY('5'),3);

    }

    /**
     * Test 2 for the convertY method
     */
    @Test
    public void test9(){
        assertEquals(input.convertY('6'),2);
        assertEquals(input.convertY('7'),1);
        assertEquals(input.convertY('8'),0);
        assertNotEquals(input.convertY('8'),8);

    }
    /**
     * Testing the convertCommand method
     */
    @Test
    public void test10(){
        assertTrue(Arrays.equals(input.convertCommand(validMove), validExecuteable));
        assertFalse(Arrays.equals(input.convertCommand(validMove), defaultExecuteable));
        assertTrue(Arrays.equals(input.convertCommand(inValidMove1), defaultExecuteable));
    }

    /**
     * Test 1 for valid inputs
     */
    @Test
    public void test11(){
        assertTrue(input.validTransform('q'));
        assertTrue(input.validTransform('\u0000'));
        assertTrue(input.validTransform('n'));
        assertTrue(input.validTransform('r'));

    }

    /**
     * Test 2 for valid inputs
     */
    @Test
    public void test12(){
        assertTrue(input.validTransform('b'));
        assertFalse(input.validTransform('c'));
        assertTrue(input.validListRequest("beaten"));
    }

}

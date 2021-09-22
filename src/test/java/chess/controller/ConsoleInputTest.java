package chess.controller;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the ConsoleInput Class
 */
public class ConsoleInputTest {




    /**
     * test for listenInput
     */
    @Test
    public void test0() {
        ConsoleInput input = new ConsoleInput();

        String input1 = "add 5";
        InputStream in = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in);

        assertEquals("add 5", input.listenInput());
    }



}

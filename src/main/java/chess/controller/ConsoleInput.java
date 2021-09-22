package chess.controller;

import java.util.Scanner;

/**
 *Class ConsoleInput, makes possible to enter a command via console
 */
public class ConsoleInput implements Input {



   // }

    /**
     * method which expect an input via console
     * @return whatever typed into console
     */
    @Override
    public String listenInput() {

        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


}

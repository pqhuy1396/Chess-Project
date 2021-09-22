package chess;


import chess.controller.ConsoleInput;

import chess.controller.ConsoleOnlineGame;
import chess.controller.GameSetup;
import chess.controller.GuiControl;
import java.io.IOException;
import java.util.Arrays;



/**
 * The main class
 */
public class Main   {

    /**
     * the main method
     *
     * @param args starts the game without gui if "--no-gui" is given
     */
    public static void main(String[] args) throws IOException {

        boolean simple = Arrays.asList(args).contains("--simple");
        boolean nogui = Arrays.asList(args).contains("--no-gui");

        GameSetup setup = new GameSetup();


        if (simple) {
            setup.HumanHuman();
        } else if (nogui) {
            ConsoleInput input = new ConsoleInput();

            boolean exit = false;
            while (!exit) {
        System.out.println("choose opponent (either 'human' or 'computer' or 'online') or exit with 'exit'");
                String choose = input.listenInput();

                if (choose.equals("exit")) {
                    exit = true;
                } else if (choose.equals("human")) {
                    setup.HumanHuman();

                } else if (choose.equals("computer")) {
                    setup.HumanAi();
                } else if (choose.equals("aiaiai")) {
                    setup.AiAi();

                }else if( choose.equals("online")){
                    ConsoleOnlineGame consoleOnlineGame = new ConsoleOnlineGame();
                    consoleOnlineGame.run();
                }

        }


        }
        else {
            GuiControl guiControl = new GuiControl();
            guiControl.activateGui();
    }

    }
}

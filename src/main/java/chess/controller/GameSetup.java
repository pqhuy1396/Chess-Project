package chess.controller;

import chess.model.AiPlayer;

import chess.model.Game;
import chess.model.HumanPlayer;
import chess.view.ConsoleView;
import chess.view.GuiView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * Class to create different types of games
 */
public class GameSetup {
ConsoleInput input = new ConsoleInput();
boolean run = true;
ConsoleView view = new ConsoleView();

    /**
     * The Setup for the Human vs Human Online Game
     * @param isFirstPlayer The chosen Player Color
     * @param in The DataInputstream
     * @param out The DataOutputStream
     * @throws  IOException check exception
     */
    public void HumanHumanOnlineConsole(boolean isFirstPlayer, DataInputStream in, DataOutputStream out) throws IOException {
        HumanPlayer player1 = new HumanPlayer(input);
        HumanPlayer player2 = new HumanPlayer(input);
        Game game1 = new Game(player1, player2);
        GameControls gameControls1 = new GameControls(game1, view, input);
        while (run) {
            gameControls1.runGame(isFirstPlayer, in, out);
            if (!gameControls1.isRun())
                run = false;

        }
    }

    /**
     * method to create human vs human game in console
     */
    public void HumanHuman() {
        HumanPlayer player1 = new HumanPlayer(input);
        HumanPlayer player2 = new HumanPlayer(input);
        Game game1 = new Game(player1, player2);
        GameControls gameControls1 = new GameControls(game1, view, input);
        while (run) {
            gameControls1.runGame(null,true);
            if (!gameControls1.isRun())
                run = false;

        }
    }



    /**
     * method to create human vs Ai game in console
     */
    public void HumanAi(){
        System.out.println("if you want to change AI depth type one number between 1-4 first");

        System.out.println("choose colour (either 'white' or 'black') or exit with 'exit'");
        String colour1 = input.listenInput();
        if(colour1.equals("1")){
            GuiVar.setDepth(1);
        }
        else if(colour1.equals("2")){
            GuiVar.setDepth(2);
        }
        else if(colour1.equals("3")){
            GuiVar.setDepth(3);
        }
        else if(colour1.equals("4")){
            GuiVar.setDepth(4);
        }

            HumanPlayer player1vsAI = new HumanPlayer(input);
        AiPlayer player2AI = new AiPlayer();
        while (!(colour1.equals("exit"))) {
            System.out.println("choose colour (either 'white' or 'black') or exit with 'exit'");
            String colour = input.listenInput();
            if (colour.equals("white")) {
                Game game2 = new Game(player1vsAI, player2AI);
                GameControls gameControls2 = new GameControls(game2, view, input);
                GuiVar.setGameAi(true);
                while (run) {
                    gameControls2.runGame(null,true);
                    if (!gameControls2.isRun())
                        run = false;

                }
                GuiVar.setGameAi(false);


            }
            else if (colour.equals("black")) {
                Game game3 = new Game(player2AI, player1vsAI);
                GameControls gameControls3 = new GameControls(game3, view, input);
                GuiVar.setGameAi(true);

                while (run) {
                    gameControls3.runGame(null,true);
                    if (!gameControls3.isRun())
                        run = false;

                }
                GuiVar.setGameAi(false);


            }
            else if (colour.equals("exit")){
                colour1 = "exit";
            }


        }
    }

    /**
     * class to create ai vs ai game in console
     */
    public void AiAi() {
        AiPlayer player1 = new AiPlayer();

        AiPlayer player2 = new AiPlayer();
        Game game = new Game(player1, player2);


        GameControls gameControls = new GameControls(game, view, input);
        while (run) {
            gameControls.runGame(null,true);
            if (!gameControls.isRun())
                run = false;

        }
    }


    /**
     * Creates a Gui interface
     * @param guiControl control the gui
     */
    public static void guiSetupHuman(GuiControl guiControl){



                //ConsoleInput input1 = new ConsoleInput();
                HumanPlayer player1 = new HumanPlayer(guiControl);
                HumanPlayer player2 = new HumanPlayer(guiControl);
                Game game3 = new Game(player1,player2);
                GuiView view = new GuiView();


                GameControls gameControls = new GameControls(game3,view, guiControl);
                guiControl.setControls(gameControls);



        }

    /**
     * Creates a Gui interface
     * @param guiControl control the gui
     * @param color check color
     */
    public static void guiSetupAi(GuiControl guiControl ,Boolean color ){



        //ConsoleInput input1 = new ConsoleInput();
        HumanPlayer player1 = new HumanPlayer(guiControl);
        AiPlayer player2 = new AiPlayer();
        player2.setGui(true);
        Game game3;
        if (color) {
            game3 = new Game(player1,player2);
        } else {
            game3 = new Game(player2,player1);
        }

        GuiView view = new GuiView();


        GameControls gameControls = new GameControls(game3,view, guiControl);
        guiControl.setControls(gameControls);



    }

    /**
     * The Setup for the Gui Human Online
     * @param guiControl The gui Control instance
     * @param canControlPlayer The Variable that shows the controllable Player
     * @param in The DataInputStream
     * @param out The DataOutputStream
     */
    public static void guiSetupHumanOnline(GuiControl guiControl, boolean canControlPlayer, DataInputStream in, DataOutputStream out){


        //ConsoleInput input1 = new ConsoleInput();
        HumanPlayer player1 = new HumanPlayer(guiControl);
        HumanPlayer player2 = new HumanPlayer(guiControl);
        Game game3 = new Game(player1,player2, canControlPlayer, in, out);
        GuiView view = new GuiView();

        GameControls gameControls = new GameControls(game3,view, guiControl);
        guiControl.setControls(gameControls);


    }
}

package chess;

//import chess.model.Board;
import chess.model.Game;
import java.util.Arrays;


/**
 * The main class
 */
public class Main {

    /**
     * the main method
     * @param args starts the game without gui if "--no-gui" is given
     */
    public static void main(String[] args) {

        boolean cli = Arrays.asList(args).contains("--no-gui");
        if (cli) {
            Game game = new Game();
            while (true){
                game.runGame();
            }
        }

        //boolean cli = Arrays.asList(args).contains("--no-gui");
        //if (cli) {
        //    Cli.main(args);
        //} else {
        //    Gui.main(args);
        //}
        //ConsoleInput Console = new ConsoleInput();
        //System.out.print(Console.listenInput());
    }
}

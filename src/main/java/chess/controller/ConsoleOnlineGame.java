package chess.controller;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Class for the Online Game of the Console
 */
public class ConsoleOnlineGame {
    private Scanner scanner;
    private DataInputStream in;
    private DataOutputStream out;

    /**
     *Setup for the Online Console Game
     * @param isFirstPlayer The chosen Player Color
     * @throws IOException
     */
    private void startGame(boolean isFirstPlayer) throws IOException {
        GameSetup setup = new GameSetup();
        setup.HumanHumanOnlineConsole(isFirstPlayer, in, out);
    }

    /**
     *Hosts an Online Game
     * @throws IOException
     */
    private void hostGame() throws IOException {
        System.out.print("ENTER PORT > ");
        int port = Integer.parseInt(scanner.nextLine());
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("WAITING FOR A CONNECTION");
        Socket connectionSocket = serverSocket.accept();
        System.out.println("ACCEPTED A CONNECTION");
        in = new DataInputStream(connectionSocket.getInputStream());
        out = new DataOutputStream(connectionSocket.getOutputStream());
        startGame(true);
    }

    /**
     * Finds a hosted online Game
     * @throws IOException
     */
    private void findGame() throws IOException {
        System.out.print("ENTER IP HOST > ");
        String ipHost = scanner.nextLine();
        System.out.print("ENTER PORT > ");
        int port = Integer.parseInt(scanner.nextLine());
        Socket clientSocket = new Socket(ipHost, port);
        System.out.println("ACCEPTED A CONNECTION");
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        startGame(false);
    }

    /**
     * Gives the Option to chose Hosting or Finding a Game
     * @throws  IOException check exception method rum
     */
    public void run() throws IOException {
        scanner = new Scanner(System.in);
        System.out.println("=== MENU ===");
        System.out.println("1. Host game");
        System.out.println("2. Find game");
        System.out.print("> ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                hostGame();
                break;
            case 2:
                findGame();
                break;
            default:
                System.exit(0);
        }
    }

}

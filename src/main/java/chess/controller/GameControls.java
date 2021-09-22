package chess.controller;

import chess.model.Board;
import chess.model.Figure;
import chess.model.Game;
import chess.model.History;
import chess.view.Views;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * The class controlling the cetral parts of the game
 */
public class GameControls {
    Game game;
    Views view;
    Input control;
    boolean stopNext = false;
    boolean run = true;
    int countKingMove=0;
    int maxHistoryIndex =0;
    //ResourceBundle language = ResourceBundle.getBundle("/language", new Locale("eng_UK"));

    /**
     * Creates a new instance of GameControls
     * @param game instance of the game class
     * @param view instance of the view class
     * @param control instance of the control class
     */
    public GameControls(Game game, Views view, Input control){
        this.control = control;
        this.game = game;
        this.view = view;
    }

    /**
     * Executes a Move by saving the Figure on a new Square and removing it from its old Position.
     * @param order The complete Move expressed in an Array Form
     * @param board The Board
     * @return the two figures which were moved
     */
    public Figure[] executeMove(int[] order, Board board) {
        int[] q = {4, 0};
        int[] w = {6, 0};
        int[] e = {5, 5};
        int[] r = {5, 3};
        int[] t = {0, 0};

        Figure figure = board.getSquare(order[1],order[0]).getFigure();


        Figure[] reMove = new Figure[2];
        reMove[0] = board.getSquare(order[1],order[0]).getFigure();
        reMove[1] = board.getSquare(order[3],order[2]).getFigure();
        board.getSquare(order[1],order[0]).removeFigure();
        if (!board.getSquare(order[3],order[2]).isEmpty()) {
            String history=board.getBeatenHistory()+board.getSquare(order[3],order[2]).getFigure().print1()+" ";
            board.setBeatenHistory(history);
            board.getBeatenFigures().add(board.getSquare(order[3],order[2]).getFigure());
        }
        board.getSquare(order[3],order[2]).removeFigure();
        board.getSquare(order[3],order[2]).setFigure(figure);

        return reMove;
    }
    /**
     * Prints list of beaten figures
     */
    public void printBeatenFigures(){
        System.out.println(game.getBoard().getBeatenHistory());
    }

    public boolean isRun() {
        return run;
    }
    /**
     * Runs the online Game on Console
     * @param in string input
     * @param out data output
     * @param isFirstPlayer check turn of player
     * @throws  IOException check exception method run game
     */
    public void runGame(boolean isFirstPlayer, DataInputStream in, DataOutputStream out) throws IOException {



        view.print(game.getBoard());


        int[] ar;
        String s = null ;
        if (isFirstPlayer && game.isTurn()) {
            System.out.println("your turn");
            s = game.getPlayer1().getNextMove(game);
            if (InputHandler.validUndoRequest(s)|InputHandler.validRedoRequest(s)){
                out.writeUTF(s);
            }
        } else if(!isFirstPlayer && game.isTurn()) {
            System.out.println("wait for your competitor");
            s = in.readUTF();
            if(s=="undo" || s == "redo"){
                s = game.getPlayer2().getNextMove(game);
            }
        } else if(isFirstPlayer && !game.isTurn()) {
            System.out.println("wait for your competitor");
            s = in.readUTF();
            if(s=="undo" || s == "redo"){
                s = game.getPlayer1().getNextMove(game);
            }
        } else if(!isFirstPlayer && !game.isTurn()) {
            System.out.println("your turn");
            s = game.getPlayer2().getNextMove(game);
            if (InputHandler.validUndoRequest(s)||InputHandler.validRedoRequest(s)){
                out.writeUTF(s);
            }
        }


        if (InputHandler.validListRequest(s)) {
            printBeatenFigures();
        }
        else if(InputHandler.validUndoRequest(s)){
            if (maxHistoryIndex>=1){
                maxHistoryIndex = maxHistoryIndex -1;
                game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());
                game.updateTurn();
            }
            else{
                System.out.println("no existing history");
            }

        } else if(InputHandler.validRedoRequest(s)){
            if (maxHistoryIndex < game.getHistory().size()-1){
                maxHistoryIndex = maxHistoryIndex +1;

                game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());
                game.updateTurn();


            }
            else{
                System.out.println("no existing history");
            }

        }else if(!InputHandler.validCommand(s)) {
            view.printMessage("!Invalid move");
        }
        else {

            ar = InputHandler.convertCommand(s);

            if (Scanner.allowedPosition(ar,game.isTurn(), game.getBoard())&& game.allowedMove(ar, game.getBoard()) && Scanner.freePath(game.getBoard().getSquare(ar[1],ar[0]).getFigure().getType(), ar, game.getBoard())) {
                Figure[] reMove = executeMove(ar, game.getBoard());
                gameExecution(ar, reMove, s);
                if(isFirstPlayer && !game.isTurn() || !isFirstPlayer && game.isTurn()) {
                    out.writeUTF(s);
                }
            }
            else {
                view.printMessage("!Move not allowed");
            }
        }

        if (isTie(game.getBoard(),game.isTurn())){
            System.out.println("tie");
            stopNext=true;
        }

        if(stopNext){
            view.print(game.getBoard());
            run=false;
        }


    }

    /**
     * Runs the Game
     * @param qq string input when play online,when offline is qq=null
     * @param resend resend string
     */
    public void runGame(String qq, boolean resend){
        //print();
        //executeMove(ConsoleInput.convertCommand(ConsoleInput.listenInput()));


        view.print(game.getBoard());


        int[] move;
        String commandString = "abc";
        if(qq == null) {
            if (game.isCanControlFirstPlayer() && game.isTurn()) {
                commandString = game.getPlayer1().getNextMove(game);
            } else if (game.isCanControlSecondPlayer() && !game.isTurn()) {
                commandString = game.getPlayer2().getNextMove(game);
            }
        } else {
            commandString = qq;
        }

        //s = control.listenInput();

        if (InputHandler.validListRequest(commandString)) {
            printBeatenFigures();
        }
        else if(InputHandler.validUndoRequest(commandString)){
            validUndoSimple();
        }
        else if(InputHandler.validRedoRequest(commandString)){
            validRedoSimple();


        }else if(!InputHandler.validCommand(commandString)) {
            view.printMessage("!Invalid move");
        }
        else {

            move = InputHandler.convertCommand(commandString);

            if (Scanner.allowedPosition(move,game.isTurn(), game.getBoard())&& game.allowedMove(move, game.getBoard()) && Scanner.freePath(game.getBoard().getSquare(move[1],move[0]).getFigure().getType(), move, game.getBoard())) {
                Figure[] reMove = executeMove(move, game.getBoard());
                boolean check = gameExecution(move, reMove, commandString);
                if(check && resend && game.out != null) {
                    try {
                        game.out.writeUTF(commandString);
                        System.out.println("move sent:" + commandString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else {
                view.printMessage("!Move not allowed");
            }
        }

        if (isTie(game.getBoard(),game.isTurn())){
            System.out.println("tie");
            stopNext=true;
        }

        if(stopNext){
            view.print(game.getBoard());
            run=false;
        }




    }
    /**
     *
     */
    public void validUndoSimple(){
        if (maxHistoryIndex >= 1 &&!GuiVar.isGameAi()) {
            maxHistoryIndex = maxHistoryIndex -1;
            game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());

            game.updateTurn();
        }
        else if (maxHistoryIndex>=2&&GuiVar.isGameAi()){
            maxHistoryIndex = maxHistoryIndex -2;
            game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());
        }
        else{
            System.out.println("no existing history");
        }

    }
    /**
     *
     */
        public void validRedoSimple(){
            if (maxHistoryIndex < game.getHistory().size()-1&&!GuiVar.isGameAi()) {
                maxHistoryIndex = maxHistoryIndex +1;
                game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());
                game.updateTurn();
            }
            else if (maxHistoryIndex < game.getHistory().size()-1&&GuiVar.isGameAi()){
                maxHistoryIndex = maxHistoryIndex +2;
                game.setBoard(game.getHistory().get(maxHistoryIndex ).getBoard());
            }
            else{
                System.out.println("no existing history");
            }
        }
    /**
     * Sets the Board to an board at the index from History
     * @param index the wanted index
     */
    public void setBoardByHistoryAndIndex(int index){
        game.setBoard(game.getHistory().get(index).getBoard());
        game.setTurn(game.getHistory().get(index).isTurn());
        maxHistoryIndex=index;
    }



    /**
     * method to check if a board is tie
     * @param board the board
     * @param player the player who did the last move
     * @return returns true if game is tie
     */
    public boolean isTie(Board board, Boolean player){
        return !CheckControl.isCheck(player, board) && !CheckControl.canRelease(player, board) || game.getBoard().getFigureAmount() == 2 || countKingMove > 49;

    }

    /**
     * sets the figures back at the Positions they had before the move was execute
     * @param ar int array, move
     * @param reMove the two figures which were moved
     */
    public void undoMove(int[] ar, Figure[] reMove){
        game.getBoard().getSquare(ar[1],ar[0]).setFigure(reMove[0]);
        game.getBoard().getSquare(ar[3],ar[2]).setFigure(reMove[1]);
        System.out.println("!Move not allowed");
    }

    /**
     * Executes the movement on the board
     * @param ar the move in int-Array form
     * @param reMove the two figures which were moved
     * @param s the move in String form
     * @return true if this movement executes
     */
    public boolean gameExecution(int[] ar, Figure[] reMove, String s){
        game.getBoard().pawnAscension(game.getBoard(),s);
        if(CheckControl.isCheck(game.isTurn(), game.getBoard())) {
            //board.beatenFigures.remove(board.beatenFigures.size());
            undoMove(ar,reMove);
            return false;
        }

        else if(CheckControl.isCheck(!game.isTurn(),game.getBoard())) {

            if(CheckControl.canRelease(!game.isTurn(),game.getBoard())) {
                if(game.getBoard().getSquare(ar[3],ar[2]).getFigure().getType() == 10) {
                    game.getBoard().getSquare(ar[3],ar[2]).getFigure().setType(1);
                }
                view.printMessage("!" + s);
                view.printMessage("check");
                //if(game.getBoard().getSquare(ar[3],ar[2]).getFigure().getType() == 10) {
                //    game.getBoard().getSquare(ar[3],ar[2]).getFigure().setType(1);
                //}

                game.resetEnPassant(ar);
                game.updateTurn();
                return true;
            }
            else {
                view.printMessage("!" + s);
                view.printMessage("checkmate");

                stopNext=true;
                return false;
            }
        }
        else{
            if (game.getBoard().getSquare(ar[3],ar[2]).getFigure().getType() == 10) {
                game.getBoard().getSquare(ar[3],ar[2]).getFigure().setType(1);
            }
            view.printMessage("!" + s);

            //if(game.getBoard().getSquare(ar[3],ar[2]).getFigure().getType() == 10) {
            //    game.getBoard().getSquare(ar[3],ar[2]).getFigure().setType(1);
            //}
            //game.getBoard().pawnAscension(game.getBoard(),s);
            game.resetEnPassant(ar);
            game.updateTurn();
            updateKingCount(game.getBoard(), ar);
            if(!(maxHistoryIndex ==game.getHistory().size()-1)){
                LinkedList<History> newHistory= new LinkedList<>();
                int i =1;
                newHistory.add(0,new History(new Board(),null,null,true));


                while( i  <=maxHistoryIndex){

                    newHistory.add(i,game.getHistory().get(i));
                    i++;
                }
                 game.setHistoryList(newHistory);
            }
            game.getHistory().addLast(new History(new Board(game.getBoard()),ar,s,game.isTurn()));
            maxHistoryIndex = game.getHistory().size()-1;

            return true;


        }
    }

    /**
     * counts the amount of move which a king did in a row
     * @param board on this board
     * @param move the last move which was done
     */
    public void updateKingCount(Board board, int[] move) {
        if (board.getSquare(move[3], move[2]).getFigure().getValue() == 900) {
            countKingMove++;
        } else {
            countKingMove = 0;
        }
    }



 public int getMaxHistoryIndex(){
        return maxHistoryIndex;
 }

 public void setMaxHistoryIndex(int toInt){
        this.maxHistoryIndex=toInt;
 }




    public Game getGame() {
        return game;
    }

    /**
     * Sends Undo by using the OutputStream
     * @throws  IOException check exception sendUndo
     */
    public void sendUndo() throws IOException {
        if(game.out != null) {
            System.out.println("sent undo");
            game.out.writeUTF("undo");
        }
    }

    /**
     * Sends Redo by using the outputStream
     * @throws  IOException check exception sendRedo
     */
    public void sendRedo() throws IOException {
        if(game.out != null) {
            System.out.println("sent redo");
            game.out.writeUTF("redo");
        }
    }

    /**
     * Sends the UndoHistory by using the OutputStream
     * @param index The Index of the History Undo
     * @throws  IOException check exception sendUndoHistory
     */
    public void sendUndoHistory(int index) throws IOException {
        if(game.out != null) {
            //System.out.println("SENDING redo history index: " + index);
            game.out.writeUTF("redoHistory " + index);
        }
    }

}
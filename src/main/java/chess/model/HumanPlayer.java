package chess.model;


import chess.controller.Input;

/**
 * The class HumanPlayer which
 */
public class HumanPlayer implements Player {
    boolean colour;
    Input input;

    /**
     * constructor of a humanPlayer
     * @param input the input object which the player use to give inputs
     */
    public HumanPlayer(Input input){
        this.input = input;
    }

    /**
     * method to get the colour of the player
     * @return actual colour
     */
    @Override
    public boolean isColour() {
        return colour;
    }

    /**
     * method to set the colour of the player
     * @param set the value of the wanted colour
     */
    @Override
    public void setColour(boolean set) {
        this.colour=set;

    }

    /**
     * method to get the next move from the player
     * @param actualGame for this game
     * @return move in int array
     */
    @Override
    public String getNextMove(Game actualGame) {
        String move = input.listenInput();
        return move;
    }

}

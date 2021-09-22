package chess.model;

/**
 * interface for different types of player
 */
public interface Player {


    /**
     * method to get the colour of the player
     * @return color of player
     */
    boolean isColour();

    /**
     * method to set the colour of a player
     * @param set wanted colour
     */
    void setColour(boolean set);


    /**
     * method to get the next move which the player wants to make
     * @param actualGame in this game
     * @return move in int array
     */
    String getNextMove(Game actualGame);
}

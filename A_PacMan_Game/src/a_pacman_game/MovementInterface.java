/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package a_pacman_game;

/**
 *
 * @author David Fryer
 * all classes that move MUST implement this.
 */
public interface MovementInterface {
    /**
     * use this method for moving pac-man
     * @param direction the direction to move
     */
    public abstract void step();
    public abstract void step(String direction);
    public abstract void act();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Wu
 */
public class pacMan extends Bug {

    private static int numFlowers;
    private static Location pacManLocation = new Location(11, 9);
    private static int pacManDirection = 0;

    /**
     * constuctor makes a yellow pacman
     */
    public pacMan() {
        super(Color.YELLOW);
        setDirection(Location.EAST);
        numFlowers = 0;
        pacManLocation = getLocation();
        pacManDirection = getDirection();
    }

    /**
     * This is just for my sanity... mostly for testing purposes.
     */
    public void step() {
        step("UP");
    }

    /**
     * gets a count of all flowers in the grid
     */
    static void setNumFlowers() {
        numFlowers++;
    }

    /**
     * gives the number of flowers remaining
     * @return 
     */
    static int getNumFlowers() {
        return numFlowers;
    }

    /**
     * turns pac man
     * @param direction 
     */
    public void step(String direction) {
        if (direction.equalsIgnoreCase("UP")) {
            setDirection(Location.NORTH);
        } else if (direction.equalsIgnoreCase("DOWN")) {
            setDirection(Location.SOUTH);
        } else if (direction.equalsIgnoreCase("RIGHT")) {
            setDirection(Location.EAST);
        } else if (direction.equalsIgnoreCase("LEFT")) {
            setDirection(Location.WEST);
        }
    }

    /**
     * this moves pac man
     */
    @Override
    public void act() {
        if (true) {
            int again = JOptionPane.showConfirmDialog(null, "You Win! "
                    + "Play Again?", "You Win!", JOptionPane.YES_NO_OPTION);
            if (again == JOptionPane.YES_OPTION) {
                pacManMain.setGame();
            } else {
                numFlowers--;
            }
        } else if (canMove()) {
            if (numFlowers > 0) {
                if (getGrid().get(getLocation().
                        getAdjacentLocation(getDirection())) instanceof Flower) {
                    numFlowers--;
                }
                moveTo(getLocation().getAdjacentLocation(getDirection()));
            }
        } else if (getLocation().equals(new Location(9, 0))) {
            moveTo(new Location(9, 18));
        } else if (getLocation().equals(new Location(9, 18))) {
            moveTo(new Location(9, 0));
        }
        pacManLocation = getLocation();
    }

    /**
     * allows the ghosts to get pac man's location
     * @return 
     */
    static Location getPacManLoc() {
        return pacManLocation;
    }

    /**
     * allows inky to see pac man's direction
     * @return 
     */
    static int getPacManDir() {
        return pacManDirection;
    }

    /**
     * checks if the game is cleared
     * @return 
     */
    private boolean isCleared() {
        if (numFlowers == 0) {
            return true;
        }
        return false;
    }
}

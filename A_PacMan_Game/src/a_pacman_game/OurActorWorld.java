/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import javax.swing.JOptionPane;

/**
 *
 * @author David Fryer
 */
public class OurActorWorld extends ActorWorld {

    /**
     * creates a new actor world, giving controls above the grid
     */
    public OurActorWorld() {
        super();
        setGrid(new BoundedGrid<Actor>(21, 19));
        setMessage("Click 'Run' to play.\nUse the Arrow Keys to change"
                + " direction. Clear the flowers to win.\nOh yeah, Don't get"
                + " eaten!");
    }

    /**
     * overrides the clicks within the grid, disabling the menus.
     * @param loc
     * @return
     */
    @Override
    public boolean locationClicked(Location loc) {
        return false;
    }

    /**
     * handles keys being pressed. if it is a directional key,
     * it finds pacman, otherwise, it lets the guiController class handle
     * the key
     * @param description
     * @param loc
     * @return
     */
    @Override
    public boolean keyPressed(String description, Location loc) {
        if (description.equalsIgnoreCase("UP")
                || description.equalsIgnoreCase("DOWN")
                || description.equalsIgnoreCase("RIGHT")
                || description.equalsIgnoreCase("LEFT")) {
            for (Location l : getGrid().getOccupiedLocations()) {
                if (getGrid().get(l) instanceof pacMan) {
                    ((pacMan) getGrid().get(l)).step(description);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * asks the user if they wish to replay the game.
     * @return 
     */
    static boolean playAgainDialog(String message) {
        if (JOptionPane.showConfirmDialog(null, message
                + " Play Again?", message, JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            pacManMain.setGame();
            return true;
        }
        pacManMain.clearGame();
        return false;
    }

    public void clear() {
        for (Location l : getGrid().getOccupiedLocations()) {
            getGrid().get(l).removeSelfFromGrid();
        }
    }
}

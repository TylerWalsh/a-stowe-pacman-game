/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author David Fryer
 */
public class pacGhost extends Bug implements MovementInterface {

    private Location targetLocation;

    public pacGhost() {
        super(Color.BLUE);
        setDirection(Location.EAST);
    }

    public pacGhost(Color c) {
        super(c);
        setDirection(Location.EAST);
    }

    public void step() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void step(String direction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void act() {
        if (!canMove()) {
            turn();
            turn();
        } else {
            moveTo(getLocation().getAdjacentLocation(getDirection()));
        }
    }
}

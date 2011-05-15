/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author Jesse
 */
public class Blinky extends pacGhost {
    public Blinky() {
        setColor(Color.red);
        setDirection(Location.WEST);
    }
    
    protected void setTarget(String mode) {
        if (mode.equals(CHASE)) {
            for (Location loc : getGrid().getOccupiedLocations()) {
                if(getGrid().get(loc) instanceof pacMan) {
                    target = loc;
                }
            }
        } else if (mode.equals(SCATTER)) {
            target = new Location(0, 18);
        }
    }
}

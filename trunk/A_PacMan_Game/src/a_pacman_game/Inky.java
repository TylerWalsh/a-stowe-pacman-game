/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author 248 A
 */
public class Inky extends pacGhost {
    public Inky() {
        setColor(Color.blue);
        setDirection(Location.NORTH);
    }
    
    protected void setTarget(String mode) {
        if (mode.equals(CHASE)) {
           Location secTile = getSecondTile(pacMan.getPacManLoc());
           int dist = getDistance(secTile);
           target = getTargetTile(dist);
        } else if (mode.equals(SCATTER)) {
           target = new Location(20, 18);
        }
    }
    
    private Location getSecondTile(Location loc) {
        int dir = pacMan.getPacManDir();
        Location here = getLocation();
        for (int x = 0; x < 3; x++) {
            here = here.getAdjacentLocation(dir);
        }
        return here;
    }
    
    private Location getTargetTile(int dist) {
        Location here = getBlinkyLoc();
        Location pacManLoc = getBlinkyLoc();
        for (int x = 0; x < 2 * dist; x++) {
            int dir = pacManLoc.getDirectionToward(here);
            here = here.getAdjacentLocation(dir);
        }
        return here;
    }
    
    private int getDistance(Location secTile) {
        Location here = getBlinkyLoc();
        int dist = 0;
        while (!here.equals(secTile)) {
            int dir = secTile.getDirectionToward(here);
            here = here.getAdjacentLocation(dir);
            dist++;
        }
        return dist;
    }
    
    private Location getBlinkyLoc() {
        for (Location loc : getGrid().getOccupiedLocations()) {
            if (getGrid().get(loc) instanceof Blinky) {
                return loc;
            }
        }
        return null;
    }
    
    private Location getPacManLoc() {
        for (Location loc : getGrid().getOccupiedLocations()) {
            if (getGrid().get(loc) instanceof pacMan) {
                return loc;
            }
        }
        return null;
    }
}

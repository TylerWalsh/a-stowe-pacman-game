package a_pacman_game;


import a_pacman_game.pacGhost;
import a_pacman_game.pacMan;
import info.gridworld.grid.Location;
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesse
 */
public class Pinky extends pacGhost {
    public Pinky() {
        setDirection(Location.NORTH);
        setColor(Color.pink);
    }
    
    private Location getFourthTile(Location start) {
        int dir = getGrid().get(start).getDirection();
        for (int x = 0; x < 4; x++) {
            target = getGrid().get(start).getLocation().getAdjacentLocation(dir);
        }
        return target;
    }
    
    protected void setTarget(String mode) {
        if (mode.equals(CHASE)) {
            for (Location loc : getGrid().getOccupiedLocations()) {
                if(getGrid().get(loc) instanceof pacMan) {
                    target = getFourthTile(loc);
                }
            }
        } else if (mode.equals(SCATTER)) {
            target = new Location(0, 0);
        }
    }
}

package a_pacman_game;


import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
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
    private int step;
    
    public Pinky() {
        setDirection(Location.NORTH);
        setColor(Color.pink);
        step = 0;
    }
    
    private Location getFourthTile(Location start) {
        int dir = getGrid().get(start).getDirection();
        for (int x = 0; x < 4; x++) {
            target = getGrid().get(start).getLocation().getAdjacentLocation(dir);
        }
        return target;
    }
    
    private boolean inHouse() {
        Location here = getLocation();
        Location[] house = {new Location(9, 8), new Location(9, 9), new Location(9, 10)};
        for (Location loc : house) {
            if (here.equals(loc))
                return true;
        }
        return false;
    }
    
    private void replaceRock() {
        
    }
    
    protected void setTarget(String mode) {
        Grid<Actor> gr = getGrid();
        if (inHouse())
        if (mode.equals(CHASE)) {
            for (Location loc : gr.getOccupiedLocations()) {
                Actor actor = gr.get(loc);
                if(actor instanceof pacMan) {
                    target = getFourthTile(loc);
                }
            }
        } else if (mode.equals(SCATTER)) {
            target = new Location(0, 0);
        }
    }
}

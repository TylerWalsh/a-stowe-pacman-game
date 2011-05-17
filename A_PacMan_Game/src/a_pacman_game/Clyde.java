package a_pacman_game;


import info.gridworld.grid.Location;
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 248 A
 */
public class Clyde extends pacGhost {
    public Clyde() {
        setColor(Color.orange);
        setDirection(Location.NORTH);
    }
    
    protected void setTarget(String mode) {
        int dist = getDistance();
        if (mode.equals(CHASE) && dist < 8) {
            target = new Location(20, 0);
        } else if (mode.equals(CHASE) && dist >= 8) {
            target = pacMan.getPacManLoc();
        } else if (mode.equals(SCATTER)) {
            target = new Location(20, 0);
        }
    }
    
    private int getDistance() {
        int count = 0;
        Location here = getLocation();
        Location pacManLoc = pacMan.getPacManLoc();
        while (!here.equals(pacManLoc)) {
            int dir = here.getDirectionToward(pacManLoc);
            here = here.getAdjacentLocation(dir);
            count++;
        }
        return count;
    }
}

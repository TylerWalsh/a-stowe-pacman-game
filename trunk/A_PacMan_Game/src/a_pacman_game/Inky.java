/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

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
           // Location secTile = getSecondTile(pacMan.getPacManLoc());
        }
    }
    
    /*private Location getSecondTile(Location loc) {
        
    }*/
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import a_pacman_game.pacGhost;
import a_pacman_game.pacMan;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
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
        Grid<Actor> gr = getGrid();
        if (mode.equals(CHASE)) {
            for (Location loc : gr.getOccupiedLocations()) {
                Actor actor = (Actor) gr.get(loc);
                if (actor instanceof pacMan) {
                    target = loc;
                }
            }
        } else if (mode.equals(SCATTER)) {
            target = new Location(0, 18);
        }
    }
}

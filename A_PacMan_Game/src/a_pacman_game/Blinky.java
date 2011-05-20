package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Blinky (the red ghost) targets PacMan's current location when in chase
 * mode, giving the appearance of consistently pursuing PacMan. In scatter mode,
 * Blinky targets the top right corner.
 * @author Jesse Wang
 */
public class Blinky extends pacGhost {
    
    // Blink's corner
    private final Location corner = new Location(0, 18);
    
    /**
     * Constructor
     * @param loc Location Blinky is initially put in
     */
    public Blinky(Location loc) {
        setColor(Color.red); // Set Blinky's color
        setDirection(Location.EAST); // Set Blinky's orientation
        direction = Location.WEST; // Set Blinky's direction 
        prevLoc = loc; // Set Blinky's previous location
    }
    
    /**
     * Return Blinky's target location based on the move mode.
     * @param mode move mode
     * @return Blinky's target location
     */
    protected Location getTargetLoc (String mode) {
        if (mode.equals(CHASE)) {
            pacMan theMan = getPacMan();
            Location target = theMan.getLocation();
            return target;
        } else if (mode.equals(SCATTER)) {
            return corner;
        } else {
            return null;
        }
    }
}
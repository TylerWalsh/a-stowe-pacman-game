package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Clyde (the orange ghost) is generally characterized as having a lack
 * of interest in pursuing PacMan, when in chase mode. This is accomplished
 * by first calculating the distance between Clyde and PacMan. If the distance
 * is too small, Clyde will move toward his corner, effectively avoiding
 * PacMan. If the distance is large enough, Clyde will target PacMan like
 * Blinky. In scatter mode, Clyde targets the bottom right corner.
 * @author Jesse Wang
 */
public class Clyde extends pacGhost {
    
    // Clyde's corner
    private final Location corner = new Location(20, 0);
    
    /**
     * Constructor
     * @param loc Location Clyde is initially put in
     */
    public Clyde(Location loc) {
        setColor(Color.orange); // Set Clyde's color
        setDirection(Location.EAST); // Set Clyde's orientation
        direction = Location.NORTH; // Set Clyde's direction
        prevLoc = loc; // Set previous location
    }
    
    /**
     * Return the number of tiles between Clyde and PacMan.
     * @return number of tiles between Clyde and PacMan
     */
    private int getDistance() {
        int distance = 0;
        Location here = getLocation();
        Location pacManLoc = getPacMan().getLocation();
        while (!here.equals(pacManLoc)) {
            int targetDir = here.getDirectionToward(pacManLoc);
            here = here.getAdjacentLocation(targetDir);
            distance++;
        }
        return distance;
    }
    
    /**
     * Return Clyde's target location based on the move mode.
     * @param mode move mode
     * @return Clyde's target location
     */
    protected Location getTargetLoc(String mode) {
        if (step == 4) {
            moveTo(new Location(7, 9));
        }
        if (mode.equals(CHASE)) {
            int distance = getDistance();
            if (distance < 8) {
                return corner;
            } else {
                Location pacManLoc = getPacMan().getLocation();
                return pacManLoc;
            }

        } else if (mode.equals(SCATTER)) {
            return corner;
        } else {
            return null;
        }
    }
}
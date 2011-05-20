package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Inky (the blue ghost) is the most unpredictable during gameplay because,
 * when in chase mode, it calculates its target location based on PacMan's
 * location and Blinky's location, effectively coordinating its attack with
 * Blinky to employ a chase-capture strategy.
 * @author Jesse Wang
 */
public class Inky extends pacGhost {
    
    // Inky's corner
    private final Location corner = new Location(20, 18);
    
    /**
     * Constructor
     * @param loc Location Inky is initially put in
     */
    public Inky(Location loc) {
        setColor(Color.blue); // Set Inky's color
        setDirection(Location.EAST); // Set Inky's orientation
        direction = Location.NORTH; // Set Inky's direction
        prevLoc = loc; // Set previous location
    }
    
    /**
     * Get distance between Blinky's location and the second tile in front of
     * PacMan.
     * @return distance between Blinky and second tile in front of
     * PacMan
     */
    private int getDistance() {
        Location here = getBlinky().getLocation();
        Location secTile = getSecondTile();
        int distance = 0;
        while (!here.equals(secTile)) {
            int targetDir = here.getDirectionToward(secTile);
            here = here.getAdjacentLocation(targetDir);
            distance++;
        }
        return distance;

    }
    
    /**
     * Get second tile in front of PacMan.
     * @return second tile in front of PacMan
     */
    private Location getSecondTile() {
        pacMan theMan = getPacMan();
        Location pacManLoc = theMan.getLocation();
        int pacManDir = theMan.getDirection();
        for (int x = 0; x < 3; x++) {
            pacManLoc = pacManLoc.getAdjacentLocation(pacManDir);
        }
        return pacManLoc;
    }
    
    /**
     * Get Blinky from the grid.
     * @return Blinky
     */
    private Blinky getBlinky() {
        Grid<Actor> grid = getGrid();
        for (Location loc : grid.getOccupiedLocations()) {
            Actor actor = grid.get(loc);
            if (actor instanceof Blinky) {
                return (Blinky) actor;
            }
        }
        return null;
    }
    
    /**
     * Get Inky's target location based on the current move mode.
     * @param mode move mode
     * @return Inky's target location
     */
    protected Location getTargetLoc(String mode) {
        if (step == 6) {
            moveTo(new Location(7, 9));
        }
        if (mode.equals(CHASE)) {
            Location here = getBlinky().getLocation();
            Location pacManLoc = getPacMan().getLocation();
            int distance = 2 * getDistance();
            for (int x = 0; x < distance; x++) {
                int targetDir = here.getDirectionToward(pacManLoc);
                here = here.getAdjacentLocation(targetDir);
            }
            return here;
        } else if (mode.equals(SCATTER)) {
            return corner;
        } else {
            return null;
        }
    }
}
package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * Pinky (the pink ghost) targets the fourth tile in front of PacMan when in
 * chase mode. In scatter mode, it targets the top left corner.
 * @author Jesse Wang
 */
public class Pinky extends pacGhost {
    
    // Pinky's corner
    private final Location corner = new Location(0, 0);
    
    /**
     * Constructor
     * @param loc Location Pinky is initially put in
     */
    public Pinky(Location loc) {
        setColor(Color.pink);
        setDirection(Location.EAST);
        direction = Location.NORTH;
        prevLoc = loc;
    }
    
    /**
     * Return fourth tile in front of PacMan.
     * @return fourth tile in front of PacMan.
     */
    private Location getFourthTile() {
        pacMan theMan = getPacMan();
        Location targetLoc = theMan.getLocation();
        int targetDir = theMan.getDirection();
        for (int x = 0; x < 5; x++) {
            targetLoc = targetLoc.getAdjacentLocation(targetDir);
        }
        return targetLoc;
    }
    
    /**
     * Return Pinky's target location based on the current move mode.
     * @param mode Move mode
     * @return Pinky's target location
     */
    protected Location getTargetLoc(String mode) {
        if (step == 8) {
            moveTo(new Location(7, 9));
        }
        if (mode.equals(CHASE)) {
            return getFourthTile();
        } else if (mode.equals(SCATTER)) {
            return corner;
        } else {
            return null;
        }
    }
}
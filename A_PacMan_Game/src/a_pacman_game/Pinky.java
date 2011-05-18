package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

public class Pinky extends pacGhost {

    private final Location corner = new Location(0, 0);

    public Pinky() {
        setColor(Color.pink);
        setDirection(Location.EAST);
        direction = Location.NORTH;
    }

    private Location getFourthTile() {
        pacMan theMan = getPacMan();
        Location targetLoc = theMan.getLocation();
        int targetDir = theMan.getDirection();
        for (int x = 0; x < 5; x++) {
            targetLoc = targetLoc.getAdjacentLocation(targetDir);
        }
        return targetLoc;
    }

    protected Location getTargetLoc(String mode) {
        if (mode.equals(CHASE)) {
            return getFourthTile();
        } else if (mode.equals(SCATTER)) {
            return corner;
        } else {
            return null;
        }
    }
}
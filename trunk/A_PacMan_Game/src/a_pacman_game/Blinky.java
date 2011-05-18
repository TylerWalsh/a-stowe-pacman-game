package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

public class Blinky extends pacGhost {

    private final Location corner = new Location(0, 18);

    public Blinky() {
        setColor(Color.red);
        setDirection(Location.EAST);
        direction = Location.WEST;
    }

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
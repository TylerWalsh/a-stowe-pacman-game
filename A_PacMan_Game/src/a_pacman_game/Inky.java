package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Inky extends pacGhost {

    private final Location corner = new Location(20, 18);

    public Inky(Location loc) {
        setColor(Color.blue);
        setDirection(Location.EAST);
        direction = Location.NORTH;
        prevLoc = loc;
    }

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

    private Location getSecondTile() {
        pacMan theMan = getPacMan();
        Location pacManLoc = theMan.getLocation();
        int pacManDir = theMan.getDirection();
        for (int x = 0; x < 3; x++) {
            pacManLoc = pacManLoc.getAdjacentLocation(pacManDir);
        }
        return pacManLoc;
    }

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
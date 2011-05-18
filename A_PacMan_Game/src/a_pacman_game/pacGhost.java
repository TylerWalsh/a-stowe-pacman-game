package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public abstract class pacGhost extends Bug {

    private Location prevLoc = getLocation();
    private boolean ateBit = true;
    protected int step = 0;
    protected int direction = Location.NORTH;
    protected final String CHASE = "CHASE";
    protected final String SCATTER = "SCATTER";
    protected final String FRIGHTENED = "FRIGHTENED";
    protected static boolean gameFinished = false;


    @Override
    public void act() {
        if (!gameFinished) {
            String mode = getMode(step);
            Location target = getTargetLoc(mode);
            moveTowardTarget(target);
            step++;
        }
    }

    private String getMode(int step) {
        if (step >= 0 && step < 10) {
            return CHASE;
        } else if (step >= 10 && step < 15) {
            return SCATTER;
        } else if (step >= 15) {
            step = 0;
            return CHASE;
        } else {
            return null;
        }
    }

    protected abstract Location getTargetLoc(String mode);

    protected pacMan getPacMan() {
        Grid<Actor> grid = getGrid();
        for (Location loc : grid.getOccupiedLocations()) {
            Actor actor = grid.get(loc);
            if (actor instanceof pacMan) {
                return (pacMan) actor;
            }
        }
        return null;
    }

    private void setPrevLoc() {
        prevLoc = getLocation();
    }

    private void moveTowardTarget(Location target) {
        Location here = getLocation();
        int dirTarget = here.getDirectionToward(target);
        if (dirTarget == Location.NORTH || dirTarget == Location.NORTHWEST) {
            if (moveUp() || moveLeft() || moveRight() || moveDown() || moveBack()) {
            }
        } else if (dirTarget == Location.NORTHEAST) {
            if (moveUp() || moveRight() || moveLeft() || moveDown() || moveBack()) {
            }
        } else if (dirTarget == Location.EAST) {
            if (moveRight() || moveUp() || moveDown() || moveLeft() || moveBack()) {
            }
        } else if (dirTarget == Location.WEST) {
            if (moveLeft() || moveUp() || moveDown() || moveRight() || moveBack()) {
            }
        } else if (dirTarget == Location.SOUTH || dirTarget == Location.SOUTHWEST) {
            if (moveDown() || moveLeft() || moveRight() || moveUp() || moveBack()) {
            }
        } else if (dirTarget == Location.SOUTHEAST) {
            if (moveDown() || moveRight() || moveLeft() || moveUp() || moveBack()) {
            }
        }
    }

    private boolean move(Location loc) {
        Grid gr = getGrid();
        Location here = getLocation();
        Actor nextActor = (Actor) gr.get(loc);
        if (nextActor == null || nextActor instanceof Bit || nextActor instanceof pacMan) {
            setGameFinished(loc);
            replaceBit();
            setAteBit(nextActor);
            setPrevLoc();
            moveTo(loc);
            if (!justCrossed(loc))
                direction = here.getDirectionToward(loc);
            return true;
        }
        return false;
    }

    private boolean moveUp() {
        Location here = getLocation();
        if (direction == Location.SOUTH) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.NORTH);
        if (cross(nextLoc)) {
            nextLoc = crossGrid(nextLoc);
        }
        return move(nextLoc);
    }

    private boolean moveDown() {
        Location here = getLocation();
        if (direction == Location.NORTH) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.SOUTH);
        if (cross(nextLoc)) {
            nextLoc = crossGrid(nextLoc);
        }
        return move(nextLoc);
    }

    private boolean moveLeft() {
        Location here = getLocation();
        if (direction == Location.EAST) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.WEST);
        if (cross(nextLoc)) {
            nextLoc = crossGrid(nextLoc);
        }
        return move(nextLoc);
    }

    private boolean moveRight() {
        Location here = getLocation();
        if (direction == Location.WEST) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.EAST);
        if (cross(nextLoc)) {
            nextLoc = crossGrid(nextLoc);
        }
        return move(nextLoc);
    }

    private boolean moveBack() {
        return move(prevLoc);
    }

    private boolean cross(Location loc) {
        if (loc.equals(new Location(9, -1)) || loc.equals(new Location(9, 19))) {
            return true;
        } else {
            return false;
        }
    }

    private Location crossGrid(Location loc) {
        if (loc.equals(new Location(9, -1))) {
            return new Location(9, 18);
        } else if (loc.equals(new Location(9, 19))) {
            return new Location(9, 0);
        } else {
            return null;
        }
    }

    private boolean justCrossed(Location loc) {
        if (loc.equals(new Location(9, 18)) && prevLoc.equals(new Location(9, 0))) {
            return true;
        } else if (loc.equals(new Location(9, 0)) && prevLoc.equals(new Location(9, 18))) {
            return true;
        } else {
            return false;
        }
    }
    
    private void setAteBit(Actor actor) {
        if (actor instanceof Bit)
            ateBit = true;
    }
    
    private void replaceBit() {
        Grid grid = getGrid();
        if (ateBit) {
            grid.put(prevLoc, new Bit(Color.yellow));
        }
    }
    
    private boolean setGameFinished(Location loc) {
        Location pacManLoc = getPacMan().getLocation();
        if (loc.equals(pacManLoc)) {
            gameFinished = true;
            return true;
        } else {
            gameFinished = false;
            return false;
        }
    }
    
}
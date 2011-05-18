package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public abstract class pacGhost extends Bug {

    protected int step = 0;
    protected int direction = Location.NORTH;
    protected final String CHASE = "CHASE";
    protected final String SCATTER = "SCATTER";
    protected final String FRIGHTENED = "FRIGHTENED";
    
    @Override
    public void act() {
        String mode = getMode(step);
        Location target = getTargetLoc (mode);
        moveTowardTarget(target);
        step++;
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

    protected abstract Location getTargetLoc (String mode);
    
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

    private void moveTowardTarget(Location target) {
        Location here = getLocation();
        int dirTarget = here.getDirectionToward(target);
        if (dirTarget == Location.NORTH || dirTarget == Location.NORTHWEST) {
            if (moveUp() || moveLeft() || moveRight() || moveDown()) {
            }
        } else if (dirTarget == Location.NORTHEAST) {
            if (moveUp() || moveRight() || moveLeft() || moveDown()) {
            }
        } else if (dirTarget == Location.EAST) {
            if (moveRight() || moveUp() || moveDown() || moveLeft()) {
            }
        } else if (dirTarget == Location.WEST) {
            if (moveLeft() || moveUp() || moveDown() || moveRight()) {
            }
        } else if (dirTarget == Location.SOUTH || dirTarget == Location.SOUTHWEST) {
            if (moveDown() || moveLeft() || moveRight() || moveUp()) {
            }
        } else if (dirTarget == Location.SOUTHEAST) {
            if (moveDown() || moveRight() || moveLeft() || moveUp()) {
            }
        } else {
            moveBack();
        }
    }

    private boolean move(Location loc) {
        Grid gr = getGrid();
        Location here = getLocation();
        Actor nextActor = (Actor) gr.get(loc);
        if (nextActor == null || nextActor instanceof Bit) {
            moveTo(loc);
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
        return move(nextLoc);
    }

    private boolean moveDown() {
        Location here = getLocation();
        if (direction == Location.NORTH) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.SOUTH);
        return move(nextLoc);
    }

    private boolean moveLeft() {
        Location here = getLocation();
        if (direction == Location.EAST) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.WEST);
        return move(nextLoc);
    }

    private boolean moveRight() {
        Location here = getLocation();
        if (direction == Location.WEST) {
            return false;
        }
        Location nextLoc = here.getAdjacentLocation(Location.EAST);
        return move(nextLoc);
    }

    private boolean moveBack() {
        Location here = getLocation();
        Location nextLoc = null;
        if (direction == Location.NORTH) {
            nextLoc = here.getAdjacentLocation(Location.SOUTH);
        } else if (direction == Location.SOUTH) {
            nextLoc = here.getAdjacentLocation(Location.NORTH);
        } else if (direction == Location.WEST) {
            nextLoc = here.getAdjacentLocation(Location.WEST);
        } else if (direction == Location.EAST) {
            nextLoc = here.getAdjacentLocation(Location.EAST);
        }
	return move(nextLoc);
    }
}
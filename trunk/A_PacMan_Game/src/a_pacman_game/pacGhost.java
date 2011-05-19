package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * The pacGhost class contains attributes and methods common to all the ghosts
 * in pac-man, including Blinky, Pinky, Inky, and Clyde. Such attributes
 * include previous previous location, direction, and modes of movement.
 * Some prevalent methods include getting the movement mode based on the
 * number of taken, moving toward the target, and a couple of methods
 * specifying the details of moving left, right, up, and down. The class is
 * abstract because each ghost targets a different tile, resulting in distinct
 * personalities and more interesting gameplay.
 * @author Jesse Wang
 */
public abstract class pacGhost extends Bug {

    private boolean ateBit = false;
    private static boolean gameFinished = false;
    protected int step = 0;
    protected int direction = Location.NORTH;
    protected Location prevLoc;
    protected final String CHASE = "CHASE";
    protected final String SCATTER = "SCATTER";
    protected final String FRIGHTENED = "FRIGHTENED";

    @Override
    public void act() {
        if (!gameFinished) {
            String mode = getMode(step);
            Location target = getTargetLoc(mode);
            moveTowardTarget(target);
            step++;
        }
    }

    /**
     * Return movement mode.
     * @param step Number of steps taken.
     * @return movement mode.
     */
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
    
    /**
     * Set target location.
     * @param mode Movement mode
     * @return target location
     */
    protected abstract Location getTargetLoc(String mode);
    
    /**
     * Get pacMan.
     * @return pacMan
     */
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
    
    /**
     * Set ghost's current location as the previous location for the next
     * time act() is called.
     */
    private void setPrevLoc() {
        prevLoc = getLocation();
    }
    
    /**
     * Move ghost in direction of the target.
     * @param target target that the ghost is to move towards
     */
    private void moveTowardTarget(Location target) {
        Location here = getLocation();
        int dirTarget = here.getDirectionToward(target);
        
        // Movement is based on the ghost's direction toward the target.
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
    
    /**
     * Move to the specified location, if possible, while determining the 
     * state of the game (finished or not finished), replacing bits, and
     * setting direction without physically turning the ghost.
     * @param loc Location ghost is to move into
     * @return true - ghost successfully moved; false - ghost did not move
     */
    private boolean move(Location loc) {
        Grid gr = getGrid();
        Actor nextActor = (Actor) gr.get(loc);
        if (nextActor == null || nextActor instanceof Bit || nextActor instanceof pacMan) {
            setGameFinished(loc);
            //replaceBit();
            setAteBit(nextActor);
            setPrevLoc();
            moveTo(loc);
            if(ateBit){
                replaceBit();
            }
            setDirection(loc);
            return true;
        }
        return false;
    }
    
    /**
     * Move to location above the ghost.
     * @return true - ghost successfully moved; false - ghost did not move
     */
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
    
    /**
     * Move to location below the ghost.
     * @return true - ghost successfully moved; false - ghost did not move
     */
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
    
    /**
     * Move to location left of the ghost.
     * @return true - ghost successfully moved; false - ghost did not move
     */
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
    
    /**
     * Move to location right of the ghost.
     * @return true - ghost successfully moved; false - ghost did not move
     */
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
    
    /**
     * Move to previous location.
     * @return true - ghost successfully moved; false - ghost did not move
     */
    private boolean moveBack() {
        return move(prevLoc);
    }
    
    /**
     * Check whether ghost will cross the grid.
     * @param loc ghost's target location
     * @return true - ghost will cross the grid; false - ghost will not cross
     */
    private boolean cross(Location loc) {
        if (loc.equals(new Location(9, -1)) || loc.equals(new Location(9, 19))) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Transport ghost across the grid to give the appearance that it moved
     * across and circled back into view.
     * @param loc ghost's target location
     * @return ghost's new location across the grid
     */
    private Location crossGrid(Location loc) {
        if (loc.equals(new Location(9, -1))) {
            return new Location(9, 18);
        } else if (loc.equals(new Location(9, 19))) {
            return new Location(9, 0);
        } else {
            return null;
        }
    }
    
    /**
     * Check whether ghost just crossed the grid.
     * @param loc ghost's current location
     * @return true - ghost just crossed the grid; false - ghost did not
     */
    private boolean justCrossed(Location loc) {
        if (loc.equals(new Location(9, 18)) && prevLoc.equals(new Location(9, 0))) {
            return true;
        } else if (loc.equals(new Location(9, 0)) && prevLoc.equals(new Location(9, 18))) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Set the value of ateBit.
     * @param actor to be determined whether actor is a bit
     */
    private void setAteBit(Actor actor) {
        if (actor instanceof Bit) {
            ateBit = true;
        } else {
            ateBit = false;
        }
    }
    
    /**
     * Set the ghost's direction without physically turning the ghost.
     * @param loc next location for ghost to move into
     * 
     */
    private void setDirection(Location loc) {
        // Only set a new direction if ghost did not just cross the grid.
        if (!justCrossed(loc)) {
            direction = getLocation().getDirectionToward(loc);
        }
    }
    
    /**
     * Replace the bit that the ghost temporarily removed. Ghosts do not
     * eat bits.
     */
    private void replaceBit() {
        Grid grid = getGrid();
        if (ateBit) {
            Bit myBit = new Bit(Color.yellow);
            myBit.putSelfInGrid(grid, prevLoc);
        }
    }
    
    /**
     * Set the status of the game (finished or not finished).
     * @param loc next location for ghost to move into
     */
    private void setGameFinished(Location loc) {
        Location pacManLoc = getPacMan().getLocation();
        if (loc.equals(pacManLoc)) {
            gameFinished = true;
        } else {
            gameFinished = false;
        }
    }
}
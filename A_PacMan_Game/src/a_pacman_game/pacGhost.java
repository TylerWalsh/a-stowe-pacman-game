/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author Jesse Wang
 */
public abstract class pacGhost extends Bug {
    
    private int step = 0;
    private Location dotLoc = null, prevDotLoc = null;
    protected Location target = null;
    protected String mode = null;
    protected final String CHASE = "CHASE";
    protected final String SCATTER = "SCATTER";

    public void act() {
        setMode(step);
        setTarget(mode);
        moveTowardTarget(target);
        step++;
    }
    
    public Location getTarget() {
        return target;
    }
     
    public int getDirectionTowardTarget() {
        return getLocation().getDirectionToward(target);
    }
    
    private void setMode(int step) {
        if (step >= 0 && step < 10)
            mode = CHASE;
        else if (step >= 10 && step < 15)
            mode = SCATTER;
        else if (step >= 15) {
            mode = CHASE;
            step = 0;
        }
    }
     
    protected abstract void setTarget(String mode);
    
    private void moveTowardTarget(Location target) {
        Location here = getLocation();
        int dir = here.getDirectionToward(target);
        if (dir == Location.NORTH || dir == Location.NORTHWEST) {
            if (moveUp() || moveLeft() || moveRight() || moveDown()) {
                
            } 
        } else if (dir == Location.NORTHEAST) {
            if (moveUp() || moveRight() || moveLeft() || moveDown()) {
                
            }
        } else if (dir == Location.EAST) {
            if (moveRight() || moveUp() || moveDown() || moveLeft()) {
                
            }
        } else if (dir == Location.WEST) {
            if (moveLeft() || moveUp() || moveDown() || moveRight()) {
                
            }
        } else if (dir == Location.SOUTH || dir == Location.SOUTHWEST) {
            if (moveDown() || moveLeft() || moveRight() || moveUp()) {
                
            }
        } else if (dir == Location.SOUTHEAST) {
            if (moveDown() || moveRight() || moveLeft() || moveUp()) {
                
            }
        }
    }
    
    private boolean move(Location loc) {
                Grid gr = getGrid();

        if (!gr.isValid(loc)) {
            throw new IllegalArgumentException("Invalid move location!!!");
        }
        Location here = getLocation();
        Actor nextActor = (Actor) gr.get(loc);
        int dir = here.getDirectionToward(loc);
        if (nextActor == null || nextActor instanceof Flower) {
            setBitLocs(loc);
            System.out.println("Pac Ghost Loc: (" + loc.getRow() + ", " + loc.getCol() + ")");
            moveTo(loc);
            replacePrevBit();
            setDirection(dir);
            return true;
        }
        return false;
    }
    
    private boolean moveUp() {
        int dir = getDirection();
        Location here = getLocation();
        if (dir == Location.SOUTH)
            return false;
        Location nextLoc = here.getAdjacentLocation(Location.NORTH);
        return move(nextLoc);
    }
    
    private boolean moveLeft() {
        int dir = getDirection();
        Location here = getLocation();
        if (dir == Location.EAST)
            return false;
        Location nextLoc = here.getAdjacentLocation(Location.WEST);
        return move(nextLoc);
    }
    
    private boolean moveDown() {
        int dir = getDirection();
        Location here = getLocation();
        if (dir == Location.NORTH)
            return false;
        Location nextLoc = here.getAdjacentLocation(Location.SOUTH);
        return move(nextLoc);
    }
    
    private boolean moveRight() {
        int dir = getDirection();
        Location here = getLocation();
        if (dir == Location.WEST)
            return false;
        Location nextLoc = here.getAdjacentLocation(Location.EAST);
        return move(nextLoc);
    }  
    
    private void setBitLocs(Location loc) {
        
    }
    
    private void replacePrevBit() {
        
    }
}

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
import java.util.ArrayList;

/**
 *
 * @author David Fryer
 */
public abstract class pacGhost extends Bug {
    
    private int step = 0;
    protected Location target;
    protected String mode;
    protected final String CHASE = "CHASE";
    protected final String SCATTER = "SCATTER";

    public void act() {
        setMode(step);
        setTarget(mode);
        moveTowardTarget();
        step++;
    }
    
    public Location getTarget() {
        return target;
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
    
    private void moveTowardTarget() {
        int dir = getLocation().getDirectionToward(target);
        if (dir == Location.NORTH || dir == Location.NORTHWEST || dir == Location.NORTHEAST)
            moveUp();
        else if (dir == Location.SOUTH || dir == Location.SOUTHWEST || dir == Location.SOUTHEAST)
            moveDown();
        else if (dir == Location.WEST)
            moveLeft();
        else if (dir == Location.EAST)
            moveRight();
    }
    
    private boolean moveUp() {
        int dir = getDirection();
        Location next = getLocation().getAdjacentLocation(Location.NORTH);
        Grid gr = getGrid();
        if (dir != Location.SOUTH && (gr.get(next) == null || gr.get(next) instanceof Flower)) {
            moveTo(next);
            setDirection(Location.NORTH);
            checkPacMan();
            return true;
        } else {
            if (moveLeft() || moveRight() || moveDown()) {
                checkPacMan();
                return true;
            } else {
                return false;
            }
        }
    }
    
    private boolean moveDown() {
        int dir = getDirection();
        Location next = getLocation().getAdjacentLocation(Location.SOUTH);
        Grid gr = getGrid();
        if (dir != Location.NORTH && (gr.get(next) == null || gr.get(next) instanceof Flower)) {
            moveTo(next);
            setDirection(Location.SOUTH);
            checkPacMan();
            return true;
        } else {
            if (moveLeft() || moveRight() || moveUp()) {
                checkPacMan();
                return true;
            } else {
                return false;
            }
        }
    }
    
    private boolean moveLeft() {
        int dir = getDirection();
        Location next = getLocation().getAdjacentLocation(Location.WEST);
        Grid gr = getGrid();
        if (dir != Location.EAST && (gr.get(next) == null || gr.get(next) instanceof Flower)) {
            moveTo(next);
            setDirection(Location.WEST);
            checkPacMan();
            return true;
        } else {
            if (moveUp() || moveDown() || moveRight()) {
                checkPacMan();
                return true;
            } else {
                return false;
            }
        }
    }
    
    private boolean moveRight() {
        int dir = getDirection();
        Location next = getLocation().getAdjacentLocation(Location.EAST);
        Grid gr = getGrid();
        if (dir != Location.WEST && (gr.get(next) == null || gr.get(next) instanceof Flower)) {
            moveTo(next);
            setDirection(Location.EAST);
            checkPacMan();
            return true;
        } else {
            if (moveUp() || moveDown() || moveLeft()) {
                checkPacMan();
                return true;
            } else {
                return false;
            }
        }
    }
    
    private boolean checkPacMan() {
        Actor pacMan = null;
        for (Location loc : getGrid().getOccupiedLocations()) {
            if(getGrid().get(loc) instanceof pacMan) {
                pacMan = getGrid().get(loc);
            }
        }
        
        if (pacMan != null && pacMan.getLocation().equals(getLocation())) {
            return true;
        } else {
            return false;
        }
    }
    
    private ArrayList<Location> getPath(int dir) {
       ArrayList<Location> path = new ArrayList<Location>();
       ArrayList<Location> tempPath = new ArrayList<Location>();
       Location here = getLocation();
       if (here.equals(target))
           return path;
       else if (isIntersection(here)) {
           ArrayList<Location> subpath = new ArrayList<Location>();
           for (int x = 0; x < 4; x++) {
               if (x == 0)
                   subpath = getPath(Location.NORTH);
               else if (x == 1) {
                   tempPath = getPath(Location.EAST);
                   subpath = shorter(subpath, tempPath);
               } else if (x == 2) {
                   tempPath = getPath(Location.SOUTH);
                   subpath = shorter(subpath, tempPath);
               } else if (x == 3) {
                   tempPath = getPath(Location.WEST);
                   subpath = shorter(subpath, tempPath);
               }
           }
           return path;
       } else {
           path = getPath(dir);
       }
    }
    
    protected abstract void setTarget(String mode);
}

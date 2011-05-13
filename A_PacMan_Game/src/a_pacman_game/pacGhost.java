/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 *
 * @author David Fryer
 */
public abstract class pacGhost extends Bug {

    protected Location target;
    protected String mode;
    private final String CHASE = "CHASE";
    private final String SCATTER = "SCATTER";

    public void act() {
        
    }
    
    private void setMode() {
        
    }
    
    private boolean getPacManLives() {
        
    }
    
    private boolean checkGameFinished() {
        
    }
    
    private void moveTowardTarget() {
        
    }
    
    private boolean eatPacMan() {
        
    }
    
    protected abstract void setTarget();
}

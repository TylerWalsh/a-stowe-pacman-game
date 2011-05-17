/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a_pacman_game;

import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import java.awt.Color;

/**
 *
 * @author David Fryer
 */
public class pacManMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OurActorWorld pacWorld = new OurActorWorld();
        pacMan pacman = new pacMan();
        pacWorld.add(new Location(11, 9), pacman);
        //before we're done, all ghosts except Blinky MUST be added within the
        //"ghost house" (Locations (9,8) to (9,10). Blinky should start above
        //this area(Location(7,9)
        pacWorld.add(new Location(7, 9), new Blinky());
        pacWorld.add(new Location(7, 10), new Pinky());
        pacWorld.add(new Location(7, 11), new Clyde());
        /*
         * these two ghosts are work in progresses -jesse wang
         * pacWorld.add(new Location(7, 9), new Blinky());
         * pacWorld.add(new Location(9, 8), new Pinky());
         */

        pacWorld.show();
        makeBounds(pacWorld);
        //add all other actors FIRST, otherwise you will get
        //the wrong flower count
        for (int r = 0; r < pacWorld.getGrid().getNumRows(); r++) {
            for (int c = 0; c < pacWorld.getGrid().getNumCols(); c++) {
                Location l = new Location(r, c);
                if (pacWorld.getGrid().get(l) == null) {
                    pacWorld.add(l, new Bit(Color.YELLOW));
                    pacMan.setNumFlowers();
                }
            }
        }
    }

    /**
     * Adds the boundaries to the world.
     * @param pacWorld
     */
    private static void makeBounds(World pacWorld) {
        for (int x = 0; x < pacWorld.getGrid().getNumRows(); x++) {
            for (int y = 0; y < pacWorld.getGrid().getNumCols(); y++) {
                Location l = new Location(x, y);
                if (x == 0 || x == pacWorld.getGrid().getNumRows() - 1) {
                    pacWorld.add(l, new Rock());
                } else {
                    if (x != 9 && (y == 0 || y == pacWorld.getGrid().
                            getNumCols() - 1)) {
                        pacWorld.add(l, new Rock());
                    }
                    if (x < 3 && y == 9) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 2 && ((y > 1 && y < 4) || (y > 4 && y < 8)
                            || (y > 10 && y < 14) || (y > 14 && y < 17))) {
                        pacWorld.add(l, new Rock());
                    } else if ((x == 4 || x == 12) && ((y > 1 && y < 4)
                            || (y == 5) || (y > 6 && y < 12) || (y == 13)
                            || (y > 14 && y < 17))) {
                        pacWorld.add(l, new Rock());
                    } else if ((x == 5 || x == 17) && (y == 5 || y == 9
                            || y == 13)) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 6 && ((y < 4) || (y > 4 && y < 8)
                            || (y == 9) || (y > 10 && y < 14) || y > 14)) {
                        pacWorld.add(l, new Rock());
                    } else if (((x > 6 && x < 12) && x != 9) && ((y < 4)
                            || (y == 5) || (y == 13) || (y > 14))) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 13 && y == 9) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 14 && ((y > 1 && y < 4) || (y > 4 && y < 8)
                            || (y == 9) || (y > 10 && y < 14)
                            || (y > 14 && y < 17))) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 15 && (y == 3 || y == 15)) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 16 && ((y == 1) || (y == 3) || (y == 5)
                            || (y > 6 && y < 12) || (y == 13) || (y == 15)
                            || (y == 17))) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 18 && ((y > 1 && y < 8) || (y == 9)
                            || (y > 10 && y < 17))) {
                        pacWorld.add(l, new Rock());
                    }
                    if ((x == 8 || x == 10) && (y > 6 && y < 12)) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 9 && (y == 7 || y == 11)) {
                        pacWorld.add(l, new Rock());
                    } else if (x == 12 && (y == 1 || y == 17)) {
                        pacWorld.add(l, new Rock());
                    }
                }
            }
        }
    }
}

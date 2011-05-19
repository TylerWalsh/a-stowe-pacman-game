package a_pacman_game;

import info.gridworld.grid.Location;
import java.awt.Color;

public class Clyde extends pacGhost {
	private final Location corner = new Location(20, 0);
	
	public Clyde(Location loc) {
		setColor(Color.orange);
		setDirection(Location.EAST);
		direction = Location.NORTH;
                prevLoc = loc;
	}

	private int getDistance() {
		int distance = 0;
		Location here = getLocation();
		Location pacManLoc = getPacMan().getLocation();
		while(!here.equals(pacManLoc)) {
			int targetDir = here.getDirectionToward(pacManLoc);
			here = here.getAdjacentLocation(targetDir);
			distance++;
		}
		return distance;
	}

	protected Location getTargetLoc (String mode) {
		if (mode.equals(CHASE)) {
			int distance = getDistance();
			if (distance < 8)
				return corner;
			else {
				Location pacManLoc = getPacMan().getLocation();
				return pacManLoc;
			}
				
		} else if (mode.equals(SCATTER)) {
			return corner;
		} else {
			return null;
		}
	}
	
}
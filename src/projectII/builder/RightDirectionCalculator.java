package projectII.builder;

import projectII.Vector;

public class RightDirectionCalculator extends DirectionCalculator {
	
	public RightDirectionCalculator(Rectangle rec) {
		forwardDirection = new Vector(1, 0);
		backwardCenter = new Vector(rec.getX(), (rec.getY() + rec.getEndY()) / 2);
		leftCenter = new Vector(rec.getX(), rec.getEndY() - 1);
		rightCenter = new Vector(rec.getX(), rec.getY());
	}

}

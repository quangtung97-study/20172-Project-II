package projectII.builder;

import projectII.Vector;

public class UpDirectionCalculator extends DirectionCalculator {

	public UpDirectionCalculator(Rectangle rec) {
		forwardDirection = new Vector(0, 1);
		backwardCenter = new Vector((rec.getX() + rec.getEndX()) / 2, rec.getY());
		leftCenter = new Vector(rec.getX(), rec.getY());
		rightCenter = new Vector(rec.getEndX() - 1, rec.getY());
	}

}

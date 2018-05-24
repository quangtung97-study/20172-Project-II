package projectII.builder;

import projectII.Vector;

public class UpDirectionCalculator extends DirectionCalculator {

	public UpDirectionCalculator(Rectangle rec) {
		super(rec);
		
		forwardDirection = new Vector(0, 1);
		backwardCenter = mid0;
		leftCenter = v0;
		rightCenter = v1;
	}

}

package projectII.builder;

import projectII.Vector;

public class RightDirectionCalculator extends DirectionCalculator {
	
	public RightDirectionCalculator(Rectangle rec) {
		super(rec);

		forwardDirection = new Vector(1, 0);
		backwardCenter = mid3;
		leftCenter = v3;
		rightCenter = v0;
	}

}

package projectII.builder;

import projectII.Vector;

public class LeftDirectionCalculator extends DirectionCalculator {
	
	public LeftDirectionCalculator(Rectangle rec) {
		super(rec);
		
		forwardDirection = new Vector(-1, 0);
		backwardCenter = mid1;
		leftCenter = v1;
		rightCenter = v2;
	}

}

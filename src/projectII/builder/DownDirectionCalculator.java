package projectII.builder;

import projectII.Vector;

public class DownDirectionCalculator extends DirectionCalculator {
	
	public DownDirectionCalculator(Rectangle rec) {
		super(rec);
		
		forwardDirection = new Vector(0, -1);
		backwardCenter = mid2;
		leftCenter = v2;
		rightCenter = v3;
	}

}

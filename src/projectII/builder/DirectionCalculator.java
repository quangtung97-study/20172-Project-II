package projectII.builder;

import projectII.Vector;

public class DirectionCalculator {
	protected Vector forwardDirection;
	protected Vector backwardCenter;
	protected Vector leftCenter;
	protected Vector rightCenter;
	
	public Vector getForward(int x, int y) {
		return forwardDirection;
	}

	public Vector getBackward(int x, int y) {
		float xc = backwardCenter.x;
		float yc = backwardCenter.y;

		float dirX = -(y - yc);
		float dirY = (x - xc);

		float d2 = dirX * dirX + dirY * dirY;
		float d = (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	public Vector getLeft(int x, int y) {
		float xc = leftCenter.x;
		float yc = leftCenter.y;
		
		float dirX = -(y - yc);
		float dirY = (x - xc);
		float d2 = dirX * dirX + dirY * dirY;
		float d = (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	public Vector getRight(int x, int y) {
		float xc = rightCenter.x;
		float yc = rightCenter.y;
		
		float dirX = (y - yc);
		float dirY = -(x - xc);
		float d2 = dirX * dirX + dirY * dirY;

		float d = (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}
}

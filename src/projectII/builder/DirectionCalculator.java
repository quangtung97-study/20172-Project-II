package projectII.builder;

import projectII.Vector;

public abstract class DirectionCalculator {
	protected final Vector v0, v1, v2, v3;
	protected final Vector mid0, mid1, mid2, mid3;
	
	protected Vector forwardDirection;
	protected Vector backwardCenter;
	protected Vector leftCenter;
	protected Vector rightCenter;
	
	public DirectionCalculator(Rectangle rec) {
		v0 = new Vector(rec.getX(), rec.getY());
		v1 = new Vector(rec.getEndX() - 1, rec.getY());
		v2 = new Vector(rec.getEndX() - 1, rec.getEndY() - 1);
		v3 = new Vector(rec.getX(), rec.getEndY() - 1);

		mid0 = new Vector((rec.getX() + rec.getEndX()) / 2, rec.getY());
		mid1 = new Vector(rec.getEndX() - 1, (rec.getY() + rec.getEndY()) / 2);
		mid2 = new Vector((rec.getX() + rec.getEndX()) / 2, rec.getEndY() - 1);
		mid3 = new Vector(rec.getX(), (rec.getY() + rec.getEndY()) / 2);
	}
	
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

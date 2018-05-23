package projectII.builder;

import projectII.Vector;

public class UpDirectionCalculator extends DirectionCalculator {

	@Override
	public Vector getForward(int x, int y) {
		return new Vector(0, 1);
	}

	@Override
	public Vector getBackward(int x, int y) {
		float xc = (rec.getX() + rec.getEndX()) / 2;
		float yc = rec.getY();

		float dirX = -(y - yc);
		float dirY = (x - xc);

		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	@Override
	public Vector getLeft(int x, int y) {
		float dirX = -(y - rec.getY());
		float dirY = (x - rec.getX());
		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	@Override
	public Vector getRight(int x, int y) {
		float xc = rec.getEndX() - 1;
		float yc = rec.getY();
		
		float dirX = (y - yc);
		float dirY = -(x - xc);
		float d2 = dirX * dirX + dirY * dirY;

		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}
}

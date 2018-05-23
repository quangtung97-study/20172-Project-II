package projectII.builder;

import projectII.Vector;

public abstract class DirectionCalculator {
	protected Rectangle rec;
	
	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}

	public abstract Vector getForward(int x, int y);

	public abstract Vector getBackward(int x, int y);
	
	public abstract Vector getLeft(int x, int y);
	
	public abstract Vector getRight(int x, int y);
	
}

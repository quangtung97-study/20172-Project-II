package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Road extends Agent {
	protected float directionX, directionY;

	public Road(Context<Object> context, 
			Grid<Object> grid, ContinuousSpace<Object> space, 
			float x, float y, float directionX, float directionY) {
		super(context, grid, space, x, y);
		this.directionX = directionX;
		this.directionY = directionY;
	}
	
	public float getDirectionX() {
		return directionX;
	}
	
	public float getDirectionY() {
		return directionY;
	}
}

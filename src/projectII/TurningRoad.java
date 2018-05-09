package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class TurningRoad extends Road {
	private int directionIndex = 0;

	public TurningRoad(
			Context<Object> context, 
			Grid<Object> grid, ContinuousSpace<Object> space,
			float x, float y,
			float directionX, float directionY,
			int directionIndex) 
	{
		super(context, grid, space, x, y, directionX, directionY);
		this.directionIndex = directionIndex;
	}
	
	public int getDirectionIndex() {
		return directionIndex;
	}
}

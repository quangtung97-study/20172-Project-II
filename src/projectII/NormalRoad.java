package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class NormalRoad extends Road {

	public NormalRoad(
			Context<Object> context, 
			Grid<Object> grid, ContinuousSpace<Object> space,
			float x, float y, 
			float directionX, float directionY) {
		super(context, grid, space, x, y, directionX, directionY);
	}
}

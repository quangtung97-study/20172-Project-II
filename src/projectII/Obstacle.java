package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Obstacle extends Agent {
	
	public Obstacle(Context<Object> context, 
			Grid<Object> grid, ContinuousSpace<Object> space,
			float x, float y)
	{
		super(context, grid, space, x, y);
	}
}

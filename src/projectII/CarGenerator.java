package projectII;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class CarGenerator {
	private final Context<Object> context;
	private final Grid<Object> grid;
	private final ContinuousSpace<Object> space;
	private float x, y, v;

	public CarGenerator(Context<Object> context, Grid<Object> grid, 
			ContinuousSpace<Object> space,
			float x, float y, float v) {
		this.context = context;
		this.grid = grid;
		this.space = space;
		this.x = x;
		this.y = y;
		this.v = v;
		
		context.add(this);
	}

	@ScheduledMethod(start = 1, interval = 10, priority = 1)
	public void generate() {
		int d = RandomHelper.nextIntFromTo(-5, 5);
		new Car(context, grid, space, x + d, y, v);
	}
}

package projectII;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Car extends Agent {
	private float v;
	
	public Car(Context<Object> context, Grid<Object> grid, 
			ContinuousSpace<Object> space, float x, float y, float v) {
		super(context, grid, space, x, y);
		this.v = v;
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = 2)
	public void step() {
		float x = getX();
		float y = getY();
		
		Iterable<Object> objects = grid.getObjectsAt((int)x, (int)y);
		Road road = null;
		for (Object o: objects) 
			if (o instanceof NormalRoad) {
				road = (Road)o;
				break;
			}

		float vx = v * road.getDirectionX();
		float vy = v * road.getDirectionY();
		moveTo(x + vx, y + vy);
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = 1)
	public void postStep() {
		applyMove();

		float x = getX();
		float y = getY();
		if (x < 0 || x >= TrafficModel.SPACE_SIZE ||
			y < 0 || y >= TrafficModel.SPACE_SIZE)
			removeThis();
	}
}

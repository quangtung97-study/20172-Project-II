package projectII;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.InfiniteBorders;
import repast.simphony.space.grid.SimpleGridAdder;

public class TrafficModel implements ContextBuilder<Object> {
	public static final int SPACE_SIZE = 101;
	public static final int ROAD_SIZE = 20;

	private Context<Object> context = null;
	private Grid<Object> grid = null; 
	ContinuousSpace<Object> space = null;
	
	private boolean insideRectangle(int x, int y, int rx, int ry, int w, int h) {
		if (x < rx || x >= rx + w)
			return false;
		if (y < ry || y >= ry + h)
			return false;
		return true;
	}
	
	private boolean isRoad(int x, int y) {
		final int NON_ROAD_SIZE = (SPACE_SIZE - 1 - ROAD_SIZE * 2) / 2;
		if (insideRectangle(x, y, 0, 0, NON_ROAD_SIZE, NON_ROAD_SIZE))
			return false;
		if (insideRectangle(x, y, 0, SPACE_SIZE - NON_ROAD_SIZE, NON_ROAD_SIZE, NON_ROAD_SIZE))
			return false;
		if (insideRectangle(x, y, SPACE_SIZE - NON_ROAD_SIZE, 0, NON_ROAD_SIZE, NON_ROAD_SIZE))
			return false;
		if (insideRectangle(x, y, SPACE_SIZE - NON_ROAD_SIZE, 
				SPACE_SIZE - NON_ROAD_SIZE, NON_ROAD_SIZE, NON_ROAD_SIZE))
			return false;
		
		int pivot = SPACE_SIZE / 2;

		if (insideRectangle(x, y, pivot, 0, 1, NON_ROAD_SIZE))
			return false;
		if (insideRectangle(x, y, 0, pivot, NON_ROAD_SIZE, 1))
			return false;
		if (insideRectangle(x, y, pivot, SPACE_SIZE - NON_ROAD_SIZE, 1, NON_ROAD_SIZE))
			return false;
		if (insideRectangle(x, y, SPACE_SIZE - NON_ROAD_SIZE, pivot, NON_ROAD_SIZE, 1))
			return false;

		return true;
	}
	
	private void buildObstacles() {
		for (int x = 0; x < SPACE_SIZE; x++)
			for (int y = 0; y < SPACE_SIZE; y++)
				if (isRoad(x, y))
					new NormalRoad(context, grid, space, x, y, 0, 1);
				else
					new Obstacle(context, grid, space, x, y);
	}
	
	private void buildLeftTurningRoad() {
		int x0 = (SPACE_SIZE - 1) / 2 - ROAD_SIZE;
		int y0 = (SPACE_SIZE - 1) / 2 - ROAD_SIZE;
		int x1 = SPACE_SIZE - ROAD_SIZE;
		int y1 = SPACE_SIZE - ROAD_SIZE;

		float r1 = ROAD_SIZE + 1;
		float r2 = 2 * ROAD_SIZE + 1;
		
		for (int x = x0; x < x1; x++)
			for (int y = y0; y < y1; y++) {
				float dirX = -(y - y0);
				float dirY = (x - x0);
				float d2 = dirX * dirX + dirY * dirY;
				float dirLen = (float)Math.sqrt(d2);
				dirX /= dirLen;
				dirY /= dirLen;

				if (r1 * r1 <= d2 && d2 < r2 * r2)
					new TurningRoad(context, grid, space, x, y, dirX, dirY, 0);
			}
	}
	
	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("ProjectII");
		this.context = context;

		GridFactory gridBuilder = GridFactoryFinder.createGridFactory(null);
		GridBuilderParameters<Object> params = new GridBuilderParameters<Object> (
				new InfiniteBorders<Object>(),
				new SimpleGridAdder<Object>(), false,
				SPACE_SIZE, SPACE_SIZE);
		
		grid = gridBuilder.createGrid("grid", context, params);
		
		ContinuousSpaceFactory factory = 
				ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		space = factory.createContinuousSpace(
				"space", context, new SimpleCartesianAdder<Object>(), 
				new repast.simphony.space.continuous.InfiniteBorders<Object>(),
				SPACE_SIZE, SPACE_SIZE);
		
		buildObstacles();
		
		buildLeftTurningRoad();
		
		new CarGenerator(context, grid, space, SPACE_SIZE / 2 + 7, 0, 0.5f);
		
		return context;
	}

}

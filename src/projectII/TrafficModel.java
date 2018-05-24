package projectII;

import projectII.builder.Rectangle;
import projectII.builder.StraightBuilder;
import projectII.builder.TurningBuilder;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.InfiniteBorders;
import repast.simphony.space.grid.SimpleGridAdder;

public class TrafficModel implements ContextBuilder<Object> {
	public static final int spaceSizeX = 252;
	public static final int spaceSizeY = 242;
	
	private ServiceLocator createServices(Context<Object> context) {
		GridFactory gridBuilder = GridFactoryFinder.createGridFactory(null);
		GridBuilderParameters<Object> params = new GridBuilderParameters<Object> (
				new InfiniteBorders<Object>(),
				new SimpleGridAdder<Object>(), true, // multi occurance space
				spaceSizeX, spaceSizeY);

		Grid<Object> grid = gridBuilder.createGrid("grid", context, params);
		
		ContinuousSpaceFactory factory = 
				ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);

		ContinuousSpace<Object> space = factory.createContinuousSpace(
				"space", context, new SimpleCartesianAdder<Object>(), 
				new repast.simphony.space.continuous.InfiniteBorders<Object>(),
				spaceSizeX, spaceSizeY);

		NetworkBuilder<Object> networkBuilder = new NetworkBuilder<Object>(
				"traffic network", context, true);
		Network<Object> net = networkBuilder.buildNetwork();
		
		return new ServiceLocator(context, grid, space, net);
	}
	
	private void buildStraightRoad(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);

		// builder.setRectangle(new Rectangle(MIDDLE + 1, 0, ROAD_SIZE, NON_ROAD_SIZE));
		// builder.setEdge(graph.bot, graph.mid);
		// builder.setDirection(new Vector(0, 1));
		// builder.build();
	}

	private void buildRoad(ServiceLocator locator, TrafficGraph graph) {
		buildStraightRoad(locator, graph);
	}

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("ProjectII");
		
		ServiceLocator locator = createServices(context);
		TrafficGraph graph = new TrafficGraph(locator);
		
		buildRoad(locator, graph);
		return context;
	}

}

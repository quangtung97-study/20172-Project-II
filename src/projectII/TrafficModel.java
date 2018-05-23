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
	public static final int SPACE_SIZE = 101;
	private static final int ROAD_SIZE = 20;
	private static final int NON_ROAD_SIZE = (SPACE_SIZE - 1 - 2 * ROAD_SIZE) / 2;
	private static final int MIDDLE = SPACE_SIZE / 2;
	
	private ServiceLocator createServices(Context<Object> context) {
		GridFactory gridBuilder = GridFactoryFinder.createGridFactory(null);
		GridBuilderParameters<Object> params = new GridBuilderParameters<Object> (
				new InfiniteBorders<Object>(),
				new SimpleGridAdder<Object>(), true, // multi occurance space
				SPACE_SIZE, SPACE_SIZE);

		Grid<Object> grid = gridBuilder.createGrid("grid", context, params);
		
		ContinuousSpaceFactory factory = 
				ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);

		ContinuousSpace<Object> space = factory.createContinuousSpace(
				"space", context, new SimpleCartesianAdder<Object>(), 
				new repast.simphony.space.continuous.InfiniteBorders<Object>(),
				SPACE_SIZE, SPACE_SIZE);

		NetworkBuilder<Object> networkBuilder = new NetworkBuilder<Object>(
				"traffic network", context, true);
		Network<Object> net = networkBuilder.buildNetwork();
		
		return new ServiceLocator(context, grid, space, net);
	}
	
	private void buildStraightRoad(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);

		builder.setRectangle(new Rectangle(MIDDLE + 1, 0, ROAD_SIZE, NON_ROAD_SIZE));
		builder.setEdge(graph.bot, graph.mid);
		builder.setDirection(new Vector(0, 1));
		builder.build();
		
		builder.setRectangle(new Rectangle(MIDDLE + 1, SPACE_SIZE - NON_ROAD_SIZE, ROAD_SIZE, NON_ROAD_SIZE));
		builder.setEdge(graph.mid, graph.top);
		builder.build();
		
		builder.setRectangle(new Rectangle(NON_ROAD_SIZE, 0, ROAD_SIZE, NON_ROAD_SIZE));
		builder.setEdge(graph.mid, graph.bot);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(NON_ROAD_SIZE, SPACE_SIZE - NON_ROAD_SIZE, 
				ROAD_SIZE, NON_ROAD_SIZE));
		builder.setEdge(graph.top, graph.mid);
		builder.build();

		builder.setRectangle(new Rectangle(0, NON_ROAD_SIZE, NON_ROAD_SIZE, ROAD_SIZE));
		builder.setEdge(graph.left, graph.mid);
		builder.setDirection(new Vector(1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(SPACE_SIZE - NON_ROAD_SIZE, NON_ROAD_SIZE, 
				NON_ROAD_SIZE, ROAD_SIZE));
		builder.setEdge(graph.mid, graph.right);
		builder.build();

		builder.setRectangle(new Rectangle(0, MIDDLE + 1, NON_ROAD_SIZE, ROAD_SIZE));
		builder.setEdge(graph.mid, graph.left);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(SPACE_SIZE - NON_ROAD_SIZE, MIDDLE + 1, 
				NON_ROAD_SIZE, ROAD_SIZE));
		builder.setEdge(graph.right, graph.mid);
		builder.build();
	}

	private void buildRoad(ServiceLocator locator, TrafficGraph graph) {
		buildStraightRoad(locator, graph);
		
		TurningBuilder builder = new TurningBuilder(locator);
		builder.setRectangle(new Rectangle(
				NON_ROAD_SIZE, NON_ROAD_SIZE, 
				2 * ROAD_SIZE + 1, 2 * ROAD_SIZE + 1));
		builder.setNodes(graph.mid, graph.top, graph.bot, graph.left, graph.right);
		builder.build();
	}

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("ProjectII");
		
		ServiceLocator locator = createServices(context);
		TrafficGraph graph = new TrafficGraph(locator);
		
		buildRoad(locator, graph);
		
		Vector pos = new Vector(SPACE_SIZE / 2 + ROAD_SIZE / 2, 0);
		new CarGenerator(locator, pos, 0.5f, graph, true);
		
		pos = new Vector(0, NON_ROAD_SIZE + ROAD_SIZE / 2);
		new CarGenerator(locator, pos, 0.5f, graph, false);
		
		return context;
	}

}

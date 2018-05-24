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
	public static final int spaceSizeX = 232;
	public static final int spaceSizeY = 222;
	
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
	
	private void buildStraightUp(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);

		builder.setRectangle(new Rectangle(61, 0, 20, 40));
		builder.setEdge(graph.bot0, graph.mid0);
		builder.setDirection(new Vector(0, 1));
		builder.build();

		builder.setRectangle(new Rectangle(61, 81, 20, 60));
		builder.setEdge(graph.mid0, graph.mid3);
		builder.setDirection(new Vector(0, 1));
		builder.build();

		builder.setRectangle(new Rectangle(61, 182, 20, 40));
		builder.setEdge(graph.mid3, graph.top0);
		builder.setDirection(new Vector(0, 1));
		builder.build();

		builder.setRectangle(new Rectangle(172, 0, 20, 40));
		builder.setEdge(graph.bot1, graph.mid1);
		builder.setDirection(new Vector(0, 1));
		builder.build();

		builder.setRectangle(new Rectangle(172, 81, 20, 60));
		builder.setEdge(graph.mid1, graph.mid2);
		builder.setDirection(new Vector(0, 1));
		builder.build();

		builder.setRectangle(new Rectangle(172, 182, 20, 40));
		builder.setEdge(graph.mid2, graph.top1);
		builder.setDirection(new Vector(0, 1));
		builder.build();
	}
	
	private void buildStraightDown(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);

		builder.setRectangle(new Rectangle(40, 0, 20, 40));
		builder.setEdge(graph.mid0, graph.bot0);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(40, 81, 20, 60));
		builder.setEdge(graph.mid3, graph.mid0);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(40, 182, 20, 40));
		builder.setEdge(graph.top0, graph.mid3);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(151, 0, 20, 40));
		builder.setEdge(graph.mid1, graph.bot1);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(151, 81, 20, 60));
		builder.setEdge(graph.mid2, graph.mid1);
		builder.setDirection(new Vector(0, -1));
		builder.build();

		builder.setRectangle(new Rectangle(151, 182, 20, 40));
		builder.setEdge(graph.top1, graph.mid2);
		builder.setDirection(new Vector(0, -1));
		builder.build();
	}

	private void buildStraightRight(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);
		
		builder.setRectangle(new Rectangle(0, 40, 40, 20));
		builder.setEdge(graph.left0, graph.mid0);
		builder.setDirection(new Vector(1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(81, 40, 70, 20));
		builder.setEdge(graph.mid0, graph.mid1);
		builder.setDirection(new Vector(1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(192, 40, 40, 20));
		builder.setEdge(graph.mid1, graph.right0);
		builder.setDirection(new Vector(1, 0));
		builder.build();
		
		builder.setRectangle(new Rectangle(0, 141, 40, 20));
		builder.setEdge(graph.left1, graph.mid3);
		builder.setDirection(new Vector(1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(81, 141, 70, 20));
		builder.setEdge(graph.mid3, graph.mid2);
		builder.setDirection(new Vector(1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(192, 141, 40, 20));
		builder.setEdge(graph.mid2, graph.right1);
		builder.setDirection(new Vector(1, 0));
		builder.build();

	}

	private void buildStraightLeft(ServiceLocator locator, TrafficGraph graph) {
		StraightBuilder builder = new StraightBuilder(locator);
		
		builder.setRectangle(new Rectangle(0, 61, 40, 20));
		builder.setEdge(graph.mid0, graph.left0);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(81, 61, 70, 20));
		builder.setEdge(graph.mid1, graph.mid0);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(192, 61, 40, 20));
		builder.setEdge(graph.right0, graph.mid1);
		builder.setDirection(new Vector(-1, 0));
		builder.build();
		
		builder.setRectangle(new Rectangle(0, 162, 40, 20));
		builder.setEdge(graph.mid3, graph.left1);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(81, 162, 70, 20));
		builder.setEdge(graph.mid2, graph.mid3);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

		builder.setRectangle(new Rectangle(192, 162, 40, 20));
		builder.setEdge(graph.right1, graph.mid2);
		builder.setDirection(new Vector(-1, 0));
		builder.build();

	}
	
	private void buildStraightRoad(ServiceLocator locator, TrafficGraph graph) {
		buildStraightUp(locator, graph);
		buildStraightDown(locator, graph);
		buildStraightRight(locator, graph);
		buildStraightLeft(locator, graph);
	}
	
	private void buildCrossSection(ServiceLocator locator, TrafficGraph graph) {
		TurningBuilder builder = new TurningBuilder(locator);

		builder.setRectangle(new Rectangle(40, 40, 41, 41));
		builder.setNodes(graph.mid0, graph.mid3, graph.bot0, graph.left0, graph.mid1);
		builder.build();

		builder.setRectangle(new Rectangle(151, 40, 41, 41));
		builder.setNodes(graph.mid1, graph.mid2, graph.bot1, graph.mid0, graph.right0);
		builder.build();

		builder.setRectangle(new Rectangle(151, 141, 41, 41));
		builder.setNodes(graph.mid2, graph.top1, graph.mid1, graph.mid3, graph.right1);
		builder.build();

		builder.setRectangle(new Rectangle(40, 141, 41, 41));
		builder.setNodes(graph.mid3, graph.top0, graph.mid0, graph.left1, graph.mid2);
		builder.build();
	}

	private void buildRoad(ServiceLocator locator, TrafficGraph graph) {
		buildStraightRoad(locator, graph);
		buildCrossSection(locator, graph);
	}

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("ProjectII");
		
		ServiceLocator locator = createServices(context);
		TrafficGraph graph = new TrafficGraph(locator);
		
		buildRoad(locator, graph);
		
		new CarGenerator(locator, new Vector(71, 0), 0.5f, true);
		new CarGenerator(locator, new Vector(231, 172), 0.5f, false);
		new CarGenerator(locator, new Vector(50, 221), 0.5f, true);

		return context;
	}

}

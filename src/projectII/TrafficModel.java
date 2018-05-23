package projectII;

import projectII.builder.NormalRoadBuilder;
import projectII.builder.TurningRoadBuilder;
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
	public static final int ROAD_SIZE = 20;
	
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

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("ProjectII");
		
		ServiceLocator locator = createServices(context);
		TrafficGraph graph = new TrafficGraph(locator);
		
		NormalRoadBuilder normalBuilder = new NormalRoadBuilder(locator, graph);
		normalBuilder.build();

		TurningRoadBuilder turningBuilder = new TurningRoadBuilder(locator, graph);
		turningBuilder.build();
		
		Vector pos = new Vector(SPACE_SIZE / 2 + 7, 0);
		new CarGenerator(locator, pos, 0.5f, graph);
		
		return context;
	}

}

package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;

public class ServiceLocator {
	private final Context<Object> context;
	private final Grid<Object> grid;
	private final ContinuousSpace<Object> space;
	private final Network<Object> net;
	
	public ServiceLocator(Context<Object> context, Grid<Object> grid, 
			ContinuousSpace<Object> space, Network<Object> net) {
		this.context = context;
		this.grid = grid;
		this.space = space;
		this.net = net;
	}
	
	public Context<Object> getContext() {
		return context;
	}
	
	public Grid<Object> getGrid() {
		return grid;
	}
	
	public ContinuousSpace<Object> getSpace() {
		return space;
	}
	
	public Network<Object> getNet() {
		return net;
	}
}

package projectII;

import repast.simphony.space.graph.RepastEdge;

public class NormalRoad extends Road {
	private final RepastEdge<Object> edge;
	private final Vector direction;

	public NormalRoad(ServiceLocator locator,
			Vector pos, RepastEdge<Object> edge, Vector direction) {
		super(locator, pos);
		this.edge = edge;
		this.direction = direction;
	}
	
	public RepastEdge<Object> getEdge() {
		return edge;
	}
	
	public Vector getDirection() {
		return direction;
	}
}

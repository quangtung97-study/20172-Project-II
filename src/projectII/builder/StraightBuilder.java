package projectII.builder;

import projectII.NormalRoad;
import projectII.ServiceLocator;
import projectII.TrafficNode;
import projectII.Vector;
import repast.simphony.space.graph.RepastEdge;

public class StraightBuilder {
	private final ServiceLocator locator;
	private Rectangle rec;
	private TrafficNode from, to;
	private Vector direction;
	
	public StraightBuilder(ServiceLocator locator) {
		this.locator = locator;
	}
	
	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}
	
	public void setEdge(TrafficNode from, TrafficNode to) {
		this.from = from;
		this.to = to;
	}
	
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
	public void build() {
		RepastEdge<Object> edge = locator.getNet().getEdge(from, to);
		for (int x = rec.getX(); x < rec.getEndX(); x++)
			for (int y = rec.getY(); y < rec.getEndY(); y++) {
				Vector pos = new Vector(x, y);
				new NormalRoad(locator, pos, edge, direction);
			}
	}
}

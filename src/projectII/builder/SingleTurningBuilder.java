package projectII.builder;

import projectII.EdgePair;
import projectII.ServiceLocator;
import projectII.TrafficNode;
import projectII.TurningRoad;
import repast.simphony.space.graph.RepastEdge;

public class SingleTurningBuilder {
	private final ServiceLocator locator;
	private DirectionCalculator cal;
	private Rectangle rec;
	private TrafficNode center, forward, backward, left, right;
	
	public SingleTurningBuilder(ServiceLocator locator) {
		this.locator = locator;
	}

	public void setNodes(
			TrafficNode center,
			TrafficNode forward,
			TrafficNode backward,
			TrafficNode left,
			TrafficNode right)
	{
		this.center = center;
		this.forward = forward;
		this.backward = backward;
		this.left = left;
		this.right = right;
	}
	
	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}
	
	public void setDirectionCalculator(DirectionCalculator cal) {
		this.cal = cal;
	}
	
	public void build() {
		cal.setRectangle(rec);
		int index = 0;

		for (int x = rec.getX(); x < rec.getEndX(); x++) 
			for (int y = rec.getY(); y < rec.getEndY(); y++) {
				TurningRoad road = null;

				Iterable<Object> objects = locator.getGrid().getObjectsAt(x, y);
				for (Object o: objects)
					if (o instanceof TurningRoad) {
						road = (TurningRoad) o;
						break;
					}
						
				index = road.getDirections().size();

				road.getDirections().add(cal.getForward(x, y));
				road.getDirections().add(cal.getBackward(x, y));
				road.getDirections().add(cal.getLeft(x, y));
				road.getDirections().add(cal.getRight(x, y));
			}

		RepastEdge<Object> startEdge = locator.getNet().getEdge(backward, center);
		RepastEdge<Object> forwardEdge = locator.getNet().getEdge(center, forward);
		RepastEdge<Object> backwardEdge = locator.getNet().getEdge(center, backward);
		RepastEdge<Object> leftEdge = locator.getNet().getEdge(center, left);
		RepastEdge<Object> rightEdge = locator.getNet().getEdge(center, right);
		
		center.getIndexMap().put(new EdgePair(startEdge, forwardEdge), index++);
		center.getIndexMap().put(new EdgePair(startEdge, backwardEdge), index++);
		center.getIndexMap().put(new EdgePair(startEdge, leftEdge), index++);
		center.getIndexMap().put(new EdgePair(startEdge, rightEdge), index++);
	}
}

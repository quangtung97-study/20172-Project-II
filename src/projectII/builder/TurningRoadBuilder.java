package projectII.builder;

import projectII.EdgePair;
import projectII.ServiceLocator;
import projectII.TrafficGraph;
import projectII.TrafficNode;
import projectII.TurningRoad;
import projectII.Vector;
import repast.simphony.space.graph.RepastEdge;

public class TurningRoadBuilder extends RoadBuilder {
	private final TrafficGraph graph;
	private int x0 = NON_ROAD_SIZE;
	private int y0 = NON_ROAD_SIZE;
	private int x1 = SPACE_SIZE - NON_ROAD_SIZE;
	private int y1 = SPACE_SIZE - NON_ROAD_SIZE;
	
	public TurningRoadBuilder(ServiceLocator locator, TrafficGraph graph) {
		super(locator);
		this.graph = graph;
	}
	
	private Vector getDirectionRight(int x, int y) {
		float xc = y1 - 1;
		float yc = y0;
		
		float dirX = (y - yc);
		float dirY = -(x - xc);
		float d2 = dirX * dirX + dirY * dirY;

		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	private Vector getDirectionUp(int x, int y) {
		return new Vector(0, 1);
	}

	private Vector getDirectionLeft(int x, int y) {
		float dirX = -(y - y0);
		float dirY = (x - x0);
		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	private Vector getDirectionDown(int x, int y) {
		float xc = (x0 + x1) / 2;
		float yc = y0;

		float dirX = -(y - yc);
		float dirY = (x - xc);

		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}
	
	@Override
	public void build() {
		for (int x = x0; x < x1; x++)
			for (int y = y0; y < y1; y++) {
				TurningRoad road = new TurningRoad(locator, new Vector(x, y));
				road.getDirections().add(getDirectionRight(x, y));
				road.getDirections().add(getDirectionUp(x, y));
				road.getDirections().add(getDirectionLeft(x, y));
				road.getDirections().add(getDirectionDown(x, y));
			}

		TrafficNode a = graph.bot;
		TrafficNode b = graph.mid;
		
		RepastEdge<Object> start = locator.getNet().getEdge(a, b);

		RepastEdge<Object> right = locator.getNet().getEdge(b, graph.right);
		RepastEdge<Object> up = locator.getNet().getEdge(b, graph.top);
		RepastEdge<Object> left = locator.getNet().getEdge(b, graph.left);
		RepastEdge<Object> down = locator.getNet().getEdge(b, graph.bot);
		
		
		graph.indexMap.put(new EdgePair(start, right), 0);
		graph.indexMap.put(new EdgePair(start, up), 1);
		graph.indexMap.put(new EdgePair(start, left), 2);
		graph.indexMap.put(new EdgePair(start, down), 3);
	}
}

package projectII.builder;

import projectII.NormalRoad;
import projectII.ServiceLocator;
import projectII.TrafficGraph;
import projectII.TrafficNode;
import projectII.Vector;
import repast.simphony.space.graph.RepastEdge;

public class NormalRoadBuilder extends RoadBuilder {
	private final TrafficGraph graph;
	
	public NormalRoadBuilder(ServiceLocator locator, TrafficGraph graph) {
		super(locator);
		this.graph = graph;
	}
	
	private static class RoadInfo {
		public final TrafficNode begin, end;
		public final Vector direction;
		
		public RoadInfo(TrafficNode begin, TrafficNode end, int x, int y) {
			this.begin = begin;
			this.end = end;
			this.direction = new Vector(x, y);
		}
	}
	
	@Override
	public void build() {
		for (int x = 0; x < SPACE_SIZE; x++) {
			for (int y = 0; y < SPACE_SIZE; y++) {
				RoadInfo info = getRoadInfo(x, y);
				if (info == null)
					continue;
				Vector pos = new Vector(x, y);
				RepastEdge<Object> edge = locator.getNet().getEdge(info.begin, info.end);
				new NormalRoad(locator, pos, edge, info.direction);
			}
		}
	}
	
	private RoadInfo getRoadInfo(int x, int y) {
		if (Util.insideRectangle(x, y, PIVOT + 1, 0, ROAD_SIZE, NON_ROAD_SIZE))
			return new RoadInfo(graph.bot, graph.mid, 0, 1);
		if (Util.insideRectangle(x, y, PIVOT + 1, SPACE_SIZE - NON_ROAD_SIZE, ROAD_SIZE, NON_ROAD_SIZE))
			return new RoadInfo(graph.mid, graph.top, 0, 1);

		if (Util.insideRectangle(x, y, NON_ROAD_SIZE, 0, ROAD_SIZE, NON_ROAD_SIZE))
			return new RoadInfo(graph.mid, graph.bot, 0, -1);
		if (Util.insideRectangle(x, y, NON_ROAD_SIZE, SPACE_SIZE - NON_ROAD_SIZE, ROAD_SIZE, NON_ROAD_SIZE))
			return new RoadInfo(graph.top, graph.mid, 0, -1);

		if (Util.insideRectangle(x, y, 0, NON_ROAD_SIZE, NON_ROAD_SIZE, ROAD_SIZE))
			return new RoadInfo(graph.left, graph.mid, 1, 0);
		if (Util.insideRectangle(x, y, SPACE_SIZE - NON_ROAD_SIZE, NON_ROAD_SIZE, NON_ROAD_SIZE, ROAD_SIZE))
			return new RoadInfo(graph.mid, graph.right, 1, 0);

		if (Util.insideRectangle(x, y, 0, PIVOT + 1, NON_ROAD_SIZE, ROAD_SIZE))
			return new RoadInfo(graph.mid, graph.left, -1, 0);
		if (Util.insideRectangle(x, y, SPACE_SIZE - NON_ROAD_SIZE, PIVOT + 1, NON_ROAD_SIZE, ROAD_SIZE))
			return new RoadInfo(graph.right, graph.mid, -1, 0);

		return null;
	}
}

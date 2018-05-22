package projectII;

import java.util.Map;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.graph.RepastEdge;

public class Car extends Agent {
	private float velocity;
	private boolean turning = false;
	private int directionIndex;
	private final Map<EdgePair, Integer> indexMap;
	private RepastEdge<Object> currentEdge;
	
	public Car(ServiceLocator locator, Vector pos, float v, TrafficGraph graph, 
			Map<EdgePair, Integer> indexMap) {
		super(locator, pos);
		this.velocity = v;
		this.indexMap = indexMap;
	}
	
	private void moveByNormalRoad(NormalRoad road) {
		turning = false;
		currentEdge = road.getEdge();
		Vector dir = road.getDirection();
		Vector trans = dir.mul(velocity);
		moveTo(pos.add(trans));
	}
	
	private int computeDirectionIndex() {
		Object target = currentEdge.getTarget();
		Iterable<RepastEdge<Object>> edges = locator.getNet().getOutEdges(target);
		
		int count = 0;
		for (@SuppressWarnings("unused") RepastEdge<Object> edge: edges)
			count++;

		int rand = RandomHelper.nextIntFromTo(0,  count - 1);
		edges = locator.getNet().getOutEdges(target);

		int index = 0;
		for (RepastEdge<Object> edge: edges) {
			if (index == rand) {
				return indexMap.get(new EdgePair(currentEdge, edge));
			}
			index++;
		}
		return -1;
	}
	
	private void moveByTurningRoad(TurningRoad road) {
		if (!turning) {
			turning = true;
			directionIndex = computeDirectionIndex();
		}
		
		Vector direction = road.getDirections().get(directionIndex);
		Vector trans = direction.mul(velocity);
		moveTo(pos.add(trans));
	}
	
	private Road getCurrentRoad() {
		Iterable<Object> objects = locator.getGrid().getObjectsAt(
				(int)pos.x, (int)pos.y);
		for (Object o: objects) {
			if (o instanceof Road) 
				return (Road)o;
		}
		return null;
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = 2)
	public void step() {
		Road road = getCurrentRoad();
		if (road instanceof NormalRoad)
			moveByNormalRoad((NormalRoad)road);
		else if (road instanceof TurningRoad)
			moveByTurningRoad((TurningRoad)road);
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = 1)
	public void postStep() {
		applyMove();

		float x = pos.x;
		float y = pos.y;
		if (x < 0 || x >= TrafficModel.SPACE_SIZE ||
			y < 0 || y >= TrafficModel.SPACE_SIZE)
			removeThis();
	}
}

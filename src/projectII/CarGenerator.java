package projectII;

import java.util.Map;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;

public class CarGenerator {
	private final ServiceLocator locator;
	private final TrafficGraph graph;
	private Vector pos;
	private float v;
	private final Map<EdgePair, Integer> indexMap;

	public CarGenerator(
			ServiceLocator locator,
			Vector pos, float v, 
			TrafficGraph graph, 
			Map<EdgePair, Integer> indexMap) 
	{
		this.locator = locator;
		this.pos = pos;
		this.v = v;
		this.graph = graph;
		this.indexMap = indexMap;
		locator.getContext().add(this);
	}

	@ScheduledMethod(start = 1, interval = 10, priority = 1)
	public void generate() {
		int d = RandomHelper.nextIntFromTo(-5, 5);
		Vector trans = new Vector(d, 0);
		new Car(locator, pos.add(trans), v, graph, indexMap);
	}
}

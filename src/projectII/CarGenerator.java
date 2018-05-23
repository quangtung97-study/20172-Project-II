package projectII;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;

public class CarGenerator {
	private final ServiceLocator locator;
	private Vector pos;
	private float v;

	public CarGenerator(
			ServiceLocator locator,
			Vector pos, float v, 
			TrafficGraph graph) 
	{
		this.locator = locator;
		this.pos = pos;
		this.v = v;
		locator.getContext().add(this);
	}

	@ScheduledMethod(start = 1, interval = 10, priority = 1)
	public void generate() {
		int d = RandomHelper.nextIntFromTo(-5, 5);
		Vector trans = new Vector(d, 0);
		new Car(locator, pos.add(trans), v);
	}
}

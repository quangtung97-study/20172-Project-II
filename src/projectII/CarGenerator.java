package projectII;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;

public class CarGenerator {
	private final ServiceLocator locator;
	private Vector pos;
	private float v;
	private final boolean isVertical;

	public CarGenerator(
			ServiceLocator locator,
			Vector pos, float v, 
			TrafficGraph graph, 
			boolean isVertical) 
	{
		this.locator = locator;
		this.pos = pos;
		this.v = v;
		this.isVertical = isVertical;
		
		locator.getContext().add(this);
	}

	@ScheduledMethod(start = 1, interval = 30, priority = 1)
	public void generate() {
		int d = RandomHelper.nextIntFromTo(-5, 5);
		Vector trans;
		
		if (isVertical)
			trans = new Vector(d, 0);
		else
			trans = new Vector(0, d);

		new Car(locator, pos.add(trans), v);
	}
}

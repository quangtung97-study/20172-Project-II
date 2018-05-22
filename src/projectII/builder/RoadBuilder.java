package projectII.builder;

import projectII.ServiceLocator;
import projectII.TrafficModel;

public abstract class RoadBuilder {
	public static final int SPACE_SIZE = TrafficModel.SPACE_SIZE;
	public static final int ROAD_SIZE = TrafficModel.ROAD_SIZE;
	public static final int NON_ROAD_SIZE = (SPACE_SIZE - 1 - ROAD_SIZE * 2) / 2;
	public static final int PIVOT = SPACE_SIZE / 2;

	protected final ServiceLocator locator;
	
	public RoadBuilder(ServiceLocator locator) {
		this.locator = locator;
	}
	
	public abstract void build();
}

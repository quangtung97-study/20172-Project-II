package projectII;

import java.util.ArrayList;
import java.util.List;

public class TurningRoad extends Road {
	private final List<Vector> directions = new ArrayList<>();

	public TurningRoad(ServiceLocator locator, Vector pos) {
		super(locator, pos);
	}
	
	public List<Vector> getDirections() {
		return directions;
	}
}

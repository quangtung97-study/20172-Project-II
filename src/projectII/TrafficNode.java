package projectII;

import java.util.HashMap;
import java.util.Map;

public class TrafficNode extends Agent {
	public enum Type {
		CROSS_ROAD,
		TERMINAL
	};
	
	private final Type type;
	private Map<EdgePair, Integer> indexMap = null;
	
	public TrafficNode(ServiceLocator locator, Vector pos, Type type) {
		super(locator, pos);
		this.type = type;
		if (type == Type.CROSS_ROAD)
			this.indexMap = new HashMap<>();
	}
	
	public Type getType() {
		return type;
	}
	
	public Map<EdgePair, Integer> getIndexMap() {
		return indexMap;
	}
}

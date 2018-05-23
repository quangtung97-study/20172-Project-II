package projectII;

import java.util.Map;

public class TrafficNode extends Agent {
	public enum Type {
		CROSS_ROAD,
		TERMINAL
	};
	
	private final Type type;
	private final Map<EdgePair, Integer> indexMap;
	
	public TrafficNode(ServiceLocator locator, Vector pos, Type type,
			Map<EdgePair, Integer> indexMap) {
		super(locator, pos);
		this.type = type;
		this.indexMap = indexMap;
	}
	
	public Type getType() {
		return type;
	}
	
	public Map<EdgePair, Integer> getIndexMap() {
		return indexMap;
	}
}

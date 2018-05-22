package projectII;

public class TrafficNode extends Agent {
	public enum Type {
		CROSS_ROAD,
		TERMINAL
	};
	
	private final Type type;
	
	public TrafficNode(ServiceLocator locator, Vector pos, Type type) {
		super(locator, pos);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}

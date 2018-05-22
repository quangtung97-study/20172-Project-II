package projectII;

public class Agent {
	protected final ServiceLocator locator;
	protected Vector pos;
	protected Vector newPos;
	
	public Agent(ServiceLocator locator, Vector pos) {
		this.locator = locator;
		locator.getContext().add(this);

		moveTo(pos);
		applyMove();
	}

	protected void moveTo(Vector pos) {
		this.newPos = pos;
	}
	
	protected void applyMove() {
		pos = newPos;
		locator.getGrid().moveTo(this, Math.round(pos.x), Math.round(pos.y));
		locator.getSpace().moveTo(this, pos.x, pos.y);
	}
	
	protected void removeThis() {
		locator.getContext().remove(this);
	}
	
	public Vector getPos() {
		return pos;
	}
	
}

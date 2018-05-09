package projectII;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Agent {
	protected final Context<Object> context;
	protected final Grid<Object> grid;
	protected final ContinuousSpace<Object> space;
	protected float x, y;
	protected float newX, newY;
	
	public Agent(
			Context<Object> context, 
			Grid<Object> grid, ContinuousSpace<Object> space,
			float x, float y) {
		this.context = context;
		this.grid = grid;
		this.space = space;
		
		context.add(this);

		moveTo(x, y);
		applyMove();
	}

	protected void moveTo(float x, float y) {
		this.newX = x;
		this.newY = y;
	}
	
	protected void applyMove() {
		this.x = newX;
		this.y = newY;
		grid.moveTo(this, Math.round(x), Math.round(y));
		space.moveTo(this, x, y);
	}
	
	protected void removeThis() {
		context.remove(this);
	}
	
	public float getX() { 
		return x;
	}
	
	public float getY() {
		return y;
	}
}

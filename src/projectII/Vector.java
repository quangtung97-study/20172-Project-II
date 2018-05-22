package projectII;

public class Vector {
	public final float x, y;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y);
	}
	
	public Vector mul(float num) {
		return new Vector(x * num, y * num);
	}
}

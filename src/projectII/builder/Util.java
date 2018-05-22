package projectII.builder;

public class Util {
	
	public static boolean insideRectangle(int x, int y, int rx, int ry, int w, int h) {
		if (x < rx || x >= rx + w)
			return false;
		if (y < ry || y >= ry + h)
			return false;
		return true;
	}

}

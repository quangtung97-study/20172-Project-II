package projectII.builder;

import projectII.EdgePair;
import projectII.ServiceLocator;
import projectII.TrafficNode;
import projectII.TurningRoad;
import projectII.Vector;
import repast.simphony.space.graph.RepastEdge;

public class TurningBuilder {
	private final ServiceLocator locator;
	private Rectangle rec;
	private TrafficNode mid, top, bot, left, right;

	public TurningBuilder(ServiceLocator locator) {
		this.locator = locator;
	}
	
	public void setNodes(
			TrafficNode mid,
			TrafficNode top,
			TrafficNode bot,
			TrafficNode left,
			TrafficNode right)
	{
		this.mid = mid;
		this.top = top;
		this.bot = bot;
		this.left = left;
		this.right = right;
	}
	
	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}
	
	private Vector getDirectionRight(int x, int y) {
		float xc = rec.getEndX() - 1;
		float yc = rec.getY();
		
		float dirX = (y - yc);
		float dirY = -(x - xc);
		float d2 = dirX * dirX + dirY * dirY;

		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	private Vector getDirectionUp(int x, int y) {
		return new Vector(0, 1);
	}

	private Vector getDirectionLeft(int x, int y) {
		float dirX = -(y - rec.getY());
		float dirY = (x - rec.getX());
		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}

	private Vector getDirectionDown(int x, int y) {
		float xc = (rec.getX() + rec.getEndX()) / 2;
		float yc = rec.getY();

		float dirX = -(y - yc);
		float dirY = (x - xc);

		float d2 = dirX * dirX + dirY * dirY;
		float d= (float)Math.sqrt(d2);
		dirX /= d;
		dirY /= d;
		return new Vector(dirX, dirY);
	}
	
	
	public void build() {
		for (int x = rec.getX(); x < rec.getEndX(); x++) 
			for (int y = rec.getY(); y < rec.getEndY(); y++) {
				TurningRoad road = new TurningRoad(locator, new Vector(x, y));
				road.getDirections().add(getDirectionRight(x, y));
				road.getDirections().add(getDirectionUp(x, y));
				road.getDirections().add(getDirectionLeft(x, y));
				road.getDirections().add(getDirectionDown(x, y));
			}

		RepastEdge<Object> startEdge = locator.getNet().getEdge(bot, mid);
		RepastEdge<Object> rightEdge = locator.getNet().getEdge(mid, right);
		RepastEdge<Object> upEdge = locator.getNet().getEdge(mid, top);
		RepastEdge<Object> leftEdge = locator.getNet().getEdge(mid, left);
		RepastEdge<Object> downEdge = locator.getNet().getEdge(mid, bot);
		
		int index = 0;
		mid.getIndexMap().put(new EdgePair(startEdge, rightEdge), index++);
		mid.getIndexMap().put(new EdgePair(startEdge, upEdge), index++);
		mid.getIndexMap().put(new EdgePair(startEdge, leftEdge), index++);
		mid.getIndexMap().put(new EdgePair(startEdge, downEdge), index++);
	}
}

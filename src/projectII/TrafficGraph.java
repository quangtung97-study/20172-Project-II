package projectII;

import repast.simphony.space.graph.Network;

public class TrafficGraph {
	public final TrafficNode mid;
	public final TrafficNode bot;
	public final TrafficNode top;
	public final TrafficNode left;
	public final TrafficNode right;
	
	public TrafficGraph(ServiceLocator locator) {
		Vector pos = null;
		final int space_size = 101;
		
		pos = new Vector(space_size / 2, space_size / 2);
		mid = new TrafficNode(locator, pos, TrafficNode.Type.CROSS_ROAD);

		pos = new Vector(space_size / 2, 0);
		bot = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);

		pos = new Vector(space_size / 2, space_size - 1);
		top = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);

		pos = new Vector(0, space_size / 2);
		left = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);

		pos = new Vector(space_size - 1, space_size / 2);
		right = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		
		Network<Object> net = locator.getNet();

		net.addEdge(bot, mid);
		net.addEdge(mid, bot);

		net.addEdge(top, mid);
		net.addEdge(mid, top);

		net.addEdge(left, mid);
		net.addEdge(mid, left);

		net.addEdge(right, mid);
		net.addEdge(mid, right);
	}
}

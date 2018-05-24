package projectII;

import repast.simphony.space.graph.Network;

public class TrafficGraph {
	public final TrafficNode mid0, mid1, mid2, mid3;
	public final TrafficNode bot0, bot1;
	public final TrafficNode top0, top1;
	public final TrafficNode left0, left1;
	public final TrafficNode right0, right1;
	
	public TrafficGraph(ServiceLocator locator) {
		Vector pos = null;

		pos = new Vector(60, 60);
		mid0 = new TrafficNode(locator, pos, TrafficNode.Type.CROSS_ROAD);
		pos = new Vector(171, 60);
		mid1 = new TrafficNode(locator, pos, TrafficNode.Type.CROSS_ROAD);
		pos = new Vector(171, 161);
		mid2 = new TrafficNode(locator, pos, TrafficNode.Type.CROSS_ROAD);
		pos = new Vector(60, 161);
		mid3 = new TrafficNode(locator, pos, TrafficNode.Type.CROSS_ROAD);
		
		pos = new Vector(60, 0);
		bot0 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		pos = new Vector(171, 0);
		bot1 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);

		pos = new Vector(60, 221);
		top0 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		pos = new Vector(171, 221);
		top1 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		
		pos = new Vector(0, 60);
		left0 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		pos = new Vector(0, 161);
		left1 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);

		pos = new Vector(231, 60);
		right0 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		pos = new Vector(231, 161);
		right1 = new TrafficNode(locator, pos, TrafficNode.Type.TERMINAL);
		
		Network<Object> net = locator.getNet();
		
		net.addEdge(mid0, bot0);
		net.addEdge(bot0, mid0);
		net.addEdge(mid0, left0);
		net.addEdge(left0, mid0);
		
		net.addEdge(mid1, bot1);
		net.addEdge(bot1, mid1);
		net.addEdge(mid1, right0);
		net.addEdge(right0, mid1);
		
		net.addEdge(mid2, right1);
		net.addEdge(right1, mid2);
		net.addEdge(mid2, top1);
		net.addEdge(top1, mid2);
		
		net.addEdge(mid3, top0);
		net.addEdge(top0, mid3);
		net.addEdge(mid3, left1);
		net.addEdge(left1, mid3);

		net.addEdge(mid0, mid1);
		net.addEdge(mid1, mid0);

		net.addEdge(mid1, mid2);
		net.addEdge(mid2, mid1);

		net.addEdge(mid2, mid3);
		net.addEdge(mid3, mid2);

		net.addEdge(mid3, mid0);
		net.addEdge(mid0, mid3);
	}
}

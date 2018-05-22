package projectII;

import repast.simphony.space.graph.RepastEdge;

public class EdgePair {
	public final RepastEdge<Object> a, b;
	
	public EdgePair(RepastEdge<Object> a, RepastEdge<Object> b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() ^ b.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof EdgePair) {
			EdgePair other = (EdgePair)o;
			if (other.hashCode() == this.hashCode())
				return true;
		}
		return false;
	}
}

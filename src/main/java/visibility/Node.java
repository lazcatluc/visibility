package visibility;

import java.util.HashSet;
import java.util.Set;

public class Node {
	
	private Set<Node> children = new HashSet<>();

	public Set<Node> getVisibleChildren() {
		return children;
	}

	public void addChild(Node child) {
		children.add(child);
	}

}

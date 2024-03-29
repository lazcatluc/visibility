package visibility;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Node {

	private final int id;
	private Set<Node> children = new HashSet<>();
	private boolean hidden;
	
	public Node() {
		this(-1);
	}
	
	public Node(int id) {
		this.id = id;
	}

	public Set<Node> getVisibleChildren() {
		Set<Node> visible = Collections.synchronizedSet(new HashSet<>());
		visibleChildren().forEach(visible::add);
		visibleChildren().map(Node::getVisibleChildren).forEach(visible::addAll);
		return visible;
	}

	private Stream<Node> visibleChildren() {
		return children.stream().parallel().filter(Node::isVisible);
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public void hide() {
		hidden = true;
	}
	
	public boolean isVisible() {
		return !hidden;
	}

	public int getId() {
		return id;
	}

	public Set<Node> getChildren() {
		return children;
	}

}

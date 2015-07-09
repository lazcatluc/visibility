package visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Sampler {
	private static final Random RANDOM = new Random();
	private static final AtomicInteger SEQUENCE = new AtomicInteger(0);
	public static final List<Node> NODES = new ArrayList<>();
	
	private final int totalNodes;
	private final int totalHiddenNodes;
	private final int maxChildren;
	private final double probabilityOfHiddenNode;
	
	
	public Sampler(int totalNodes, int totalHiddenNodes, int maxChildren) {
		this.totalNodes = totalNodes;
		this.totalHiddenNodes = totalHiddenNodes;
		this.maxChildren = maxChildren;
		probabilityOfHiddenNode = 1.0 * totalHiddenNodes / totalNodes;
	}
	
	public List<Node> nodeSample(int sampleSize) {
		List<Node> ret = new ArrayList<>();
		ret.add(NODES.get(0));
		for (int i = 0; i < sampleSize - 2; i++) {
			ret.add(NODES.get(1+RANDOM.nextInt(NODES.size()-3)));
		}
		ret.add(NODES.get(NODES.size()-1));
		return ret;
	}

	public Node buildNetwork() {
		int children = RANDOM.nextInt(maxChildren);
		boolean rootIsHidden = RANDOM.nextDouble() < probabilityOfHiddenNode; 
		final int totalSubTreeNodes = totalNodes - 1;
		final int totalSubTreeHiddenNodes = totalHiddenNodes - (rootIsHidden ? 1 : 0);
		
		int totalNodesForChild = totalSubTreeNodes / (children + 1);
		int totalHiddenNodesForChild = totalSubTreeHiddenNodes / (children + 1);
		
		int totalNodesForLastChild = totalSubTreeNodes - children * totalNodesForChild;
		int totalHidenNodesForLastChild = totalSubTreeHiddenNodes - children * totalHiddenNodesForChild;
		
		Node root = new Node(SEQUENCE.incrementAndGet());
		NODES.add(root);
		if (rootIsHidden) {
			root.hide();
		}
		if (totalNodesForChild > 0) {
			for (int i = 0; i < children; i++) {
				root.addChild(new Sampler(totalNodesForChild, totalHiddenNodesForChild, maxChildren).buildNetwork());
			}
		}
		if (totalNodesForLastChild > 0) {
			root.addChild(new Sampler(totalNodesForLastChild, totalHidenNodesForLastChild, maxChildren).buildNetwork());
		}
		return root;
	}
}

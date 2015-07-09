package visibility;

import java.util.Random;

public class Sampler {
	private static final Random RANDOM = new Random();
	
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

	public Node buildNetwork() {
		int children = RANDOM.nextInt(maxChildren - 1);
		boolean rootIsHidden = RANDOM.nextDouble() < probabilityOfHiddenNode; 
		final int totalSubTreeNodes = totalNodes - 1;
		final int totalSubTreeHiddenNodes = totalHiddenNodes - (rootIsHidden ? 1 : 0);
		
		int totalNodesForChild = totalSubTreeNodes / (children + 1);
		int totalHiddenNodesForChild = totalSubTreeHiddenNodes / (children + 1);
		
		int totalNodesForLastChild = totalSubTreeNodes - children * totalNodesForChild;
		int totalHidenNodesForLastChild = totalSubTreeHiddenNodes - children * totalHiddenNodesForChild;
		
		Node root = new Node();
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

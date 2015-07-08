package visibility;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

public class NodeTest {
	@Test
	public void emptyNodeCanViewNothing() throws Exception {
		Node parent = new Node();
		assertEquals(Collections.emptySet(), parent.getVisibleChildren());
	}
	
	@Test
	public void nodeWithChildCanViewChild() throws Exception {
		Node parent = new Node();
		Node child = new Node();
		parent.addChild(child);
		
		assertEquals(Collections.singleton(child), parent.getVisibleChildren());
	}
}

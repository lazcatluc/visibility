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
	
	@Test
	public void nodeCanViewChildOfChild() throws Exception {
		Node parent = new Node();
		Node child = new Node();
		parent.addChild(child);
		Node childOfChild = new Node();
		child.addChild(childOfChild);
		
		assertTrue(parent.getVisibleChildren().contains(childOfChild));
	}
	
	@Test
	public void childCanHideFromParentNode() throws Exception {
		Node parent = new Node();
		Node child = new Node();
		parent.addChild(child);
		
		child.hide();
		
		assertEquals(Collections.emptySet(), parent.getVisibleChildren());
	}
	
	@Test
	public void grandChildWithHiddenParentIsNotVisible() throws Exception {
		Node parent = new Node();
		Node child = new Node();
		parent.addChild(child);
		Node childOfChild = new Node();
		child.addChild(childOfChild);
		
		child.hide();
		
		assertFalse(parent.getVisibleChildren().contains(childOfChild));
	}
}

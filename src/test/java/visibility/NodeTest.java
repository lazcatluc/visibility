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
}

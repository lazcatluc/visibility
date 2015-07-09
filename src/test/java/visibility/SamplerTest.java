package visibility;

import static org.junit.Assert.*;

import org.junit.Test;

public class SamplerTest {
	@Test
	public void inNetworkWithoutHiddenNodesRootCanSeeEverything() throws Exception {
		Node root = new Sampler(1000, 0, 10).buildNetwork();

		assertEquals(999, root.getVisibleChildren().size());
	}
	
	@Test
	public void inNetworkWithAllHiddenNodesRootCanSeeEverything() throws Exception {
		Node root = new Sampler(1000, 1000, 10).buildNetwork();

		assertEquals(0, root.getVisibleChildren().size());
	}
	
	@Test
	public void quicklyDecidesNetworkWithMillionNodes() throws Exception {
		Node root = new Sampler(15000000, 5000000, 1000).buildNetwork();
		long millis = System.currentTimeMillis();
		System.out.println(root.getVisibleChildren().size());
		System.out.println(System.currentTimeMillis() - millis);
	}
}

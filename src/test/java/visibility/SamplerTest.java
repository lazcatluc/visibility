package visibility;

import static org.junit.Assert.*;

import java.util.List;

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
		final Sampler sampler = new Sampler(1500000, 500000, 10);
		sampler.buildNetwork();
		List<Node> sampleNodes = sampler.nodeSample(10);
		sampleNodes.forEach(root -> {
			long millis = System.currentTimeMillis();
			System.out.println(root.getId()+": "+root.getVisibleChildren().size()
					+" children in "+(System.currentTimeMillis() - millis)
					+" milliseconds"); 
		});
	}
}

/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.ReusablePool;

/**
 * Tests ReusablePool.
 * 
 * @author Álvaro Vázquez.
 * @author Mario Juez.
 *
 */
public class ReusablePoolTest {

	private ReusablePool rp;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rp = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Prueba si el método {@link ubu.gii.dass.c01.ReusablePool#getInstance()}
	 * devuelve un objeto de tipo ReusablePool.
	 */
	@Test
	public void testGetInstance_inicial() {
		Assert.assertThat(rp, instanceOf(ReusablePool.class));
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}
	 * .
	 */
	@Test
	public void testReleaseReusable() {
		fail("Not yet implemented");
	}

}

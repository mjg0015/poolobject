/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
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
	private Vector<Reusable> reusables;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rp = ReusablePool.getInstance();
		reusables = new Vector<Reusable>(2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		for (Reusable r : reusables) {
			rp.releaseReusable(r);
		}
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
	 * Prueba si el método {@link ubu.gii.dass.c01.ReusablePool#getInstance()}
	 * implementa correctamente el patrón Singletón.
	 */
	@Test
	public void testGetInstance_singleton() {
		Assert.assertEquals(rp, ReusablePool.getInstance());
	}

	/**
	 * Prueba si el método
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}. devuelve un
	 * objeto de tipo Reusable.
	 * 
	 * @throws NotFreeInstanceException
	 */
	@Test
	public void testAcquireOneReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		Assert.assertThat(reusables.get(0), instanceOf(Reusable.class));
	}

	/**
	 * Prueba si el método
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}. devuelve dos
	 * objetos de tipo Reusable.
	 * 
	 * @throws NotFreeInstanceException
	 */
	@Test
	public void testAcquireTwoReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		Assert.assertThat(reusables.get(0), instanceOf(Reusable.class));
		Assert.assertThat(reusables.get(1), instanceOf(Reusable.class));
	}

	/**
	 * Prueba si el método
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()} lanza excepción
	 * al intentar obtener un tercer Reusable.
	 * 
	 * @throws NotFreeInstanceException
	 */
	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireThreeReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
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

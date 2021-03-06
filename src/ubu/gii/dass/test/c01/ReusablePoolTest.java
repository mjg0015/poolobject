/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.hamcrest.CoreMatchers.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * Tests ReusablePool.
 * 
 * @author �lvaro V�zquez.
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
	 * Prueba si el m�todo {@link ubu.gii.dass.c01.ReusablePool#getInstance()}
	 * devuelve un objeto de tipo ReusablePool.
	 */
	@Test
	public void testGetInstance_inicial() {
		Assert.assertThat(rp, instanceOf(ReusablePool.class));
	}

	/**
	 * Prueba si el m�todo {@link ubu.gii.dass.c01.ReusablePool#getInstance()}
	 * implementa correctamente el patr�n Singleton.
	 */
	@Test
	public void testGetInstance_singleton() {
		Assert.assertEquals(rp, ReusablePool.getInstance());
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}. devuelve un
	 * objeto de tipo Reusable.
	 * 
	 * @throws NotFreeInstanceException.
	 */
	@Test
	public void testAcquireOneReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		Assert.assertThat(reusables.get(0), instanceOf(Reusable.class));
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}. devuelve dos
	 * objetos de tipo Reusable.
	 * 
	 * @throws NotFreeInstanceException.
	 */
	@Test
	public void testAcquireTwoReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		Assert.assertThat(reusables.get(0), instanceOf(Reusable.class));
		Assert.assertThat(reusables.get(1), instanceOf(Reusable.class));
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()} lanza excepci�n
	 * al intentar obtener un tercer Reusable.
	 * 
	 * @throws NotFreeInstanceException.
	 */
	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireThreeReusable() throws NotFreeInstanceException {
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable()} a�ade un Reusable
	 * al pool.
	 * 
	 * @throws NotFreeInstanceException.
	 * @throws DuplicatedInstanceException.
	 */
	@Test
	public void testReleaseOneReusable() throws NotFreeInstanceException,
			DuplicatedInstanceException {
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		rp.releaseReusable(reusables.get(0));
		Assert.assertEquals(reusables.get(0), rp.acquireReusable());
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable()} lanza
	 * DuplicatedInstanceException al usarlo dos veces con el mismo Reusable.
	 * 
	 * @throws NotFreeInstanceException.
	 * @throws DuplicatedInstanceException.
	 */
	@Test(expected = DuplicatedInstanceException.class)
	public void testReleaseOneReusableTwoTimes()
			throws NotFreeInstanceException, DuplicatedInstanceException {
		Reusable r = rp.acquireReusable();
		rp.releaseReusable(r);
		rp.releaseReusable(r);
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable()} a�ade
	 * correctamente dos objetos Reusable diferentes.
	 * 
	 * @throws DuplicatedInstanceException
	 * @throws NotFreeInstanceException.
	 */
	@Test
	public void testReleaseTwoReusable() throws NotFreeInstanceException,
			DuplicatedInstanceException {
		Reusable r1 = rp.acquireReusable();
		Reusable r2 = rp.acquireReusable();
		rp.releaseReusable(r1);
		rp.releaseReusable(r2);
		reusables.add(rp.acquireReusable());
		reusables.add(rp.acquireReusable());
		Assert.assertEquals(r2, reusables.get(0));
		Assert.assertEquals(r1, reusables.get(1));
	}

	/**
	 * Prueba si el m�todo
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable()} lanza una
	 * excepci�n al devolver m�s objetos de los que puede almacenar el pool.
	 * 
	 * @throws DuplicatedInstanceException.
	 * @throws NotFreeInstanceException.
	 */
	@Test(expected = Exception.class)
	public void testReleaseThreeReusable() throws NotFreeInstanceException,
			DuplicatedInstanceException {
		Reusable r1 = rp.acquireReusable();
		Reusable r2 = rp.acquireReusable();
		Reusable r3 = new Reusable();
		rp.releaseReusable(r1);
		rp.releaseReusable(r2);
		rp.releaseReusable(r3);
	}
}

package test;

import java.util.Set;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.transaction.annotation.Transactional;

import ch.amaba.dao.model.UserAdressEntity;
import ch.amaba.dao.model.UserAmiEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.exception.EntityNotFoundException;

/**
 * Unit test for simple App.
 */
public class DaoTest extends AbstractDaoTest {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DaoTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public DaoTest(final String testName) {
		super(testName);
	}

	public void testAjouterFavori() {
		try {

			// dao.ajouterFavori(1L, 2L);
			// dao.ajouterFavori(1L, 2L);
			dao.ajouterFavori(2L, 1L);

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test la modification d'une entité. Les champs version (+1) et
	 * dateModification (new Date()) doivent être mis à jour.
	 */
	@Transactional
	public void testModifier() {
		UserEntity load = (UserEntity) dao.get(UserEntity.class, 1L);
		load.setPrenom("Bobby");
		dao.saveOrUpdate(load);
		load = (UserEntity) dao.get(UserEntity.class, 1L);
		Assert.assertEquals("Bobby", load.getPrenom());
	}

	@Transactional
	public void testSupprimer() {
		// Creer un nouvel ami puis le supprimer
		final UserAmiEntity u = new UserAmiEntity();
		final UserEntity ue = new UserEntity();
		u.setEntityId(1L);
		u.setUserEntity(ue);
		final int ami = RandomUtils.nextInt();
		u.setIdAmi(new Long(ami));
		dao.save(u);
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dao.supprimer(u);
		} catch (final EntityNotFoundException e) {
			Assert.assertEquals(true, false);
		}

	}

	/**
	 * Rigourous Test :-)
	 */
	public void testChangerStatutMessage() {
		dao.changerMessageStatut(1L, 2L, TypeMessageStatutEnum.LU);
	}

	public void testCreerAdress() {
		final UserAdressEntity userAdressEntity = new UserAdressEntity();
		userAdressEntity.setIdCanton(27);
		userAdressEntity.setIdUser(3L);
		dao.save(userAdressEntity);
		Assert.assertNotNull(userAdressEntity.getDateModification());
	}

	public void testGetUser() {
		final UserEntity load = (UserEntity) dao.get(UserEntity.class, 1L);
		Assert.assertNotNull(load);
		Assert.assertEquals("Gomes", load.getNom());
	}

	public void testListeFavoris() {
		final Set<UserCriteria> listeFavoris = dao.listeFavoris(3L);
		for (final UserCriteria userCriteria : listeFavoris) {
			System.out.println(userCriteria.getIdUser());
		}
		Assert.assertNotNull(listeFavoris);
	}
}

package test;

import java.util.HashSet;
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
	 * Test la modification d'une entit�. Les champs version (+1) et
	 * dateModification (new Date()) doivent �tre mis � jour.
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
		final UserEntity ue = new UserEntity();
		ue.setEntityId(3L);
		final UserAmiEntity ami = new UserAmiEntity();
		ami.setEntityId(1L);
		ami.setUserEntity(ue);
		final int amiId = RandomUtils.nextInt();
		ami.setIdAmi(new Long(amiId));
		dao.save(ami);
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dao.supprimer(ami);
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
		Assert.assertEquals(1, listeFavoris.size());
	}

	public void testLoadFullUserData() {
		final UserCriteria user = new UserCriteria();
		user.setIdUser(3L);
		final UserCriteria loadFullUserData = dao.loadFullUserData(user);
		final Set<Integer> idMusiques = loadFullUserData.getIdMusiques();
		final Set<Integer> idProfessions = loadFullUserData.getIdProfessions();
		Assert.assertEquals(3, idMusiques.size());
		Assert.assertEquals(1, idProfessions.size());
	}

	@Transactional
	public void testModifierMusic() {
		final Set<Integer> ids = new HashSet<Integer>();
		// Avant : 1,3,10
		ids.add(4);
		ids.add(5);
		ids.add(6);
		dao.modifierMusics(3L, ids);

	}
}

package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeGenreEnum;
import ch.amaba.model.bo.constants.TypeInteretEnum;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;

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

	/**
	 * Rigourous Test :-)
	 */
	public void testFindUserByCriteria() {
		final UserCriteria criteria = new UserCriteria();
		criteria.setIdSexe(Integer.valueOf(1));
		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(criteria);
		Assert.assertNotNull(findUserBycriteria);
		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getEmail());
		}
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testFindUserAimeReggaeEtCinema() {
		final UserCriteria criteria = new UserCriteria();
		// criteria.setIdSexe(Integer.valueOf(1));
		criteria.addMusique(TypeMusiqueEnum.REGGAE);
		criteria.addInteret(TypeInteretEnum.CINE);
		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(criteria);
		Assert.assertNotNull(findUserBycriteria);
		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getEmail());
		}
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testFindUserVoyageCineSport() {
		final UserCriteria criteria = new UserCriteria();
		// criteria.setIdSexe(Integer.valueOf(1));
		// criteria.addInteret(TypeInteretEnum.SPORT);
		criteria.addInteret(TypeInteretEnum.VOYAGE);
		criteria.addInteret(TypeInteretEnum.CINE);
		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(criteria);
		Assert.assertNotNull(findUserBycriteria);
		final Set<String> found = new HashSet<String>();
		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getEmail());
			found.add(userEntity.getEmail());
		}
		Assert.assertTrue(found.contains("paul@gmail.com"));
		Assert.assertTrue(found.contains("rodolphe.gomes@gmail.com"));
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testAddUserMusique() {
		dao.ajouterMusique(TypeMusiqueEnum.REGGAE);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testChangerStatutMessage() {
		dao.changerMessageStatut(1L, 2L, TypeMessageStatutEnum.LU);
	}

	public void testCreateUserAndProfile() {

		final UserEntity userEntity = new UserEntity();
		userEntity.setEmail("test" + new Random().nextInt() + "@test.com");
		userEntity.setNom("Test nom");
		userEntity.setPrenom("Test prénom");
		userEntity.setIdSexe(1);
		userEntity.setDateNaissance(new Date());
		userEntity.setIdValid(0);
		userEntity.setPassword("123");
		dao.save(userEntity);

		final UserProfileEntity profileEntity = new UserProfileEntity();

		profileEntity.setUserEntity(userEntity);
		profileEntity.setTypeGenreEnum(TypeGenreEnum.HOMO);
		final Set<UserProfileEntity> set = new HashSet<UserProfileEntity>();
		set.add(profileEntity);
		userEntity.setUserProfil(set);

		dao.save(profileEntity);
		dao.flush();
		//
	}
}

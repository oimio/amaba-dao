package test;

import java.util.Date;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeGenreEnum;

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
		final List<UserEntity> findUserBycriteria = dao.findUserBycriteria(criteria);
		Assert.assertNotNull(findUserBycriteria);
		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getEmail());
		}
	}

	public void testCreateProfile() {

		final UserEntity userEntity = new UserEntity();
		userEntity.setEmail("test" + new Random().nextInt() + "@test.com");
		userEntity.setNom("Test nom");
		userEntity.setPrenom("Test prénom");
		userEntity.setIdSexe(1);
		userEntity.setDateNaissance(new Date());
		userEntity.setIdValid(0);
		dao.save(userEntity);

		final UserProfileEntity profileEntity = new UserProfileEntity();

		profileEntity.setIdUser(userEntity.getEntityId());
		profileEntity.setTypeGenreEnum(TypeGenreEnum.HOMO);
		userEntity.setUserProfil(profileEntity);

		dao.save(profileEntity);
		dao.flush();
		//
	}
}

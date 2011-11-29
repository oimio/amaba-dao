package test;

import java.util.Set;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import ch.amaba.dao.model.Audit;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeSportEnum;
import ch.amaba.model.bo.exception.LoginFailedException;

/**
 * Unit test for simple App.
 */
public class AuthentificationDaoTest extends AbstractDaoTest {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AuthentificationDaoTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public AuthentificationDaoTest(final String testName) {
		super(testName);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testAuthentificationAndPopulate() {
		UserCriteria authentification = null;
		try {
			authentification = dao.authentification("hugo@gmail.com", "123");
			final Session s = dao.getSessionFactory().openSession(new Audit());

			final Criteria criteria = s.createCriteria(UserEntity.class);
			criteria.add(Restrictions.idEq(authentification.getIdUser()));
			criteria.createAlias("userAdresses", "adress", CriteriaSpecification.INNER_JOIN);
			criteria.createAlias("userProfil", "profile", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("userSports", "userSports", CriteriaSpecification.LEFT_JOIN);

			criteria.createAlias("userReligions", "userReligions", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("userReligions.religion", "religion", CriteriaSpecification.LEFT_JOIN);
			final UserEntity userEntity = (UserEntity) criteria.uniqueResult();
			Assert.assertNotNull(userEntity.getUserAdresses());
			Assert.assertNotNull(userEntity.getUserSports());
			Assert.assertNotNull(TypeSportEnum.NATATION.equals(userEntity.getUserSports().iterator().next().getIdLink()));
			Assert.assertEquals(2, userEntity.getUserSports().size());
		} catch (final LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertNotNull(authentification);
	}

	public void testAuthentificationAndPopulateHugo() {
		UserCriteria authentification = null;
		try {
			authentification = dao.authentification("hugo@gmail.com", "123");
			final Session s = dao.getSessionFactory().openSession(new Audit());
			dao.loadFullUserData(authentification);
			// Assert.assertNotNull(authentification.getIdCantons());
			Assert.assertNotNull(authentification.getIdSports());
			final Set<Integer> idSports = authentification.getIdSports();

			Assert.assertEquals(true, idSports.contains(TypeSportEnum.FOOT.getId()));
			Assert.assertEquals(true, idSports.contains(TypeSportEnum.NATATION.getId()));
			// Assert.assertEquals(2, userEntity.getUserSports().size());
		} catch (final LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertNotNull(authentification);
	}
}

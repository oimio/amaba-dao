package test;

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
import ch.amaba.model.bo.exception.LoginFailedException;

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
			criteria.createAlias("userSports.sport", "sport", CriteriaSpecification.LEFT_JOIN);

			criteria.createAlias("userReligions", "userReligions", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("userReligions.religion", "religion", CriteriaSpecification.LEFT_JOIN);
			final UserEntity userEntity = (UserEntity) criteria.uniqueResult();
			System.out.println(userEntity.getUserAdresses());
		} catch (final LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertNotNull(authentification);
	}

}

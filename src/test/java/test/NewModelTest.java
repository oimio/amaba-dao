package test;

import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.CriteriaSpecification;

import ch.amaba.dao.model.Audit;
import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.ReligionEntity;
import ch.amaba.dao.model.SportEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserReligionEntity;
import ch.amaba.dao.model.UserSportEntity;

/**
 * Unit test for simple App.
 */
public class NewModelTest extends TestCase {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NewModelTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public NewModelTest(final String testName) {
		super(testName);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		final SessionFactory factory = new AnnotationConfiguration()
		    .addAnnotatedClass(DefaultEntity.class)
		    .addAnnotatedClass(UserEntity.class)
		    .addAnnotatedClass(UserProfileEntity.class)
		    .addAnnotatedClass(UserSportEntity.class)
		    .addAnnotatedClass(SportEntity.class)
		    .addAnnotatedClass(ReligionEntity.class)
		    .addAnnotatedClass(UserReligionEntity.class)

		    .setProperty("hibernate.connection.driver_class",
		        "com.mysql.jdbc.Driver")
		    .setProperty("hibernate.connection.url",
		        "jdbc:mysql://localhost:3306/amaba")
		    .setProperty("hibernate.connection.username", "a2m0a1b2a_root")
		    .setProperty("hibernate.connection.password", "m2e0e1t2all")
		    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
		    .setProperty("hibernate.show_sql", "true")
		    .setProperty("hibernate.hbm2ddl.auto", "update").buildSessionFactory();
		final Session s = factory.openSession(new Audit());
		final Criteria criteria = s.createCriteria(UserEntity.class);
		criteria.createAlias("userProfil", "profile",
		    CriteriaSpecification.INNER_JOIN);
		criteria.createAlias("userSports", "userSports",
		    CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userSports.sport", "sport",
		    CriteriaSpecification.INNER_JOIN);

		criteria.createAlias("userReligions", "userReligions",
		    CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userReligions.religion", "religion",
		    CriteriaSpecification.INNER_JOIN);
		final List<UserEntity> list = criteria.list();
		for (final UserEntity userEntity : list) {
			final UserProfileEntity userProfil = userEntity.getUserProfil();
			System.out.println(userProfil.isEchangiste() + " "
			    + userProfil.getCodeHomoBi());
			final Set<UserSportEntity> userSports = userEntity.getUserSports();
			for (final UserSportEntity userSportEntity : userSports) {
				System.out.println(userSportEntity.getSport().getCodeSport());
			}
			final Set<UserReligionEntity> userReligions = userEntity
			    .getUserReligions();
			for (final UserReligionEntity userReligionEntity : userReligions) {
				System.out.println(userReligionEntity.getReligion().getCodeReligion());
			}
		}
		// System.out.println(list);
	}
}

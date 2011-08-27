package test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import ch.amaba.dao.model.Audit;
import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.InteretEntity;
import ch.amaba.dao.model.ReligionEntity;
import ch.amaba.dao.model.SportEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserInteretEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserReligionEntity;
import ch.amaba.dao.model.UserSportEntity;

/**
 * Unit test for simple App.
 */
public class NewModelAddTest extends TestCase {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NewModelAddTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public NewModelAddTest(final String testName) {
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
		    .addAnnotatedClass(UserInteretEntity.class)
		    .addAnnotatedClass(InteretEntity.class)

		    .setProperty("hibernate.connection.driver_class",
		        "com.mysql.jdbc.Driver")
		    .setProperty("hibernate.connection.url",
		        "jdbc:mysql://localhost:3306/amaba")
		    .setProperty("hibernate.connection.username", "a2m0a1b2a_root")
		    .setProperty("hibernate.connection.password", "m2e0e1t2all")
		    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
		    .setProperty("hibernate.show_sql", "true")
		    .setProperty("hibernate.hbm2ddl.auto", "update").buildSessionFactory();
		final Session session = factory.openSession(new Audit());

		final UserEntity userEntity = (UserEntity) session.load(UserEntity.class,
		    Long.valueOf(1));

		final InteretEntity interetEntity = (InteretEntity) session.load(
		    InteretEntity.class, Long.valueOf(1));

		System.out.println(userEntity);
		System.out.println(interetEntity);

		final Transaction beginTransaction = session.beginTransaction();
		final UserInteretEntity userInteretEntity = new UserInteretEntity();
		userInteretEntity.setUserEntity(userEntity);
		userInteretEntity.setInteret(interetEntity);

		userEntity.addInteret(userInteretEntity);

		session.save(userInteretEntity);
		beginTransaction.commit();
		session.close();
	}
}

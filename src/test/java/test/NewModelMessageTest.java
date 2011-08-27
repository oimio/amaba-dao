package test;

import java.util.Date;

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
import ch.amaba.dao.model.MessageStatutEntity;
import ch.amaba.dao.model.ReligionEntity;
import ch.amaba.dao.model.SportEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserInteretEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.dao.model.UserMessageStatutEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserReligionEntity;
import ch.amaba.dao.model.UserSportEntity;

/**
 * Unit test for simple App.
 */
public class NewModelMessageTest extends TestCase {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NewModelMessageTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public NewModelMessageTest(final String testName) {
		super(testName);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		final SessionFactory factory = new AnnotationConfiguration()

		.addAnnotatedClass(DefaultEntity.class)

		.addAnnotatedClass(UserEntity.class).addAnnotatedClass(UserProfileEntity.class).addAnnotatedClass(UserSportEntity.class)
		    .addAnnotatedClass(SportEntity.class).addAnnotatedClass(ReligionEntity.class).addAnnotatedClass(UserReligionEntity.class)
		    .addAnnotatedClass(UserInteretEntity.class).addAnnotatedClass(InteretEntity.class).addAnnotatedClass(MessageStatutEntity.class)
		    .addAnnotatedClass(UserMessageStatutEntity.class).addAnnotatedClass(UserMessageEntity.class)

		    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
		    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/amaba")

		    .setProperty("hibernate.connection.username", "a2m0a1b2a_root")

		    .setProperty("hibernate.connection.password", "m2e0e1t2all")

		    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")

		    .setProperty("hibernate.show_sql", "true")

		    .setProperty("hibernate.hbm2ddl.auto", "update").buildSessionFactory();
		final Session session = factory.openSession(new Audit());

		final Transaction beginTransaction = session.beginTransaction();
		final UserMessageEntity userMessageEntity = new UserMessageEntity();
		userMessageEntity.setTo(2L);
		userMessageEntity.setFrom(1L);
		userMessageEntity.setMessage("test message..........");
		userMessageEntity.setSujet("nouveau sujet...");

		session.save(userMessageEntity);

		final MessageStatutEntity messageStatutEntity = new MessageStatutEntity();
		// messageStatutEntity.setCodeStatut(TypeMessageStatut.ENVOYE.name());
		messageStatutEntity.setEntityId(3L);

		final UserMessageStatutEntity userMessageStatutEntity = new UserMessageStatutEntity();
		userMessageStatutEntity.setDateStatut(new Date());
		userMessageStatutEntity.setMessageStatutEntity(messageStatutEntity);
		userMessageStatutEntity.setUserMessageEntity(userMessageEntity);
		userMessageStatutEntity.setIdUser(1L);

		final UserMessageStatutEntity userMessageStatutNonLuEntity = new UserMessageStatutEntity();
		userMessageStatutNonLuEntity.setDateStatut(new Date());
		userMessageStatutNonLuEntity.setMessageStatutEntity(MessageStatutEntity.NON_LU);
		userMessageStatutNonLuEntity.setUserMessageEntity(userMessageEntity);
		userMessageStatutNonLuEntity.setIdUser(2L);

		// userMessageEntity.getMessageStatuts().add(userMessageStatutEntity);
		session.save(userMessageStatutEntity);
		session.save(userMessageStatutNonLuEntity);

		beginTransaction.commit();
		session.close();
	}
}

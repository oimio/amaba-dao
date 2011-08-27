package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import ch.amaba.dao.model.Audit;
import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.PropertyCodeEntity;
import ch.amaba.dao.model.PropertyDefinitionEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.SearchUserCriteria;

/**
 * Unit test for simple App.
 */
public class SearchUserTest extends TestCase {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SearchUserTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public SearchUserTest(final String testName) {
		super(testName);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		final SessionFactory factory = new AnnotationConfiguration().addAnnotatedClass(DefaultEntity.class).addAnnotatedClass(UserEntity.class)

		.addAnnotatedClass(PropertyDefinitionEntity.class).addAnnotatedClass(PropertyCodeEntity.class)

		.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
		    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/amaba").setProperty("hibernate.connection.username", "a2m0a1b2a_root")
		    .setProperty("hibernate.connection.password", "m2e0e1t2all").setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
		    .setProperty("hibernate.show_sql", "true").setProperty("hibernate.hbm2ddl.auto", "update").buildSessionFactory();
		final Session s = factory.openSession(new Audit());
		final Criteria criteria = s.createCriteria(UserEntity.class);
		final SearchUserCriteria searchUserCriteria = new SearchUserCriteria();
		searchUserCriteria.addCriteria("1", "1"); // brun
		searchUserCriteria.addCriteria("4", "8"); // basket

		final HashMap<String, Set<String>> searchCriteria = searchUserCriteria.getCriteria();
		final Set<String> keySet = searchCriteria.keySet();

		criteria.createAlias("userPropertyEntities", "ref");

		for (final String key : keySet) {
			criteria.add(Restrictions.eq("ref.entityId", Long.parseLong(key)));
			criteria.add(Restrictions.eq("ref.propertyCode.entityId", Long.parseLong(((HashSet<String>) searchCriteria.get(key)).iterator().next())));
		}

		// detachedCrti.setMaxResults(100);
		final List list = criteria.list();
		System.out.println(list);
	}
}

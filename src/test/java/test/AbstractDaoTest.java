package test;

import junit.framework.TestCase;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import ch.amaba.dao.AmabaDao;

public class AbstractDaoTest extends TestCase {
	protected FileSystemXmlApplicationContext beanFactory;
	protected AmabaDao dao;

	public AbstractDaoTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		beanFactory = new FileSystemXmlApplicationContext("d:/workspaces/amaba-gwt/resources/application-context.xml");
		dao = (AmabaDao) beanFactory.getBean("amabaDao");
	}
}

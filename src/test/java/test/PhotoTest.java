package test;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ch.amaba.dao.AmabaDao;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.UserCriteria;

public class PhotoTest {

	private FileSystemXmlApplicationContext beanFactory;
	private AmabaDao dao;

	@Before
	public void setUp() {
		beanFactory = new FileSystemXmlApplicationContext("d:/workspaces/AmabaGui/resources/application-context.xml");
		dao = (AmabaDao) beanFactory.getBean("amabaDao");
	}

	@Test
	public void aucunePhoto() {
		final UserCriteria userCriteria = new UserCriteria();
		userCriteria.setIdUser(4L);
		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(userCriteria);
		Assert.assertNotNull(findUserBycriteria);
		Assert.assertFalse(findUserBycriteria.isEmpty());
		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getNom());
		}
	}

	@Test
	public void unePhoto() {
		final UserCriteria userCriteria = new UserCriteria();
		// userCriteria.setIdUser(3L);
		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(userCriteria);
		Assert.assertNotNull(findUserBycriteria);
		Assert.assertFalse(findUserBycriteria.isEmpty());
		for (final UserEntity userEntity : findUserBycriteria) {
			// System.out.println(userEntity.getNom());
			System.out.println("email=" + userEntity.getEmail() + " photo=" + userEntity.getPhotoPrincipale());
		}
	}
}

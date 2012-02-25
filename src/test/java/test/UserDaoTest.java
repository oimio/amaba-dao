package test;

import junit.framework.Assert;

import org.junit.Test;

import ch.amaba.dao.model.UserEntity;

public class UserDaoTest extends AbstractDaoTest {

	public UserDaoTest(String testName) {
		super(testName);
	}

	/**
	 * Place dans la corbeille des messages (statut
	 * {@linkTypeMessageStatutEnum.SUPPRIME}).
	 */
	@Test
	public void testMettreAJourPreferences() {
		try {

			final UserEntity userEntity = (UserEntity) dao.get(UserEntity.class, 3L);

			// Expected : 3 messages et 9 nouveaux statuts
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}

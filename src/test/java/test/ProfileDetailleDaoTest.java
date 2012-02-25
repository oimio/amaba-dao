package test;

import junit.framework.Assert;

import org.junit.Test;

import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.UserCriteria;

public class ProfileDetailleDaoTest extends AbstractDaoTest {

	public ProfileDetailleDaoTest(String testName) {
		super(testName);
	}

	/**
	 * Place dans la corbeille des messages (statut
	 * {@linkTypeMessageStatutEnum.SUPPRIME}).
	 */
	@Test
	public void testMettreAJourPreferences() {
		try {

			final UserCriteria profileDetaille = dao.getProfileDetaille(3L);
			final ProfileCriteria profileCriteria = profileDetaille.getProfileCriteria();
			Assert.assertNotNull(profileCriteria);

			// Expected : 3 messages et 9 nouveaux statuts
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}

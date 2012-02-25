package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import ch.amaba.model.bo.PreferenceDTO;
import ch.amaba.model.bo.constants.TypePreferenceEnum;

public class PreferenceDaoTest extends AbstractDaoTest {

	public PreferenceDaoTest(String testName) {
		super(testName);
	}

	/**
	 * Place dans la corbeille des messages (statut
	 * {@linkTypeMessageStatutEnum.SUPPRIME}).
	 */
	@Test
	public void testMettreAJourPreferences() {
		try {
			final PreferenceDTO dto1 = new PreferenceDTO();
			dto1.setTypePreferenceEnum(TypePreferenceEnum.HIDE_EMAIL);
			dto1.setValue("1");
			final PreferenceDTO dto2 = new PreferenceDTO();
			dto2.setTypePreferenceEnum(TypePreferenceEnum.HIDE_NOM);
			dto2.setValue("0");
			final Set<PreferenceDTO> preferenceDTOs = new HashSet<PreferenceDTO>();
			preferenceDTOs.add(dto1);
			preferenceDTOs.add(dto2);
			dao.updatePreference(3L, preferenceDTOs);

			// Expected : 3 messages et 9 nouveaux statuts
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}

package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

public class MessageDaoTest extends AbstractDaoTest {

	public MessageDaoTest(String testName) {
		super(testName);
	}

	@Test
	public void testGetMessagesRecus() {
		final Set<MessageDTO> messagesEnvoyes = dao.getMessages(3L, TypeMessageStatutEnum.RECU);
		Assert.assertNotNull(messagesEnvoyes);
		for (final MessageDTO messageDTO : messagesEnvoyes) {
			System.out.println(messageDTO);
		}
	}

	/**
	 * Envoyer des messages à idUser=3.<br/>
	 * idUser=3 supprime ses emails.
	 * */
	@Test
	public void testSupprimerLotDeMessages() {
		try {
			final Set<UserMessageEntity> userMessageEntities = new HashSet<UserMessageEntity>();
			final UserMessageEntity envoyerMessage1 = dao.envoyerMessage(3L, 1L, "message 1 du lot", "je le cré puis le supprime :)");
			final UserMessageEntity envoyerMessage2 = dao.envoyerMessage(3L, 2L, "message 2 du lot", "je le cré puis le supprime :)");
			final UserMessageEntity envoyerMessage3 = dao.envoyerMessage(3L, 2L, "message 3 du lot", "je le cré puis le supprime :)");
			userMessageEntities.add(envoyerMessage1);
			userMessageEntities.add(envoyerMessage2);
			userMessageEntities.add(envoyerMessage3);
			final Integer supprimerEntities = dao.supprimerEntities(userMessageEntities, 3L);
			Assert.assertEquals(Integer.valueOf(3), supprimerEntities);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test
	public void testGetMessagesSupprimes() {
		UserMessageEntity envoyerMessage;
		try {
			envoyerMessage = dao.envoyerMessage(1L, 3L, "test message effacé", "je le cré puis le supprime :)");
			Assert.assertNotNull(envoyerMessage);
			dao.supprimer(envoyerMessage);
			final Set<MessageDTO> messagesSupprimes = dao.getMessages(1L, TypeMessageStatutEnum.SUPPRIME);
			Assert.assertNotNull(messagesSupprimes);
			boolean found = false;
			for (final MessageDTO messageDTO : messagesSupprimes) {
				System.out.println(messageDTO.getStatut() + " " + messageDTO.getBusinessObjectId() + " " + envoyerMessage.getEntityId());
				found = (DefaultEntity.ENTITY_DELETED_STATE.equals(messageDTO.getStatut()) && messageDTO.getBusinessObjectId().equals(envoyerMessage.getEntityId()));
				break;
			}
			Assert.assertEquals(Boolean.TRUE, Boolean.valueOf(found));
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

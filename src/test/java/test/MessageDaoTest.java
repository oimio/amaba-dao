package test;

import java.util.Set;

import junit.framework.Assert;
import ch.amaba.model.bo.MessageDTO;

public class MessageDaoTest extends AbstractDaoTest {

	public MessageDaoTest(String testName) {
		super(testName);
	}

	public void testGetMessagesEnvoyes() {
		final Set<MessageDTO> messagesEnvoyes = dao.getMessagesEnvoyes(3L);
		Assert.assertNotNull(messagesEnvoyes);
		for (final MessageDTO messageDTO : messagesEnvoyes) {
			System.out.println(messageDTO);
		}
	}

}

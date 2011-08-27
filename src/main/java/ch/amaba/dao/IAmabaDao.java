package ch.amaba.dao;

import java.util.List;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.PropertyDefinitionEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.SearchUserCriteria;

public interface IAmabaDao {

	public <T extends ch.amaba.dao.model.DefaultEntity> T loadById(final Class<T> entityClass, final Long id);

	public DefaultEntity saveOrUpdateEntity(DefaultEntity entity);

	/**
	 * Load all property definition.
	 * 
	 * @return list of StockEntity
	 */
	public List<PropertyDefinitionEntity> loadPropertyDefinition();

	public List<UserEntity> findUserBycriteria(final SearchUserCriteria searchUserCriteria);

	/**
	 * @param to
	 *          - usrIdTo du usr destinataire du message
	 */
	public UserMessageEntity envoyerMessage(Long usrIdTo, String sujet, String message);

	/** Retourne la liste des messages (lu, non lus, envoyés et supprimés) */
	public List<UserMessageEntity> getMessages();

	public void messageLu(Long idMessage);

	public void supprimerMessage(Long idMessage);
}

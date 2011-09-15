package ch.amaba.dao;

import java.util.List;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public interface IAmabaDao {

	public <T extends ch.amaba.dao.model.DefaultEntity> T loadById(final Class<T> entityClass, final Long id);

	public DefaultEntity saveOrUpdateEntity(DefaultEntity entity);

	public List<UserEntity> findUserBycriteria(final UserCriteria searchUserCriteria);

	/**
	 * @param to
	 *          - usrIdTo du usr destinataire du message
	 */
	public UserMessageEntity envoyerMessage(Long usrIdTo, String sujet, String message);

	/** Retourne la liste des messages (lu, non lus, envoyés et supprimés) */
	public List<UserMessageEntity> getMessages();

	public void messageLu(Long idMessage);

	public void supprimerMessage(Long idMessage);

	public void register(final UserCriteria criteria) throws UserAlreadyExistsException;

	public void ajouterAmi(Long idAmi) throws EntityNotFoundException;

	public void blockUser(final Long idUser);
}

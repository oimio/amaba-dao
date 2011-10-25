package ch.amaba.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public interface IAmabaDao {

	public <T extends ch.amaba.dao.model.DefaultEntity> T loadById(final Class<T> entityClass, final Long id);

	public DefaultEntity saveOrUpdateEntity(DefaultEntity entity);

	public Set<UserEntity> findUserBycriteria(final UserCriteria searchUserCriteria);

	/**
	 * @param to
	 *          - usrIdTo du usr destinataire du message
	 */
	public UserMessageEntity envoyerMessage(Long usrIdTo, String sujet, String message);

	/** Retourne la liste des messages (lu, non lus, envoyés et supprimés) */
	public List<UserMessageEntity> getMessages();

	public void supprimerMessage(Long idMessage);

	public void devenirMembre(final UserCriteria criteria) throws UserAlreadyExistsException;

	public void ajouterAmi(Long idAmi) throws EntityNotFoundException;

	public void blockUser(final Long idUser);

	public void ajouterMusique(final TypeMusiqueEnum typeMusiqueEnum);

	void changerMessageStatut(Long idMessage, final Long idUser, TypeMessageStatutEnum typeMessageStatutEnum);

	/**
	 * Méthode d'authentification par email/password.
	 * 
	 * @param email
	 * @param password
	 */
	UserCriteria authentification(final String email, final String password) throws LoginFailedException;

	public Set<CantonDTO> loadCantons();

	public HashMap<String, HashMap<String, String>> loadTraductions(String langue);
}

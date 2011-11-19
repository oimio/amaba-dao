package ch.amaba.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.exception.DuplicateEntityException;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public interface IAmabaDao {

	/**
	 * 
	 * */
	public <T extends ch.amaba.dao.model.DefaultEntity> T loadById(final Class<T> entityClass, final Long id);

	/**
	 * 
	 * */
	public DefaultEntity saveOrUpdateEntity(DefaultEntity entity);

	/**
	 * 
	 * */
	public Set<UserEntity> findUserBycriteria(final UserCriteria searchUserCriteria);

	/**
	 * @param to
	 *          - usrIdTo du usr destinataire du message
	 */
	public UserMessageEntity envoyerMessage(Long usrIdTo, String sujet, String message);

	/** Retourne la liste des messages (lu, non lus, envoyés et supprimés) */
	public List<UserMessageEntity> getMessages();

	/**
	 * 
	 * */
	public void supprimerMessage(Long idMessage);

	/**
	 * 
	 * */
	public void devenirMembre(final UserCriteria criteria) throws UserAlreadyExistsException;

	/**
	 * Ajoute un user dans les favoris du user courant.
	 * 
	 * @param idUsr
	 *          - le user courant
	 * @param idAmi
	 *          - l'ami à ajouter
	 * @throws DuplicateEntityException
	 *           - la paire usr / ami doit être unique (mais possible inversée)
	 */
	public void ajouterFavori(Long idUsr, Long idAmi) throws Exception;

	/**
	 * 
	 * */
	public void blockUser(final Long idUser);

	/**
	 * 
	 * */
	public void ajouterMusique(final TypeMusiqueEnum typeMusiqueEnum);

	/**
	 * 
	 * */
	void changerMessageStatut(Long idMessage, final Long idUser, TypeMessageStatutEnum typeMessageStatutEnum);

	/**
	 * Méthode d'authentification par email/password.
	 * 
	 * @param email
	 * @param password
	 */
	public UserCriteria authentification(final String email, final String password) throws LoginFailedException;

	/**
	 * 
	 * */
	public Set<CantonDTO> loadCantons();

	/**
	 * 
	 * */
	public Map<String, Map<String, String>> loadTraductions(String langue);

	/** Chargement de l'ensemble des données d'un user. */
	public UserCriteria loadFullUserData(final UserCriteria userCriteria);

	/**
	 * Flag à supprimé. Aucune entité n'est supprimée physiquement.
	 * */
	public <T extends DefaultEntity> void supprimer(final T entity) throws EntityNotFoundException;

	/**
	 * 
	 * @param idUser
	 *          - le user courant.
	 */
	public Set<UserCriteria> listeFavoris(final Long idUser);
}

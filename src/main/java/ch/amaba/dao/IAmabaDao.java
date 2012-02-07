package ch.amaba.dao;

import java.util.Map;
import java.util.Set;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.exception.CompteBloqueException;
import ch.amaba.model.bo.exception.CompteNonValideException;
import ch.amaba.model.bo.exception.DuplicateEntityException;
import ch.amaba.model.bo.exception.EmailNonValideException;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public interface IAmabaDao extends IDao {

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
	 * @param idDestinataire
	 *          - le destinataire
	 * @param from
	 *          - l'expéditeur
	 * @param sujet
	 * @param message
	 */
	public UserMessageEntity envoyerMessage(Long idDestinataire, Long from, String sujet, String message) throws Exception;

	/**
	 * Retourne la liste des messages <b>envoyés</b>.
	 * 
	 * @param idUser
	 * @param typeMessageStatutEnum
	 * */
	public Set<MessageDTO> getMessages(final Long idUser, TypeMessageStatutEnum typeMessageStatutEnum);

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
	public void ajouterFavori(Long idUsr, Long idAmi) throws DuplicateEntityException;

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
	void changerMessagesStatut(Set<Long> idMessage, final Long idUser, TypeMessageStatutEnum typeMessageStatutEnum);

	/**
	 * Méthode d'authentification par email/password.
	 * 
	 * @param email
	 * @param password
	 */
	public UserCriteria authentification(final String email, final String password) throws LoginFailedException, EmailNonValideException, CompteBloqueException,
	    CompteNonValideException;

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

	/** Sauvegarde les noms (attribut fileName) des photos */
	public void savePhotos(Long idUser, String[] fileNames);

	/** Retourne dans un {@link Set} les photos d'un user. */
	public Set<PhotoDTO> loadPhotosByUser(final Long idUser);

	/**
	 * Flag une photo comme principale (photo du profile).
	 * 
	 * @return dans un {@link Set} les photos d'un user.
	 */
	public Long flagPhotoPrincipale(Long idUser, Long idPhoto);

	public void saveUserConnection(final Long idUser, final String ip);

	public <T extends DefaultEntity> Integer supprimerEntities(Set<T> entities, Long idUser) throws EntityNotFoundException;
}

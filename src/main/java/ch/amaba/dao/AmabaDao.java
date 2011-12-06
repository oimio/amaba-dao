package ch.amaba.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import ch.amaba.dao.model.CantonEntity;
import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.TraductionEntity;
import ch.amaba.dao.model.UserAdressEntity;
import ch.amaba.dao.model.UserAmiEntity;
import ch.amaba.dao.model.UserConnectionEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserInteretEntity;
import ch.amaba.dao.model.UserLinkEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.dao.model.UserMessageStatutEntity;
import ch.amaba.dao.model.UserMusiqueEntity;
import ch.amaba.dao.model.UserPhotoEntity;
import ch.amaba.dao.model.UserPreferenceEntity;
import ch.amaba.dao.model.UserProfessionEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserReligionEntity;
import ch.amaba.dao.model.UserSportEntity;
import ch.amaba.dao.model.UserStatutEntity;
import ch.amaba.dao.utils.DateUtils;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.TraductionDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeUserStatutEnum;
import ch.amaba.model.bo.exception.DuplicateEntityException;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public class AmabaDao extends HibernateTemplate implements IAmabaDao {

	Logger LOG = LoggerFactory.getLogger(AmabaDao.class);

	private static final SimpleExpression ENTITY_ACTIVE_STATE = Restrictions.eq("statut", DefaultEntity.ENTITY_ACTIVE_STATE);

	public AmabaDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DefaultEntity> T loadById(final Class<T> entityClass, final Long id) {
		final DefaultEntity load = (DefaultEntity) getSession().load(entityClass.getClass(), id);
		return (T) load;
	}

	public <T extends DefaultEntity> List<DefaultEntity> loadByIds(final Class<T> entityClass, final List<Long> ids) {
		@SuppressWarnings("unchecked")
		final List<DefaultEntity> list = getSession().createCriteria(entityClass).add(Restrictions.in("entityId", ids)).list();
		return list;
	}

	@Override
	public DefaultEntity saveOrUpdateEntity(final DefaultEntity entity) {
		getSession().saveOrUpdate(entity);
		// Un flush session pour recuperer la nouvelle version de l'entity
		// (propriete version =version + 1)
		// Sans ce flush session on a l'erreur Row was updated or deleted by another
		// transaction (or unsaved-value mapping
		// was incorrect)
		// si on essaye de sauvegarder le meme object 2 fois.
		getSession().flush();
		return entity;
	}

	@Override
	public UserMessageEntity envoyerMessage(Long usrIdTo, String sujet, String message) {
		final Transaction beginTransaction = getSession().beginTransaction();

		// 1 - sauver le message
		UserMessageEntity userMessageEntity = null;
		try {
			userMessageEntity = new UserMessageEntity();
			userMessageEntity.setTo(usrIdTo);
			userMessageEntity.setFrom(1L);
			userMessageEntity.setSujet(sujet);
			userMessageEntity.setMessage(message);
			getSession().save(userMessageEntity);

			// 2 - sauver les statuts du message (envoye pour celui qui envoit et
			// nouveau pour le destinataire)
			final UserMessageStatutEntity userMessageStatutEntity = new UserMessageStatutEntity();
			userMessageStatutEntity.setDateStatut(new Date());
			userMessageStatutEntity.setTypeMessageStatutEnum(TypeMessageStatutEnum.ENVOYE);
			userMessageStatutEntity.setIdMessage(userMessageEntity.getEntityId());
			userMessageStatutEntity.setIdUser(1L);

			final UserMessageStatutEntity userMessageStatutNonLuEntity = new UserMessageStatutEntity();
			userMessageStatutNonLuEntity.setDateStatut(new Date());
			userMessageStatutNonLuEntity.setTypeMessageStatutEnum(TypeMessageStatutEnum.NON_LU);
			userMessageStatutNonLuEntity.setIdMessage(userMessageEntity.getEntityId());
			userMessageStatutNonLuEntity.setIdUser(usrIdTo);

			getSession().save(userMessageStatutEntity);
			getSession().save(userMessageStatutNonLuEntity);
			beginTransaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
		} finally {
			getSession().close();
		}
		return userMessageEntity;
	}

	@Override
	public Set<MessageDTO> getMessagesEnvoyes(final Long idUser) {
		final Set<MessageDTO> messages = new HashSet<MessageDTO>();
		final StringBuffer buf = new StringBuffer();
		buf.append(" select ms.idmessage as idmessage, txSujet as sujet,txMessage, ms.dtstatut as dt,ms.idmessagestatut as statut, m.idusrto as idusrto"
		    + ", u.txusrnom as nom, u.txusrprenom as prenom");
		buf.append(" from usrMessage m");
		buf.append(" inner join usrmessagestatut ms on ms.idmessage=m.idmessage");
		buf.append(" inner join usr u on u.idusr=m.idusrto");
		buf.append(" where idusrfrom=" + idUser);

		final List<Object[]> found = getSession().createSQLQuery(buf.toString())

		.addScalar("idMessage", new LongType()).addScalar("sujet", new StringType())

		.addScalar("txMessage", new StringType()).addScalar("dt", new DateType()).addScalar("statut", new IntegerType()).

		addScalar("idusrto", new IntegerType()).addScalar("nom", new StringType()).addScalar("prenom", new StringType())

		.list();
		for (final Object[] objects : found) {
			final MessageDTO dto = new MessageDTO();
			dto.setBusinessObjectId((Long) objects[0]);
			dto.setSujet((String) objects[1]);
			dto.setMessage((String) objects[2]);
			dto.setDate((Date) objects[3]);
			dto.setTypeMessageStatutEnum(TypeMessageStatutEnum.getEnumById((Integer) objects[4]));
			dto.setIdCorrespondant((Integer) objects[5]);
			dto.setNomCorrespondant((String) objects[6]);
			dto.setPrenomCorrespondant((String) objects[7]);
			messages.add(dto);
		}
		return messages;
	}

	@Override
	@Transactional
	public void changerMessageStatut(final Long idMessage, final Long idUser, final TypeMessageStatutEnum typeMessageStatutEnum) {
		final UserMessageStatutEntity newStatut = new UserMessageStatutEntity();
		newStatut.setIdMessage(idMessage);
		newStatut.setIdUser(idUser);
		newStatut.setDateStatut(new Date());
		// Nouveau statut
		newStatut.setTypeMessageStatutEnum(typeMessageStatutEnum);
		getSession().save(newStatut);
	}

	@Override
	public void supprimerMessage(Long idMessage) {
		final UserMessageEntity userMessageEntity = (UserMessageEntity) getSession().load(UserMessageEntity.class, idMessage);
		userMessageEntity.setStatut("D");

	}

	public List<DefaultEntity> loadByUserId(final DefaultEntity entity) {
		return getSession().createCriteria(entity.getClass()).add(Restrictions.eq("idUsr", entity.getEntityId())).list();
	}

	public List<DefaultEntity> loadUserPreferences(final Long entityId) {
		return getSession().createCriteria(UserPreferenceEntity.class).add(Restrictions.eq("idUsr", entityId)).list();
	}

	@Override
	public Set<UserEntity> findUserBycriteria(UserCriteria criteria) {
		String sql = "SELECT u.idUsr AS ID FROM usr u WHERE 1=1 AND ISVALID=" + TypeUserStatutEnum.VALID.getStatut();
		sql += " AND u.STATUT='" + DefaultEntity.ENTITY_ACTIVE_STATE + "'";
		if ((criteria.getIdCantons() != null) && (criteria.getIdCantons().size() > 0)) {
			final Set<Integer> idCantons = criteria.getIdCantons();
			sql += " and exists(";
			sql += " select 1 from usradress ad";
			// sql += " inner join ville v on (v.idville=ad.idville)";
			sql += " inner join canton can on can.idCanton=ad.idCanton and" +
			// "can.idcanton=v.idville and " +
			    " can.idcanton in (" + asString(idCantons) + ")";
			sql += " where ad.idusr = u.idusr";
			sql += " )";
		}
		if (criteria.getIdSports() != null) {
			final Set<Integer> idSports = criteria.getIdSports();
			for (final Integer integer : idSports) {
				sql += " and exists(";
				sql += " select 1 from usrsport usp" + integer;
				sql += " where usp" + integer + ".idSport=" + integer + " and usp" + integer + ".idusr = u.idusr";
				sql += " )";
			}
		}
		if (criteria.getIdInterets() != null) {
			final Set<Integer> idInterets = criteria.getIdInterets();
			for (final Integer id : idInterets) {
				sql += " and exists(";
				sql += " select 1 from usrinteret ui";
				sql += " where ui.idusr = u.idusr and ui.idInteret=" + id;
				sql += " )";
			}
		}
		if (criteria.getIdMusiques() != null) {
			final Set<Integer> idMusiques = criteria.getIdMusiques();
			for (final Integer integer : idMusiques) {
				sql += " and exists(";
				sql += " select 1 from usrmusique ui" + integer;
				sql += " where ui" + integer + ".idMusique=" + integer + " and ui" + integer + ".idusr = u.idusr";
				sql += " )";
			}
		}
		if (criteria.getIdReligions() != null) {
			final Set<Integer> ids = criteria.getIdReligions();
			for (final Integer integer : ids) {
				sql += " AND EXISTS(";
				sql += " SELECT 1 FROM USRRELIGION UR" + integer;
				sql += " WHERE UR" + integer + ".idReligion=" + integer + " and ur" + integer + ".idusr = u.idusr";
				sql += " )";
			}
		}
		if (criteria.getIdProfessions() != null) {
			final Set<Integer> ids = criteria.getIdProfessions();
			for (final Integer integer : ids) {
				sql += " AND EXISTS(";
				sql += " SELECT 1 FROM USRPROFESSION UP" + integer;
				sql += " WHERE UP" + integer + ".IDRELIGION=" + integer + " AND UP" + integer + ".IDUSR = u.idusr";
				sql += " )";
			}
		}
		if (criteria.getProfileCriteria() != null) {
			final ProfileCriteria profileCriteria = criteria.getProfileCriteria();
			sql += " and exists(";
			sql += " select 1 from usrprofile pr";
			sql += " where pr.idusr = u.idusr";
			if (profileCriteria.getMarie() != null) {
				sql += " and pr.nbMarie=" + profileCriteria.getMarie();
			}
			if (profileCriteria.getDivorce() != null) {
				sql += " and pr.nbDivorce=" + profileCriteria.getDivorce();
			}
			if (profileCriteria.getVeuf() != null) {
				sql += " and pr.nbVeuf=" + profileCriteria.getVeuf();
			}
			if (profileCriteria.getNombreEnfant() != null) {
				sql += " and pr.nbEnfant=" + profileCriteria.getNombreEnfant();
			}
			if (profileCriteria.getGenre() != null) {
				sql += " and pr.idGenre=" + profileCriteria.getGenre();
			}
			if (profileCriteria.getSerieux() != null) {
				sql += " and pr.nbSerieux=" + profileCriteria.getSerieux();
			}
			sql += " )";
		}
		if (criteria.getIdSexe() != null) {
			sql += " and u.idSexe=" + criteria.getIdSexe();
		}
		if (criteria.getAgeMin() != null) {
			sql += " and (YEAR(CURDATE())-YEAR(dtUsrNaissance)) -(RIGHT(CURDATE(),5)<RIGHT(dtUsrNaissance,5)) >= " + criteria.getAgeMin();
		}
		if (criteria.getAgeMax() != null) {
			sql += " and (YEAR(CURDATE())-YEAR(dtUsrNaissance)) -(RIGHT(CURDATE(),5)<RIGHT(dtUsrNaissance,5)) <= " + criteria.getAgeMax();
		}

		final List<Long> ids = getSession().createSQLQuery(sql).addScalar("ID", new LongType()).list();
		final Set<UserEntity> arrayList = new HashSet<UserEntity>();
		if (!ids.isEmpty()) {
			final List<DefaultEntity> loadByIds = loadByIds(UserEntity.class, ids);
			for (final DefaultEntity defaultEntity : loadByIds) {
				arrayList.add((UserEntity) defaultEntity);
			}
		}
		return arrayList;
	}

	private String asString(final Set<Integer> set) {
		if (set == null) {
			throw new IllegalStateException("Le paramètre Set<Integer> est null.");
		}
		String toReturn = "";
		for (final Integer integer : set) {
			toReturn += (integer.toString() + ",");
		}
		toReturn = toReturn.substring(0, toReturn.length() - 1);
		return toReturn;
	}

	/**
	 * Service de création d'un nouveau membre.
	 * 
	 * @param criteria
	 *          , les data du membre à créer.
	 * */
	@Override
	@Transactional
	public void devenirMembre(final UserCriteria criteria) throws UserAlreadyExistsException {

		Transaction transaction = null;
		try {
			final UserEntity alreadyExists = (UserEntity) getSession().createCriteria(UserEntity.class).add(Restrictions.eq("email", criteria.getEmail()))
			    .uniqueResult();
			if (alreadyExists != null) {
				throw new UserAlreadyExistsException();
			}
			transaction = getSession().getTransaction();
			transaction.begin();
			final UserEntity userEntity = new UserEntity();
			userEntity.setIdValid(0); // raccourci pour savoir si un user est valide
			userEntity.setEmail(criteria.getEmail());
			userEntity.setNom(criteria.getNom());
			userEntity.setPrenom(criteria.getPrenom());
			userEntity.setDateNaissance(criteria.getDateNaissance());
			userEntity.setIdSexe(criteria.getIdSexe());
			userEntity.setPassword(criteria.getPassword());
			// RESTE A FAIRE LE PASSWORD
			// RESTE A FAIRE LE PASSWORD
			// RESTE A FAIRE LE PASSWORD

			if (criteria.getProfileCriteria() != null) {
				final ProfileCriteria profileCriteria = criteria.getProfileCriteria();
				final UserProfileEntity userProfileEntity = new UserProfileEntity();
				if (profileCriteria.getGenre() != null) {
					userProfileEntity.setIdGenre(profileCriteria.getGenre());
				}
				if (profileCriteria.getNombreEnfant() != null) {
					userProfileEntity.setNombreEnfant(profileCriteria.getNombreEnfant());
				}
				if (profileCriteria.getDivorce() != null) {
					userProfileEntity.setDivorce(profileCriteria.getDivorce());
				}
				if (profileCriteria.getMarie() != null) {
					userProfileEntity.setMarie(profileCriteria.getMarie());
				}
				if (profileCriteria.getVeuf() != null) {
					userProfileEntity.setVeuf(profileCriteria.getVeuf());
				}
				if (profileCriteria.getSerieux() != null) {
					userProfileEntity.setSerieux(profileCriteria.getSerieux());
				}
			}
			getSession().save(userEntity);

			// On sauve le canton
			final Set<Integer> idCantons = criteria.getIdCantons();
			for (final Integer idCanton : idCantons) {
				final UserAdressEntity userAdressEntity = new UserAdressEntity();
				userAdressEntity.setIdUser(userEntity.getEntityId());
				userAdressEntity.setIdCanton(idCanton);
				getSession().save(userAdressEntity);
			}

			// On sauve le statut : TypeUserStatutEnum.NEW
			final UserStatutEntity userStatut = new UserStatutEntity();
			userStatut.setIdUsr(userEntity.getEntityId());
			userStatut.setDebut(new Date());
			userStatut.setFin(DateUtils.getInfiniteDate());
			userStatut.setIdStatut(TypeUserStatutEnum.NEW.getStatut());
			getSession().save(userStatut);

			final UserConnectionEntity userConnectionEntity = new UserConnectionEntity();
			userConnectionEntity.setIdUsr(userEntity.getEntityId());
			userConnectionEntity.setIp(criteria.getIp());
			userConnectionEntity.setDateConnection(new Date());
			getSession().save(userConnectionEntity);
			// FInalement on commit....
			transaction.commit();

		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			// throw e;
		}
	}

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
	@Override
	@Transactional
	public void ajouterFavori(final Long idUsr, final Long idAmi) throws DuplicateEntityException {
		Transaction transaction = null;
		try {
			transaction = getSession().beginTransaction();

			final UserAmiEntity userAmiEntity = new UserAmiEntity();
			final UserEntity u = new UserEntity();
			u.setEntityId(idUsr);
			userAmiEntity.setUserEntity(u);
			userAmiEntity.setIdAmi(idAmi);
			getSession().save(userAmiEntity);
			transaction.commit();
		} catch (final ConstraintViolationException e) {
			// La paire usr / ami existe deja
			// Attention on doit pouvoir faire la paire inverse !
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DuplicateEntityException("La paire idUsr[" + idUsr + "] idAmi[" + idAmi + "] existe deja.");
		}
	}

	@Override
	public void blockUser(Long idUser) {
		// TODO Auto-generated method stub

	}

	/**
	 * Ajoute une preference musique au user de la session courante.
	 * 
	 * @param typeMusiqueEnum
	 * */
	@Override
	@Transactional
	public void ajouterMusique(final TypeMusiqueEnum typeMusiqueEnum) {
		final UserEntity userEntity = new UserEntity();
		userEntity.setEntityId(1L);
		final UserMusiqueEntity userMusiqueEntity = new UserMusiqueEntity();
		userMusiqueEntity.setIdLink(typeMusiqueEnum.getId());
		userMusiqueEntity.setIdUser(userEntity.getEntityId());
		getSession().save(userMusiqueEntity);
	}

	@Override
	public UserCriteria authentification(String email, String password) throws LoginFailedException {
		final UserEntity userEntity = (UserEntity) getSession().createCriteria(UserEntity.class).add(Restrictions.eq("email", email))
		    .add(Restrictions.eq("password", password)).add(Restrictions.eq("idValid", Integer.valueOf(TypeUserStatutEnum.VALID.getStatut()))).uniqueResult();
		UserCriteria userCriteria = null;
		if (userEntity == null) {
			throw new LoginFailedException();
		} else {
			userCriteria = new UserCriteria();
			userCriteria.setIdUser(userEntity.getEntityId());
			userCriteria.setNom(userEntity.getNom());
			userCriteria.setPrenom(userEntity.getPrenom());
			userCriteria.setDateNaissance(userEntity.getDateNaissance());
			userCriteria.setIdSexe(userEntity.getIdSexe());
		}
		return userCriteria;
	}

	@Override
	public Set<CantonDTO> loadCantons() {
		final Set<CantonDTO> cantons = new HashSet<CantonDTO>();
		final List<CantonEntity> list = getSession().createCriteria(CantonEntity.class).addOrder(Order.asc("codeCanton")).list();
		for (final CantonEntity cantonEntity : list) {
			final CantonDTO dto = new CantonDTO();
			dto.setBusinessObjectId(cantonEntity.getEntityId());
			dto.setCodeCanton(cantonEntity.getCodeCanton());
			cantons.add(dto);
		}
		return cantons;
	}

	@Override
	public Map<String, Map<String, String>> loadTraductions(String langue) {
		final Map<String, Map<String, String>> map = new TreeMap<String, Map<String, String>>();
		final List<TraductionEntity> list = getSession().createCriteria(TraductionEntity.class).add(Restrictions.eq("langue", langue))
		    .addOrder(Order.asc("traduction")).list();
		for (final TraductionEntity traductionEntity : list) {
			final TraductionDTO dto = new TraductionDTO();
			dto.setBusinessObjectId(traductionEntity.getEntityId());
			dto.setCodeType(traductionEntity.getType());
			dto.setCodeCle(traductionEntity.getCle());
			dto.setTraduction(traductionEntity.getTraduction());
			Map<String, String> traductions;
			if (map.get(traductionEntity.getType()) == null) {
				traductions = new TreeMap<String, String>();
				traductions.put(traductionEntity.getCle(), traductionEntity.getTraduction());
				map.put(traductionEntity.getType(), traductions);
			} else {
				traductions = map.get(traductionEntity.getType());
				traductions.put(traductionEntity.getCle(), traductionEntity.getTraduction());
			}
		}
		return map;
	}

	/** Load l'ensemble des données d'un user. */
	@Override
	public UserCriteria loadFullUserData(final UserCriteria userCriteria) {

		final Criteria criteria = getSession().createCriteria(UserEntity.class);
		// Filtre principal, idUser
		criteria.add(Restrictions.idEq(userCriteria.getIdUser()));
		// Chargement des adresses, profil, interet, sport, etc...
		criteria.createAlias("userAdresses", "adress", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias("userProfil", "profile", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userInterets", "interets", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userMusics", "musics", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userProfessions", "professions", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("userReligions", "religions", CriteriaSpecification.LEFT_JOIN);
		// .add(Restrictions.eq("religions.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		criteria.createAlias("userSports", "sports", CriteriaSpecification.LEFT_JOIN);
		// criteria.add(Restrictions.eq("musics.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		// criteria.add(Restrictions.eq("interets.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		// criteria.add(Restrictions.eq("sports.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		// criteria.add(Restrictions.eq("religions.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		// criteria.add(Restrictions.eq("professions.statut",
		// DefaultEntity.ENTITY_ACTIVE_STATE));
		final UserEntity userEntity = (UserEntity) criteria.uniqueResult();
		if (userEntity != null) {
			processSetProperties(userCriteria, userEntity);
			final Set<UserAdressEntity> userAdresses = userEntity.getUserAdresses();
			for (final UserAdressEntity userAdressEntity : userAdresses) {
				userCriteria.addCanton(userAdressEntity.getIdCanton());
			}
		}

		return userCriteria;
	}

	/**
	 * From to client.
	 * 
	 * @TOTO Utiliser les generics
	 */
	private void processSetProperties(final UserCriteria userCriteria, UserEntity userEntity) {
		// On purge les properties existantes
		userCriteria.clearUserProperties();
		for (final UserInteretEntity entity : userEntity.getUserInterets()) {
			userCriteria.addInteret(entity.getIdLink());
		}
		for (final UserMusiqueEntity entity : userEntity.getUserMusics()) {
			userCriteria.addMusique(entity.getIdLink());
		}
		for (final UserProfessionEntity entity : userEntity.getUserProfessions()) {
			userCriteria.addProfession(entity.getIdLink());
		}
		for (final UserReligionEntity entity : userEntity.getUserReligions()) {
			userCriteria.addReligion(entity.getIdLink());
		}
		for (final UserSportEntity entity : userEntity.getUserSports()) {
			userCriteria.addSport(entity.getIdLink());
		}
	}

	/**
	 * <b><font color=red>AUCUNE ENTITE N EST SUPPRIMEE PHYSIQUEMENT !</font><b><br/>
	 * 
	 * Flag as deleted une entité (Mets le champs STATUT a 'D').
	 * */
	@Override
	@Transactional
	public <T extends DefaultEntity> void supprimer(T entity) throws EntityNotFoundException {
		try {
			final DefaultEntity object = (DefaultEntity) get(entity.getClass(), entity.getEntityId());
			object.flagAsDeleted();
			saveOrUpdate(object);
		} catch (final Exception e) {
			throw new EntityNotFoundException("Echec de la suppression de l'entité id=" + entity.getEntityId());
		}
	}

	@Override
	public Set<UserCriteria> listeFavoris(Long idUser) {
		final HashSet<UserCriteria> amis = new HashSet<UserCriteria>();
		String sql = "select  u.idusr as ID,u.txusrprenom as PRE,u.dtUsrNaissance as DT, ad.idcanton as IDCANTON from usrami a ";
		sql += "inner join usr u on u.idUsr=a.idAmi ";
		sql += "inner join usradress ad on ad.idusr=u.idusr ";
		sql += "where a.idusr=" + idUser + " and a.statut='A' and u.statut='A'";

		final List<Object[]> found = getSession().createSQLQuery(sql)

		.addScalar("ID", new LongType())

		.addScalar("PRE", new StringType())

		.addScalar("DT", new DateType())

		.addScalar("IDCANTON", new IntegerType())

		.list();
		for (final Object[] objects : found) {
			final UserCriteria userCriteria = new UserCriteria();
			userCriteria.setIdUser((Long) objects[0]);
			userCriteria.setPrenom((String) objects[1]);
			userCriteria.setDateNaissance((Date) objects[2]);
			userCriteria.addCanton((Integer) objects[3]);
			amis.add(userCriteria);
		}
		return amis;
	}

	/**
	 * Modifie les settings Music d'un user. <br/>
	 * - Les settings nouveaux sont créés <br/>
	 * - Les settings existant sont supprimés
	 * 
	 * @param les
	 *          nouveaux id musics
	 * */
	@Transactional
	public void modifierMusics(Long idUser, Set<Integer> ids) {
		if ((ids != null) && !ids.isEmpty()) {
			final Session session = getSession();
			final Transaction transaction = session.beginTransaction();
			try {
				final Criteria criteria = session.createCriteria(UserMusiqueEntity.class);
				criteria.add(Restrictions.eq("idUser", idUser));
				final List<UserMusiqueEntity> musics = criteria.list();
				for (final UserMusiqueEntity userMusiqueEntity : musics) {
					session.delete(userMusiqueEntity);
				}
				for (final Integer idMusic : ids) {
					final UserMusiqueEntity userMusiqueEntity = new UserMusiqueEntity();
					userMusiqueEntity.setIdLink(idMusic);
					final UserEntity ue = new UserEntity();
					ue.setEntityId(idUser);
					userMusiqueEntity.setIdUser(idUser);
					session.save(userMusiqueEntity);
				}
				transaction.commit();
			} catch (final Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}
		}
	}

	/**
	 * Modifie les settings Music d'un user. <br/>
	 * - Les settings nouveaux sont créés <br/>
	 * - Les settings existant sont supprimés
	 * 
	 * @param les
	 *          nouveaux id musics
	 * */
	@Transactional
	public <T extends UserLinkEntity> void modifierSettingsGeneric(Long idUser, Set<Integer> ids, Class<T> classSetting) {
		if ((ids != null) && !ids.isEmpty()) {
			final Session session = getSession();
			final Transaction transaction = session.beginTransaction();
			try {
				final Criteria criteria = session.createCriteria(classSetting);
				criteria.add(Restrictions.eq("userEntity.entityId", idUser));
				final List<T> musics = criteria.list();
				for (final T userMusiqueEntity : musics) {
					session.delete(userMusiqueEntity);
				}
				for (final Integer idMusic : ids) {
					final T userMusiqueEntity = classSetting.newInstance();
					userMusiqueEntity.setIdLink(idMusic);
					final UserEntity ue = new UserEntity();
					ue.setEntityId(idUser);
					userMusiqueEntity.setIdUser(idUser);
					session.save(userMusiqueEntity);
				}
				transaction.commit();
			} catch (final Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}
		}
	}

	/**
	 * Sauvegarde le nom (fileName) des photos.
	 * 
	 **/
	@Transactional
	public void savePhotos(Long idUser, String[] names) {
		for (final String fileName : names) {
			final UserPhotoEntity photo = new UserPhotoEntity();
			photo.setIdUser(idUser);
			photo.setFileName(fileName);
			getSession().save(photo);
		}
	}

	/** Retourne la liste des photos */
	@SuppressWarnings("unchecked")
	public Set<PhotoDTO> loadPhotosByUser(final Long idUser) {
		final Set<PhotoDTO> set = new HashSet<PhotoDTO>();
		final List<UserPhotoEntity> list = getSession().createCriteria(UserPhotoEntity.class).add(AmabaDao.ENTITY_ACTIVE_STATE).list();
		for (final UserPhotoEntity userPhotoEntity : list) {
			final PhotoDTO photo = new PhotoDTO();
			photo.setBusinessObjectId(userPhotoEntity.getEntityId());
			photo.setFileName(userPhotoEntity.getFileName());
			photo.setIdUser(idUser);
			photo.setPrincipale(userPhotoEntity.isPrincipale());
			set.add(photo);
		}
		return set;
	}
}
package ch.amaba.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
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
import org.hibernate.type.CalendarType;
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
import ch.amaba.dao.model.UserPhysiqueEntity;
import ch.amaba.dao.model.UserPreferenceEntity;
import ch.amaba.dao.model.UserProfessionEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserReligionEntity;
import ch.amaba.dao.model.UserSportEntity;
import ch.amaba.dao.model.UserStatutEntity;
import ch.amaba.dao.utils.DateUtils;
import ch.amaba.model.bo.AmiDTO;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.CoquinCriteria;
import ch.amaba.model.bo.MessageDTO;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.PhysiqueCriteria;
import ch.amaba.model.bo.PreferenceDTO;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.TraductionDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeUserStatutEnum;
import ch.amaba.model.bo.exception.CompteBloqueException;
import ch.amaba.model.bo.exception.CompteNonValideException;
import ch.amaba.model.bo.exception.DuplicateEntityException;
import ch.amaba.model.bo.exception.EmailNonValideException;
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
	public UserMessageEntity envoyerMessage(final Long idDestinataire, final Long from, final String sujet, final String message) throws Exception {
		final Transaction beginTransaction = getSession().beginTransaction();

		// 1 - sauver le message
		UserMessageEntity userMessageEntity = null;
		try {
			userMessageEntity = new UserMessageEntity();
			userMessageEntity.setTo(idDestinataire);
			userMessageEntity.setFrom(from);
			userMessageEntity.setSujet(sujet);
			userMessageEntity.setMessage(message);
			getSession().save(userMessageEntity);

			// 2 - sauver les statuts du message (envoye pour celui qui envoit et
			// nouveau pour le destinataire)
			final UserMessageStatutEntity userMessageStatutEntity = new UserMessageStatutEntity();
			userMessageStatutEntity.setDateStatut(new Date());
			userMessageStatutEntity.setTypeMessageStatutEnum(TypeMessageStatutEnum.ENVOYE);
			userMessageStatutEntity.setIdMessage(userMessageEntity.getEntityId());
			userMessageStatutEntity.setIdUser(from);

			final UserMessageStatutEntity userMessageStatutNonLuEntity = new UserMessageStatutEntity();
			userMessageStatutNonLuEntity.setDateStatut(new Date());
			userMessageStatutNonLuEntity.setTypeMessageStatutEnum(TypeMessageStatutEnum.NON_LU);
			userMessageStatutNonLuEntity.setIdMessage(userMessageEntity.getEntityId());
			userMessageStatutNonLuEntity.setIdUser(idDestinataire);

			getSession().save(userMessageStatutEntity);
			getSession().save(userMessageStatutNonLuEntity);
			beginTransaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
			throw e;
		} finally {
			getSession().close();
		}
		return userMessageEntity;
	}

	@Override
	public Set<MessageDTO> getMessages(final Long idUser, TypeMessageStatutEnum typeMessageStatutEnum) {
		final Set<MessageDTO> messages = new LinkedHashSet<MessageDTO>();
		final StringBuffer buf = new StringBuffer();
		buf.append(" SELECT MS.IDMESSAGE AS IDMESSAGE, TXSUJET AS SUJET,TXMESSAGE, " + "M.DTE_CRE AS DT, MS.IDMESSAGESTATUT AS STATUT, M.IDUSRTO AS IDUSRTO"
		    + ", U.TXUSRNOM AS NOM, U.TXUSRPRENOM AS PRENOM, M.STATUT AS MSTATUT");
		buf.append(" FROM USRMESSAGE M");
		buf.append(" INNER JOIN USRMESSAGESTATUT MS ON MS.IDMESSAGE=M.IDMESSAGE");

		if (TypeMessageStatutEnum.ENVOYE.equals(typeMessageStatutEnum)) {
			buf.append(" INNER JOIN USR U ON U.IDUSR=M.IDUSRTO");
			buf.append(" WHERE IDUSRFROM=" + idUser);
		} else if (TypeMessageStatutEnum.RECU.equals(typeMessageStatutEnum) || TypeMessageStatutEnum.LU.equals(typeMessageStatutEnum)
		    || TypeMessageStatutEnum.NON_LU.equals(typeMessageStatutEnum) || TypeMessageStatutEnum.SUPPRIME.equals(typeMessageStatutEnum)) {
			buf.append(" INNER JOIN USR U ON U.IDUSR=M.IDUSRFROM");
			buf.append(" WHERE IDUSRTO=" + idUser);
		}
		// statut sur le champ technique
		buf.append(" AND MS.STATUT='" + DefaultEntity.ENTITY_ACTIVE_STATE + "'");
		// le statut est toujours pour le user en cours (envoyé, recus ou supprimes
		// du user en cours)
		buf.append(" AND MS.IDUSR=" + idUser);
		if (TypeMessageStatutEnum.RECU.equals(typeMessageStatutEnum)) {
			buf.append(" AND MS.IDMESSAGESTATUT in(" + TypeMessageStatutEnum.NON_LU.getId() + "," + TypeMessageStatutEnum.LU.getId() + ")");
		} else {
			buf.append(" AND MS.IDMESSAGESTATUT=" + typeMessageStatutEnum.getId());
		}
		// if (TypeMessageStatutEnum.SUPPRIME.equals(typeMessageStatutEnum)) {
		// buf.append(" AND IDMESSAGESTATUT=" +
		// TypeMessageStatutEnum.SUPPRIME.getId());
		// }
		// buf.append(" group by IDMESSAGE");
		buf.append(" ORDER BY DTSTATUT DESC, IDMESSAGE");

		final List<Object[]> found = getSession().createSQLQuery(buf.toString())

		.addScalar("idMessage", new LongType()).addScalar("sujet", new StringType())

		.addScalar("txMessage", new StringType()).addScalar("dt", new CalendarType()).addScalar("statut", new IntegerType()).

		addScalar("idusrto", new IntegerType()).addScalar("nom", new StringType()).addScalar("prenom", new StringType()).addScalar("MSTATUT", new StringType())

		.list();
		for (final Object[] objects : found) {
			final MessageDTO dto = new MessageDTO();
			dto.setBusinessObjectId((Long) objects[0]);
			dto.setSujet((String) objects[1]);
			dto.setMessage((String) objects[2]);
			dto.setDate(((GregorianCalendar) objects[3]).getTime());
			dto.setTypeMessageStatutEnum(TypeMessageStatutEnum.getEnumById((Integer) objects[4]));
			dto.setIdCorrespondant((Integer) objects[5]);
			dto.setNomCorrespondant((String) objects[6]);
			dto.setPrenomCorrespondant((String) objects[7]);
			dto.setStatut((String) objects[8]);
			messages.add(dto);
		}
		return messages;
	}

	/**
	 * Passe tous les statuts <b>actuels</b> des messages <b>du user courant</b> à
	 * supprimés (flag 'D') et créé le nouveau statut désiré.
	 * */
	@Override
	@Transactional
	public void changerMessagesStatut(final Set<Long> ids, final Long idUser, final TypeMessageStatutEnum typeMessageStatutEnum) {
		// Mettre tous les anciens du message status as Deleted (flag 'D')

		final String asString = asString(ids);
		final int update = getSession().createSQLQuery(
		    "UPDATE USRMESSAGESTATUT SET STATUT='" + DefaultEntity.ENTITY_DELETED_STATE + "' WHERE IDUSR=" + idUser + " AND IDMESSAGE IN(" + asString + ")")
		    .executeUpdate();
		for (final Long id : ids) {
			final UserMessageStatutEntity newStatut = new UserMessageStatutEntity();
			newStatut.setIdMessage(id);
			newStatut.setIdUser(idUser);
			newStatut.setDateStatut(new Date());
			// Nouveau statut
			newStatut.setTypeMessageStatutEnum(typeMessageStatutEnum);
			getSession().save(newStatut);
		}

	}

	public List<DefaultEntity> loadByUserId(final DefaultEntity entity) {
		return getSession().createCriteria(entity.getClass()).add(Restrictions.eq("idUsr", entity.getEntityId())).list();
	}

	public List<DefaultEntity> loadUserPreferences(final Long entityId) {
		return getSession().createCriteria(UserPreferenceEntity.class).add(Restrictions.eq("idUsr", entityId)).list();
	}

	@Override
	public Set<UserEntity> findUserBycriteria(UserCriteria criteria) {
		final StringBuffer sql = new StringBuffer("SELECT u.idUsr AS ID FROM usr u ");
		// sql.append(" left outer join usrphoto foto on (foto.idusr=u.idusr and foto.loprincipale=1)");
		sql.append(" WHERE 1=1 AND ISVALID=" + TypeUserStatutEnum.VALID.getStatut());
		sql.append(" AND u.STATUT='" + DefaultEntity.ENTITY_ACTIVE_STATE + "'");
		if (criteria.getIdUser() != null) {
			sql.append(" AND u.idusr=" + criteria.getIdUser());
		}
		if ((criteria.getIdCantons() != null) && (criteria.getIdCantons().size() > 0)) {
			final Set<Integer> idCantons = criteria.getIdCantons();
			sql.append(" and exists(");
			sql.append(" select 1 from usradress ad");
			// sql.append(" inner join ville v on (v.idville=ad.idville)";
			sql.append(" inner join canton can on can.idCanton=ad.idCanton and" +
			// "can.idcanton=v.idville and " +
			    " can.idcanton in (" + asString(idCantons) + ")");
			sql.append(" where ad.idusr = u.idusr");
			sql.append(" )");
		}
		if (criteria.getIdSports() != null) {
			final Set<Integer> idSports = criteria.getIdSports();
			for (final Integer integer : idSports) {
				sql.append(" and exists(");
				sql.append(" select 1 from usrsport usp" + integer);
				sql.append(" where usp" + integer + ".idSport=" + integer + " and usp" + integer + ".idusr = u.idusr and usp" + integer + ".statut='A'");
				sql.append(" )");
			}
		}
		if (criteria.getIdInterets() != null) {
			final Set<Integer> idInterets = criteria.getIdInterets();
			for (final Integer id : idInterets) {
				sql.append(" and exists(");
				sql.append(" select 1 from usrinteret ui");
				sql.append(" where ui.idusr = u.idusr and ui.idInteret=" + id);
				sql.append(" and ui.statut='A')");
			}
		}
		if (criteria.getIdMusiques() != null) {
			final Set<Integer> idMusiques = criteria.getIdMusiques();
			for (final Integer integer : idMusiques) {
				sql.append(" and exists(");
				sql.append(" select 1 from usrmusique um" + integer);
				sql.append(" where um" + integer + ".idMusique=" + integer + " and um" + integer + ".idusr = u.idusr and um" + integer + ".statut='A'");
				sql.append(" )");
			}
		}
		if (criteria.getIdReligions() != null) {
			final Set<Integer> ids = criteria.getIdReligions();
			for (final Integer integer : ids) {
				sql.append(" AND EXISTS(");
				sql.append(" SELECT 1 FROM USRRELIGION UR" + integer);
				sql.append(" WHERE UR" + integer + ".IDRELIGION=" + integer + " AND UR" + integer + ".idusr = u.idusr and UR" + integer + ".STATUT='A'");
				sql.append(" )");
			}
		}
		if (criteria.getIdProfessions() != null) {
			final Set<Integer> ids = criteria.getIdProfessions();
			for (final Integer integer : ids) {
				sql.append(" AND EXISTS(");
				sql.append(" SELECT 1 FROM USRPROFESSION UP" + integer);
				sql.append(" WHERE UP" + integer + ".IDPROFESSION=" + integer + " AND UP" + integer + ".IDUSR = u.idusr AND UP" + integer + ".STATUT='A'");
				sql.append(" )");
			}
		}
		if (criteria.getIdCaracteres() != null) {
			final Set<Integer> ids = criteria.getIdCaracteres();
			for (final Integer integer : ids) {
				sql.append(" AND EXISTS(");
				sql.append(" SELECT 1 FROM USRCARACTERE UC" + integer);
				sql.append(" WHERE UC" + integer + ".IDCARACTERE=" + integer + " AND UC" + integer + ".IDUSR = u.idusr AND UC" + integer + ".STATUT='A'");
				sql.append(" )");
			}
		}
		if (criteria.getProfileCriteria() != null) {
			final ProfileCriteria profileCriteria = criteria.getProfileCriteria();
			sql.append(" and exists(");
			sql.append(" select 1 from usrprofile pr");
			sql.append(" where pr.idusr = u.idusr");
			if (profileCriteria.getMarie() != null) {
				sql.append(" and pr.nbMarie=" + profileCriteria.getMarie());
			}
			if (profileCriteria.getDivorce() != null) {
				sql.append(" and pr.nbDivorce=" + profileCriteria.getDivorce());
			}
			if (profileCriteria.getVeuf() != null) {
				sql.append(" and pr.nbVeuf=" + profileCriteria.getVeuf());
			}
			if (profileCriteria.getNombreEnfant() != null) {
				sql.append(" and pr.nbEnfant=" + profileCriteria.getNombreEnfant());
			}
			if (profileCriteria.getGenre() != null) {
				sql.append(" and pr.idGenre=" + profileCriteria.getGenre());
			}
			if (profileCriteria.getSerieux() != null) {
				sql.append(" and pr.nbSerieux=" + profileCriteria.getSerieux());
			}
			sql.append(" )");
		}
		if (criteria.getCoquinCriteria() != null) {
			final CoquinCriteria coquinCriteria = criteria.getCoquinCriteria();
			sql.append(" AND EXISTS(");
			sql.append(" SELECT 1 FROM USRCOQUIN UC");
			sql.append(" where UC.IDUSR = U.IDUSR");
			if (coquinCriteria.getAdultere() != null) {
				sql.append(" AND UC.LOADULTERE = " + coquinCriteria.getAdultere());
			}
			if (coquinCriteria.getEchangiste() != null) {
				sql.append(" AND UC.LOECHANGISTE = " + coquinCriteria.getEchangiste());
			}
			if (coquinCriteria.getPartouze() != null) {
				sql.append(" AND UC.LOPARTOUZE = " + coquinCriteria.getPartouze());
			}
			if (coquinCriteria.getUnSoir() != null) {
				sql.append(" AND UC.LOUNSOIR = " + coquinCriteria.getUnSoir());
			}
			sql.append(") ");
		}
		if (criteria.getPhysiqueCriteria() != null) {
			final PhysiqueCriteria physiqueCriteria = criteria.getPhysiqueCriteria();
			sql.append(" and exists(");
			sql.append(" select 1 from USRPHYSIQUE UPH");
			sql.append(" where UPH.idusr = u.idusr");
			if (physiqueCriteria.getTailleMin() != null) {
				sql.append(" AND UPH.NBTAILLE >= " + physiqueCriteria.getTailleMin());
			}
			if (physiqueCriteria.getTailleMax() != null) {
				sql.append(" AND UPH.NBTAILLE <= " + physiqueCriteria.getTailleMax());
			}
			if (physiqueCriteria.getPoidsMin() != null) {
				sql.append(" AND UPH.NBPOIDS >= " + physiqueCriteria.getPoidsMin());
			}
			if (physiqueCriteria.getPoidsMax() != null) {
				sql.append(" AND UPH.NBPOIDS <= " + physiqueCriteria.getPoidsMax());
			}
			if (physiqueCriteria.getCouleurCheveux() != null) {
				sql.append(" AND UPH.IDCOULCHEVEUX in (" + asString(physiqueCriteria.getCouleurCheveux()) + ")");
			}
			if (physiqueCriteria.getCouleurYeux() != null) {
				sql.append(" AND UPH.IDCOULYEUX in (" + asString(physiqueCriteria.getCouleurYeux()) + ")");
			}
			sql.append(") ");
		}
		if (criteria.getIdSexe() != null) {
			sql.append(" and u.idSexe=" + criteria.getIdSexe());
		}
		if (criteria.getAgeMin() != null) {
			sql.append(" and (YEAR(CURDATE())-YEAR(dtUsrNaissance)) -(RIGHT(CURDATE(),5)<RIGHT(dtUsrNaissance,5)) >= " + criteria.getAgeMin());
		}
		if (criteria.getAgeMax() != null) {
			sql.append(" and (YEAR(CURDATE())-YEAR(dtUsrNaissance)) -(RIGHT(CURDATE(),5)<RIGHT(dtUsrNaissance,5)) <= " + criteria.getAgeMax());
		}

		final List<Long> ids = getSession().createSQLQuery(sql.toString()).addScalar("ID", new LongType()).list();
		final Set<UserEntity> arrayList = new LinkedHashSet<UserEntity>();
		if (!ids.isEmpty()) {
			final List<DefaultEntity> loadByIds = loadByIds(UserEntity.class, ids);
			for (final DefaultEntity defaultEntity : loadByIds) {
				final UserEntity userEntity = (UserEntity) defaultEntity;
				final Set<UserPhotoEntity> userPhotos = userEntity.getUserPhotos();
				arrayList.add(userEntity);
			}
		}
		return arrayList;
	}

	private <T> String asString(final Set<T> set) {
		if (set == null) {
			throw new IllegalStateException("Le paramètre Set<Integer> est null.");
		}
		String toReturn = "";
		for (final T value : set) {
			toReturn += (value.toString() + ",");
		}
		toReturn = toReturn.substring(0, toReturn.length() - 1);
		return toReturn;
	}

	private <T extends DefaultEntity> String entityIdAsString(final Set<T> set) {
		if (set == null) {
			throw new IllegalStateException("Le paramètre Set<Integer> est null.");
		}
		String toReturn = "";
		for (final DefaultEntity integer : set) {
			toReturn += (integer.getEntityId() + ",");
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

			// Sauvegarder une entité UserPhysique
			ajouterUserPhysique(userEntity.getEntityId(), new PhysiqueCriteria());

			saveUserConnection(userEntity.getEntityId(), criteria.getIp());

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
	 * Sauvegarde en base la paire IP/idUser en base.
	 * 
	 * @param idUser
	 * */
	@Override
	public void saveUserConnection(final Long idUser, final String ip) {
		final UserConnectionEntity userConnectionEntity = new UserConnectionEntity();
		userConnectionEntity.setIdUsr(idUser);
		userConnectionEntity.setIp(ip);
		userConnectionEntity.setDateConnection(new Date());
		getSession().save(userConnectionEntity);
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
	public UserCriteria authentification(String email, String password) throws LoginFailedException, EmailNonValideException, CompteBloqueException,
	    CompteNonValideException {
		final UserEntity userEntity = (UserEntity) getSession().createCriteria(UserEntity.class).add(Restrictions.eq("email", email))
		    .add(Restrictions.eq("password", password))
		    // .add(Restrictions.eq("idValid",
		    // Integer.valueOf(TypeUserStatutEnum.VALID.getStatut())))
		    .uniqueResult();
		UserCriteria userCriteria = null;
		if (userEntity == null) {
			throw new LoginFailedException();
		} else if (TypeUserStatutEnum.NEW.getStatut().equals(userEntity.getIdValid())) {
			throw new EmailNonValideException();
		} else if (TypeUserStatutEnum.BLOCK.getStatut().equals(userEntity.getIdValid())) {
			throw new CompteBloqueException();
		} else if (TypeUserStatutEnum.WAIT.getStatut().equals(userEntity.getIdValid())) {
			throw new CompteNonValideException();
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
		final Set<CantonDTO> cantons = new LinkedHashSet<CantonDTO>();
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

	/**
	 * <b><font color=red>AUCUNE ENTITE N EST SUPPRIMEE PHYSIQUEMENT !</font><b><br/>
	 * Permet de flagger comme supprimées un set d'entitées (mets le champs STATUT
	 * a 'D').
	 * */
	@Override
	@Transactional
	public <T extends DefaultEntity> Integer supprimerEntities(Set<T> entities, Long idUser) {
		Integer updated = null;
		if ((entities != null) && (entities.size() > 0) && (idUser != null)) {
			updated = getSession().createSQLQuery(
			    "update usrmessage set statut='" + DefaultEntity.ENTITY_DELETED_STATE + "' where idMessage in(" + entityIdAsString(entities) + ") and IDUSRTO="
			        + idUser).executeUpdate();
			logger.debug("expected=" + entities.size() + " actual=" + updated + " entités ont été flaggées comme supprimées pour idUser=" + idUser);
		}
		return updated;
	}

	@Override
	public Set<AmiDTO> findAmis(Long idUser) {
		final Set<AmiDTO> amis = new LinkedHashSet<AmiDTO>();
		String sql = "SELECT  u.idusr as ID,u.txusrprenom as PRE,u.dtUsrNaissance as DT, ad.idcanton as IDCANTON, txUrl as fileName ";
		sql += "from usrami a inner join usr u on u.idUsr=a.idAmi ";
		sql += "inner join usradress ad on ad.idusr=u.idusr ";
		sql += "left outer join usrphoto ph on ph.idusr=a.idAmi and ph.loprincipale=1 and ph.statut='A'";
		sql += "where a.idusr=" + idUser + " and a.statut='A' and u.statut='A'";

		final List<Object[]> found = getSession().createSQLQuery(sql)

		.addScalar("ID", new LongType())

		.addScalar("PRE", new StringType())

		.addScalar("DT", new DateType())

		.addScalar("IDCANTON", new IntegerType())

		.addScalar("fileName", new StringType()).list();

		for (final Object[] objects : found) {
			// final UserCriteria userCriteria = new UserCriteria();
			final AmiDTO amiDTO = new AmiDTO();
			amiDTO.setBusinessObjectId((Long) objects[0]);
			amiDTO.setPrenom((String) objects[1]);
			amiDTO.setDateNaissance((Date) objects[2]);
			amiDTO.addCanton((Integer) objects[3]);
			final String url = (String) objects[4];
			if (url != null) {
				final PhotoDTO photoDTO = new PhotoDTO();
				photoDTO.setFileName(url);
				// elle est forcément principale par le
				// filtre de la requête
				photoDTO.setPrincipale(true);
				amiDTO.addPhoto(photoDTO);
			}
			amis.add(amiDTO);
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
		final Set<PhotoDTO> loadPhotosByUser = loadPhotosByUser(idUser);
		boolean checkHasPrincipale = true;

		for (final String fileName : names) {
			final UserPhotoEntity photo = new UserPhotoEntity();
			photo.setIdUser(idUser);
			photo.setFileName(fileName);
			// Si aucune photo, alors la première est désignée comme principale
			if (checkHasPrincipale && loadPhotosByUser.isEmpty()) {
				checkHasPrincipale = false;
				photo.setPrincipale(Integer.valueOf(1));
			}
			getSession().save(photo);
		}
	}

	/** Retourne la liste des photos */
	@SuppressWarnings("unchecked")
	public Set<PhotoDTO> loadPhotosByUser(final Long idUser) {
		final Set<PhotoDTO> set = new LinkedHashSet<PhotoDTO>();
		final List<UserPhotoEntity> list = getSession().createCriteria(UserPhotoEntity.class).add(Restrictions.eq("idUser", idUser))
		    .add(AmabaDao.ENTITY_ACTIVE_STATE).list();
		for (final UserPhotoEntity userPhotoEntity : list) {
			final PhotoDTO photo = new PhotoDTO();
			photo.setBusinessObjectId(userPhotoEntity.getEntityId());
			photo.setFileName(userPhotoEntity.getFileName());
			photo.setIdUser(idUser);
			photo.setPrincipale(Integer.valueOf(1).equals(userPhotoEntity.getPrincipale()));
			photo.setDateUpload(userPhotoEntity.getDateCreation());
			set.add(photo);
		}
		return set;
	}

	@Transactional
	public Long flagPhotoPrincipale(Long idUser, Long idPhoto) {
		final Session session = getSession();
		try {
			@SuppressWarnings("unchecked")
			final List<UserPhotoEntity> list = session.createCriteria(UserPhotoEntity.class).add(Restrictions.eq("idUser", idUser))
			    .add(Restrictions.eq("principale", Integer.valueOf(1))).add(Restrictions.ne("entityId", idPhoto)).add(AmabaDao.ENTITY_ACTIVE_STATE).list();
			for (final UserPhotoEntity userPhotoEntity : list) {
				userPhotoEntity.setPrincipale(Integer.valueOf(0));
				session.save(userPhotoEntity);
			}
			final UserPhotoEntity load = (UserPhotoEntity) session.get(UserPhotoEntity.class, idPhoto);
			load.setPrincipale(Integer.valueOf(1));
			session.save(load);
		} finally {
			session.flush();
			session.close();
		}
		return idPhoto;
	}

	/** Ajoute une propriété physique de type {@link UserPhysiqueEntity}. */
	@Transactional
	public void ajouterUserPhysique(Long idUser, PhysiqueCriteria physiqueCriteria) {
		final UserPhysiqueEntity userPhysiqueEntity = new UserPhysiqueEntity();
		userPhysiqueEntity.setIdUser(idUser);
		userPhysiqueEntity.setIdCouleurCheveux(physiqueCriteria.getCouleurCheveux() != null ? physiqueCriteria.getCouleurCheveux().iterator().next() : null);
		userPhysiqueEntity.setIdCouleurYeux(physiqueCriteria.getCouleurYeux() != null ? physiqueCriteria.getCouleurYeux().iterator().next() : null);
		userPhysiqueEntity.setPoids(physiqueCriteria.getPoidsMin());
		userPhysiqueEntity.setTaille(physiqueCriteria.getTailleMin());
		getSession().save(userPhysiqueEntity);
	}

	/** Ajoute une propriété physique de type {@link UserPhysiqueEntity}. */
	@Transactional
	public void ajouterUserProfil(final Long idUser, final ProfileCriteria profileCriteria) {
		final UserEntity userEntity = (UserEntity) getSession().get(UserEntity.class, idUser);
		UserProfileEntity userProfileEntity = null;
		if (userEntity != null) {
			final Set<UserProfileEntity> userProfil = userEntity.getUserProfil();
			for (final UserProfileEntity upe : userProfil) {
				userProfileEntity = upe;
				break;
			}
		}
		if (userProfileEntity == null) {
			userProfileEntity = new UserProfileEntity();
			userProfileEntity.setUserEntity(userEntity);
		}
		userProfileEntity.setDivorce(profileCriteria.getDivorce());
		userProfileEntity.setIdGenre(profileCriteria.getGenre());
		userProfileEntity.setMarie(profileCriteria.getMarie());
		userProfileEntity.setNombreEnfant(profileCriteria.getNombreEnfant());
		userProfileEntity.setSerieux(profileCriteria.getRechercheRelationSerieuse());
		userProfileEntity.setVeuf(profileCriteria.getVeuf());

		getSession().save(userProfileEntity);
	}

	public void getVueDetailleUser(Long idUsr) {
		final StringBuffer buf = new StringBuffer();
		buf.append("select u.idusr, u.txusrprenom, u.isvalid, uph.* from usr u ");
		buf.append("inner join usradress ad on ad.idusr=u.idusr ");
		buf.append("left outer join usrcaractere uc on uc.idusr=u.idusr ");
		buf.append("left outer join usrinteret ui on ui.idusr=u.idusr ");
		buf.append("left outer join usrmusique um on um.idusr=u.idusr ");
		buf.append("left outer join usrprofession up on up.idusr=u.idusr ");
		buf.append("left outer join usrsport us on us.idusr=u.idusr ");
		buf.append("left outer join usrreligion ur on ur.idusr=u.idusr ");
		buf.append("inner join usrprofile upr on upr.idusr=u.idusr ");
		buf.append("inner join usrphysique uph on uph.idusr=u.idusr ");
		buf.append("where 1=1 ");
		/* and u.isvalid=2 */
		buf.append("and u.idusr= " + idUsr);
	}

	/**
	 * Retourne le text du message défini par son id.
	 * 
	 * @param idMessage
	 *          - l'id technique du message
	 * @return le text du message
	 */
	public MessageDTO getMessageContentById(final Long idUser, final Long idMessage) {
		MessageDTO messageDTO = null;
		final UserMessageEntity userMessageEntity = (UserMessageEntity) getSession().createCriteria(UserMessageEntity.class).add(Restrictions.idEq(idMessage))
		    .createAlias("messageStatuts", "messageStatuts").add(Restrictions.eq("messageStatuts.idUser", idUser)).uniqueResult();
		if (userMessageEntity != null) {
			messageDTO = new MessageDTO();
			messageDTO.setBusinessObjectId(userMessageEntity.getEntityId());
			messageDTO.setMessage(userMessageEntity.getMessage());
			messageDTO.setDate(userMessageEntity.getDateCreation());
		}
		return messageDTO;
	}

	/**
	 * 
	 * */
	@Override
	@Transactional
	public void updatePreference(final Long idUser, final Set<PreferenceDTO> preferenceDTOs) {
		Transaction transaction = null;
		try {
			final Session session = getSession();
			transaction = session.beginTransaction();
			// on supprime les préférences qui existent
			final int executeUpdate = session.createSQLQuery("delete from usrpreference where idusr=" + idUser).executeUpdate();
			logger.info("Préférences supprimées:" + executeUpdate);
			// On ajoute les nouvelles
			for (final PreferenceDTO preferenceDTO : preferenceDTOs) {
				final UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
				final UserEntity userEntity = new UserEntity();
				userEntity.setEntityId(idUser);
				userPreferenceEntity.setUserEntity(userEntity);
				userPreferenceEntity.setIdPreference(preferenceDTO.getTypePreferenceEnum().getId());
				userPreferenceEntity.setPreferenceValue(preferenceDTO.getValue());
				session.save(userPreferenceEntity);
			}
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}

	/**
	 * Retourne les informations du profil détaillé.
	 * */
	@Override
	public UserCriteria getProfileDetaille(final Long idUser) {
		final UserCriteria userCriteria = new UserCriteria();
		// Charger les préférences du user dont on veut voir le détail.
		final Session session = getSession();
		final UserEntity userEntity = (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.idEq(idUser))
		    .createAlias("userProfil", "userProfil", Criteria.INNER_JOIN).uniqueResult();
		final Set<UserPreferenceEntity> userPreferences = userEntity.getUserPreferences();

		for (final UserPreferenceEntity userPreferenceEntity : userPreferences) {
			System.out.println(userPreferenceEntity);
		}
		userCriteria.setIdUser(userEntity.getEntityId());
		userCriteria.setNom(userEntity.getNom());
		userCriteria.setPrenom(userEntity.getPrenom());
		userCriteria.setEmail(userEntity.getEmail());
		userCriteria.setDateNaissance(userEntity.getDateNaissance());
		userCriteria.setIdSexe(userEntity.getIdSexe());
		userCriteria.addCanton(userEntity.getIdCanton());

		final Set<PhotoDTO> loadPhotosByUser = loadPhotosByUser(idUser);
		userCriteria.setPhotos(loadPhotosByUser);

		final Set<UserProfileEntity> userProfil = userEntity.getUserProfil();
		for (final UserProfileEntity userProfileEntity : userProfil) {
			final ProfileCriteria profileCriteria = new ProfileCriteria();
			profileCriteria.setDivorce(userProfileEntity.getDivorce());
			profileCriteria.setMarie(userProfileEntity.getMarie());
			profileCriteria.setVeuf(userProfileEntity.getVeuf());
			profileCriteria.setGenre(userProfileEntity.getIdGenre());
			profileCriteria.setNombreEnfant(userProfileEntity.getNombreEnfant());
			profileCriteria.setRechercheRelationSerieuse(userProfileEntity.getSerieux());
			userCriteria.setProfileCriteria(profileCriteria);
		}

		// Liste des amis
		// userCriteria.setAmis(listeFavoris(idUser));

		final Set<UserInteretEntity> userInterets = userEntity.getUserInterets();
		for (final UserInteretEntity userInteretEntity : userInterets) {
			System.out.println(userInteretEntity);
			userCriteria.addInteret(userInteretEntity.getIdLink());
		}
		final Set<UserMusiqueEntity> userMusics = userEntity.getUserMusics();
		for (final UserMusiqueEntity userInteretEntity : userMusics) {
			System.out.println(userInteretEntity);
			userCriteria.addMusique(userInteretEntity.getIdLink());
		}
		final Set<UserProfessionEntity> userProfessions = userEntity.getUserProfessions();
		for (final UserProfessionEntity userInteretEntity : userProfessions) {
			System.out.println(userInteretEntity);
			userCriteria.addProfession(userInteretEntity.getIdLink());
		}
		final Set<UserReligionEntity> userReligions = userEntity.getUserReligions();
		for (final UserReligionEntity userInteretEntity : userReligions) {
			System.out.println(userInteretEntity);
			userCriteria.addReligion(userInteretEntity.getIdLink());
		}
		final Set<UserSportEntity> userSports = userEntity.getUserSports();
		for (final UserSportEntity sport : userSports) {
			System.out.println(sport);
			userCriteria.addSport(sport.getIdLink());
		}
		//
		return userCriteria;
	}
}
package ch.amaba.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import ch.amaba.dao.model.CantonEntity;
import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.TraductionEntity;
import ch.amaba.dao.model.UserAdressEntity;
import ch.amaba.dao.model.UserConnectionEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.dao.model.UserMessageStatutEntity;
import ch.amaba.dao.model.UserMusiqueEntity;
import ch.amaba.dao.model.UserPreferenceEntity;
import ch.amaba.dao.model.UserProfileEntity;
import ch.amaba.dao.model.UserStatutEntity;
import ch.amaba.dao.utils.DateUtils;
import ch.amaba.model.bo.CantonDTO;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.TraductionDTO;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.constants.TypeMusiqueEnum;
import ch.amaba.model.bo.constants.TypeUserStatutEnum;
import ch.amaba.model.bo.exception.EntityNotFoundException;
import ch.amaba.model.bo.exception.LoginFailedException;
import ch.amaba.model.bo.exception.UserAlreadyExistsException;

public class AmabaDao extends HibernateTemplate implements IAmabaDao {

	Logger LOG = LoggerFactory.getLogger(AmabaDao.class);

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
	public List<UserMessageEntity> getMessages() {
		// TODO Auto-generated method stub
		return null;
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

	public void supprimer(Long id) {
		final DefaultEntity defaultEntity = (DefaultEntity) getSession().load(DefaultEntity.class, id);
		if (defaultEntity != null) {
			defaultEntity.setStatut("D");
			defaultEntity.setLastModificationDate(new Date());
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
		String sql = "SELECT u.idUsr AS ID FROM usr u WHERE 1=1";
		if (criteria.getIdCantons() != null) {
			final Set<Integer> idCantons = criteria.getIdCantons();
			sql += " and exists(";
			sql += " select 1 from usradress ad";
			sql += " inner join ville v on (v.idville=ad.idville)";
			sql += " inner join canton can on (can.idcanton=v.idville and can.idcanton in(" + AmabaDao.asString(idCantons) + "))";
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
			for (final Integer integer : idInterets) {
				sql += " and exists(";
				sql += " select 1 from usrinteret ui" + integer;
				sql += " where ui" + integer + ".idInteret=" + integer + " and ui" + integer + ".idusr = u.idusr";
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
		if (criteria.getProfileCriteria() != null) {
			final ProfileCriteria profileCriteria = criteria.getProfileCriteria();
			sql += " and exists(";
			sql += " select 1 from usrprofil pr";
			sql += " where pr.idusr = u.idusr";
			if (profileCriteria.isMarie() != null) {
				sql += " and pr.loMarie=" + profileCriteria.isMarie();
			}
			if (profileCriteria.isDivorce() != null) {
				sql += " and pr.loDivorce=" + profileCriteria.isDivorce();
			}
			if (profileCriteria.isVeuf() != null) {
				sql += " and pr.loVeuf=" + profileCriteria.isVeuf();
			}
			if (profileCriteria.getNombreEnfant() != null) {
				sql += " and pr.nbEnfant=" + profileCriteria.getNombreEnfant();
			}
			if (profileCriteria.getGenre() != null) {
				sql += " and pr.loIdGenre=" + profileCriteria.getGenre();
			}
			sql += " )";
		}
		if (criteria.getIdSexe() != null) {
			sql += " and u.idSexe=" + criteria.getIdSexe();
		}
		if (criteria.getAgeMin() != null) {
			sql += " and (now() - dtUsrNaissance) >= " + criteria.getAgeMin();
		}
		if (criteria.getAgeMax() != null) {
			sql += " and (now() - dtUsrNaissance) <= " + criteria.getAgeMin();
		}

		System.out.println(sql);
		final List<Long> ids = getSession().createSQLQuery(sql).addScalar("ID", Hibernate.LONG).list();
		final Set<UserEntity> arrayList = new HashSet<UserEntity>();
		if (!ids.isEmpty()) {
			final List<DefaultEntity> loadByIds = loadByIds(UserEntity.class, ids);

			for (final DefaultEntity defaultEntity : loadByIds) {
				arrayList.add((UserEntity) defaultEntity);
			}
		}
		return arrayList;
	}

	static String asString(final Set<Integer> set) {
		if (set == null) {
			throw new IllegalStateException("Le paramètre Set<Integer> est null.");
		}
		String toReturn = "";
		for (final Integer integer : set) {
			toReturn += (integer.toString() + ",");
		}
		toReturn = toReturn.substring(0, toReturn.length() - 2);
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
					userProfileEntity.setTypeGenreEnum(profileCriteria.getGenre());
				}
				if (profileCriteria.getNombreEnfant() != null) {
					userProfileEntity.setNombreEnfant(profileCriteria.getNombreEnfant());
				}
				if (profileCriteria.isDivorce() != null) {
					userProfileEntity.setDivorce(profileCriteria.isDivorce());
				}
				// TODO completer les autres filtres................
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

	@Override
	public void ajouterAmi(Long idAmi) throws EntityNotFoundException {
		// TODO Auto-generated method stub

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
		userMusiqueEntity.setTypeMusiqueEnum(typeMusiqueEnum);
		userMusiqueEntity.setUserEntity(userEntity);
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
		final List<CantonEntity> list = getSession().createCriteria(CantonEntity.class).list();
		for (final CantonEntity cantonEntity : list) {
			final CantonDTO dto = new CantonDTO();
			dto.setBusinessObjectId(cantonEntity.getEntityId());
			dto.setCodeCanton(cantonEntity.getCodeCanton());
			cantons.add(dto);
		}
		return cantons;
	}

	@Override
	public HashMap<String, HashMap<String, String>> loadTraductions(String langue) {
		final HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		final List<TraductionEntity> list = getSession().createCriteria(TraductionEntity.class).add(Restrictions.eq("langue", langue)).addOrder(Order.asc("cle"))
		    .list();
		for (final TraductionEntity traductionEntity : list) {
			final TraductionDTO dto = new TraductionDTO();
			dto.setBusinessObjectId(traductionEntity.getEntityId());
			dto.setCodeType(traductionEntity.getType());
			dto.setCodeCle(traductionEntity.getCle());
			dto.setTraduction(traductionEntity.getTraduction());
			HashMap<String, String> traductions;
			if (map.get(traductionEntity.getType()) == null) {
				traductions = new HashMap<String, String>();
				traductions.put(traductionEntity.getCle(), traductionEntity.getTraduction());
				map.put(traductionEntity.getType(), traductions);
			} else {
				traductions = map.get(traductionEntity.getType());
				traductions.put(traductionEntity.getCle(), traductionEntity.getTraduction());
			}
		}
		return map;
	}
}

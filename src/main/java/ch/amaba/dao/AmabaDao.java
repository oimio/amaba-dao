package ch.amaba.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.amaba.dao.model.DefaultEntity;
import ch.amaba.dao.model.MessageStatutEntity;
import ch.amaba.dao.model.PropertyDefinitionEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.dao.model.UserMessageEntity;
import ch.amaba.dao.model.UserMessageStatutEntity;
import ch.amaba.dao.model.UserPreferenceEntity;
import ch.amaba.model.bo.SearchUserCriteria;

public class AmabaDao extends HibernateTemplate implements IAmabaDao {

	Logger LOG = LoggerFactory.getLogger(AmabaDao.class);

	public AmabaDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public <T extends DefaultEntity> T loadById(final Class<T> entityClass, final Long id) {
		// TODO Auto-generated method stub
		return null;
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

	@SuppressWarnings("unchecked")
	public List<PropertyDefinitionEntity> loadAll() {
		final List<PropertyDefinitionEntity> loadAll = getSession().createCriteria(PropertyDefinitionEntity.class).list();
		return loadAll;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PropertyDefinitionEntity> loadPropertyDefinition() {
		final List<PropertyDefinitionEntity> loadAll = getSession().createCriteria(PropertyDefinitionEntity.class)
		    .setFetchMode("adresses", FetchMode.JOIN).list();

		return loadAll;
	}

	public List<PropertyDefinitionEntity> findUserProperties(int idUser) {
		final List<PropertyDefinitionEntity> toReturn = getSession().createCriteria(PropertyDefinitionEntity.class)
		    .setFetchMode("propertyValues", FetchMode.JOIN).list();
		// criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		// criteria.setMaxResults(100);
		return toReturn;
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
			userMessageStatutEntity.setMessageStatutEntity(MessageStatutEntity.ENVOYE);
			userMessageStatutEntity.setUserMessageEntity(userMessageEntity);
			userMessageStatutEntity.setIdUser(1L);

			final UserMessageStatutEntity userMessageStatutNonLuEntity = new UserMessageStatutEntity();
			userMessageStatutNonLuEntity.setDateStatut(new Date());
			userMessageStatutNonLuEntity.setMessageStatutEntity(MessageStatutEntity.NON_LU);
			userMessageStatutNonLuEntity.setUserMessageEntity(userMessageEntity);
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
	public void messageLu(Long idMessage) {
		// TODO Auto-generated method stub

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
	public List<UserEntity> findUserBycriteria(SearchUserCriteria searchUserCriteria) {

		return null;
	}
}

package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultEntity {

	public final static String ENTITY_ACTIVE_STATE = "A";
	public final static String ENTITY_DELETED_STATE = "D";

	@Id
	@GeneratedValue
	private Long entityId;

	@Column(name = "VERSION")
	private Long version;

	@Column(name = "DTE_MOD")
	private Date dateModification;

	@Column(name = "DTE_CRE")
	private Date dateCreation;

	@Column(name = "USR_MOD")
	private String userModification;

	@Column(name = "USR_CRE")
	private String userCreation;

	@Column(name = "STATUT")
	private String statut;

	/**
	 * Marque une entité comme supprimée.
	 * */
	public void flagAsDeleted() {
		setStatut(DefaultEntity.ENTITY_DELETED_STATE);
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getUserModification() {
		return userModification;
	}

	public void setUserModification(String userModification) {
		this.userModification = userModification;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
}

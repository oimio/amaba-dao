package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "messageStatut")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idMessageStatut")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class MessageStatutEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "cdStatut")
	private String codeStatut;

	public MessageStatutEntity() {
	}

	public MessageStatutEntity(Long entityId) {
		setEntityId(entityId);
	}

	@ManyToOne
	@JoinColumn(name = "idMessageStatut", insertable = false, updatable = false)
	private UserMessageStatutEntity userMessageStatutEntity;

	public String getCodeStatut() {
		return codeStatut;
	}

	public void setCodeStatut(String codeStatut) {
		this.codeStatut = codeStatut;
	}

	public UserMessageStatutEntity getUserMessageStatutEntity() {
		return userMessageStatutEntity;
	}

	public void setUserMessageStatutEntity(UserMessageStatutEntity userMessageStatutEntity) {
		this.userMessageStatutEntity = userMessageStatutEntity;
	}

}
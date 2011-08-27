package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import ch.amaba.model.bo.constants.TypeMessageStatut;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "messageStatut")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idMessageStatut")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "OHDATMOD")) })
public class MessageStatutEntity extends DefaultEntity {

	public static MessageStatutEntity ENVOYE = new MessageStatutEntity(TypeMessageStatut.ENVOYE.getId());
	public static MessageStatutEntity NON_LU = new MessageStatutEntity(TypeMessageStatut.NON_LU.getId());
	public static MessageStatutEntity LU = new MessageStatutEntity(TypeMessageStatut.LU.getId());
	public static MessageStatutEntity SUPPRIME = new MessageStatutEntity(TypeMessageStatut.SUPPRIME.getId());

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

	@PreUpdate
	@PrePersist
	public void sysout(final MessageStatutEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final MessageStatutEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

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
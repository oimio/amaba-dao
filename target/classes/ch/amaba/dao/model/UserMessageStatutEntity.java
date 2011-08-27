package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrMessageStatut")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrMessageStatut")), @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserMessageStatutEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "dtStatut")
	private Date dateStatut;

	/**
	 * La personne qui a changé le statut.
	 * */
	@Column(name = "idUsr")
	private Long idUser;

	public UserMessageStatutEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idMessageStatut")
	public MessageStatutEntity messageStatutEntity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMessage", unique = true, insertable = true)
	public UserMessageEntity userMessageEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserMessageStatutEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserMessageStatutEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public MessageStatutEntity getMessageStatutEntity() {
		return messageStatutEntity;
	}

	public void setMessageStatutEntity(MessageStatutEntity messageStatutEntity) {
		this.messageStatutEntity = messageStatutEntity;
	}

	public Date getDateStatut() {
		return dateStatut;
	}

	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	public UserMessageEntity getUserMessageEntity() {
		return userMessageEntity;
	}

	public void setUserMessageEntity(UserMessageEntity userMessageEntity) {
		this.userMessageEntity = userMessageEntity;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
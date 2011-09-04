package ch.amaba.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrMessage")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idMessage")), @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserMessageEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "txSujet")
	private String sujet;

	@Column(name = "txMessage")
	private String message;

	@Column(name = "idUsrFrom")
	private Long from;

	@Column(name = "idUsrTo")
	private Long to;

	public UserMessageEntity() {
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMessage", insertable = false, updatable = false)
	private Set<UserMessageStatutEntity> messageStatuts;

	@PreUpdate
	@PrePersist
	public void sysout(final UserMessageEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserMessageEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public Set<UserMessageStatutEntity> getMessageStatuts() {
		return messageStatuts;
	}

	public void setMessageStatuts(Set<UserMessageStatutEntity> messageStatuts) {
		this.messageStatuts = messageStatuts;
	}

}
package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrInteret")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idUsrInteret")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserInteretEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserInteretEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idInteret")
	public InteretEntity interet;

	@ManyToOne
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserInteretEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserInteretEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public InteretEntity getInteret() {
		return interet;
	}

	public void setInteret(InteretEntity interet) {
		this.interet = interet;
	}

}
package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
@Table(name = "usrSport")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idUsrSport")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserSportEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserSportEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idSport")
	public SportEntity sport;

	// @ManyToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserSportEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserSportEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public SportEntity getSport() {
		return sport;
	}

	public void setSport(SportEntity sport) {
		this.sport = sport;
	}

	// public UserEntity getUserEntity() {
	// return userEntity;
	// }
	//
	// public void setUserEntity(UserEntity userEntity) {
	// this.userEntity = userEntity;
	// }

}
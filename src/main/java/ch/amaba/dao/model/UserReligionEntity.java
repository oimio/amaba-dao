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
@Table(name = "usrReligion")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idUsrReligion")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserReligionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserReligionEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idReligion")
	public ReligionEntity religion;

	// @ManyToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserReligionEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserReligionEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public ReligionEntity getReligion() {
		return religion;
	}

	public void setReligion(ReligionEntity religion) {
		this.religion = religion;
	}

	// public UserEntity getUserEntity() {
	// return userEntity;
	// }
	//
	// public void setUserEntity(UserEntity userEntity) {
	// this.userEntity = userEntity;
	// }

}
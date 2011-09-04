package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrProfil")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrProfil")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserCoquinEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "loEchangiste")
	private boolean isEchangiste;

	@Column(name = "loPartouze")
	private boolean partouze;

	/**
	 * Recherche une relation pour un soir
	 * */
	@Column(name = "loUnSoir")
	private boolean unSoir;

	/**
	 * Recherche une relation pour un soir
	 * */
	@Column(name = "loAdultere")
	private boolean adultere;

	// @OneToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	public UserCoquinEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final UserCoquinEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserCoquinEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	// public UserEntity getUserEntity() {
	// return userEntity;
	// }
	//
	// public void setUserEntity(UserEntity userEntity) {
	// this.userEntity = userEntity;
	// }

	public boolean isEchangiste() {
		return isEchangiste;
	}

	public void setEchangiste(boolean isEchangiste) {
		this.isEchangiste = isEchangiste;
	}

	public boolean isPartouze() {
		return partouze;
	}

	public void setPartouze(boolean partouze) {
		this.partouze = partouze;
	}

	public boolean isUnSoir() {
		return unSoir;
	}

	public void setUnSoir(boolean unSoir) {
		this.unSoir = unSoir;
	}

	public boolean isAdultere() {
		return adultere;
	}

	public void setAdultere(boolean adultere) {
		this.adultere = adultere;
	}

}
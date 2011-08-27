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
public class UserProfileEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdHomoBi")
	private String codeHomoBi;

	@Column(name = "loEchangiste")
	private boolean isEchangiste;

	@Column(name = "loMarie")
	private boolean marie;

	@Column(name = "loDivorce")
	private boolean divorce;

	@Column(name = "loVeuf")
	private boolean veuf;

	@Column(name = "loAvecEnfant")
	private boolean avecEnfant;

	@Column(name = "loPartouze")
	private boolean partouze;

	/**
	 * Recherche une relation sérieuse
	 * */
	@Column(name = "loSerieux")
	private boolean serieux;

	/**
	 * Recherche une relation pour un soir
	 * */
	@Column(name = "loUnSoir")
	private boolean unSoir;

	// @OneToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	public UserProfileEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final UserProfileEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserProfileEntity o) {
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

	public String getCodeHomoBi() {
		return codeHomoBi;
	}

	public void setCodeHomoBi(String codeHomoBi) {
		this.codeHomoBi = codeHomoBi;
	}

	public boolean isEchangiste() {
		return isEchangiste;
	}

	public void setEchangiste(boolean isEchangiste) {
		this.isEchangiste = isEchangiste;
	}

	public boolean isMarie() {
		return marie;
	}

	public void setMarie(boolean marie) {
		this.marie = marie;
	}

	public boolean isDivorce() {
		return divorce;
	}

	public void setDivorce(boolean divorce) {
		this.divorce = divorce;
	}

	public boolean isVeuf() {
		return veuf;
	}

	public void setVeuf(boolean veuf) {
		this.veuf = veuf;
	}

	public boolean isAvecEnfant() {
		return avecEnfant;
	}

	public void setAvecEnfant(boolean avecEnfant) {
		this.avecEnfant = avecEnfant;
	}

	public boolean isPartouze() {
		return partouze;
	}

	public void setPartouze(boolean partouze) {
		this.partouze = partouze;
	}

	public boolean isSerieux() {
		return serieux;
	}

	public void setSerieux(boolean serieux) {
		this.serieux = serieux;
	}

	public boolean isUnSoir() {
		return unSoir;
	}

	public void setUnSoir(boolean unSoir) {
		this.unSoir = unSoir;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "usrProfil")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrProfil")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
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
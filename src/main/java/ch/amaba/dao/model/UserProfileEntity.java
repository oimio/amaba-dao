package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "usrProfile")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrProfile")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserProfileEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "nbMarie")
	private Short marie;

	@Column(name = "nbDivorce")
	private Short divorce;

	@Column(name = "nbVeuf")
	private Short veuf;

	@Column(name = "nbEnfant")
	private Short nombreEnfant;

	/**
	 * Critère 'Recherche une relation sérieuse'.
	 * */
	@Column(name = "nbSerieux")
	private Short serieux;

	@Column(name = "idGenre")
	private Short idGenre;

	@ManyToOne
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	public UserProfileEntity() {
	}

	public Short getMarie() {
		return marie;
	}

	public void setMarie(Short marie) {
		this.marie = marie;
	}

	public Short getDivorce() {
		return divorce;
	}

	public void setDivorce(Short divorce) {
		this.divorce = divorce;
	}

	public Short getVeuf() {
		return veuf;
	}

	public void setVeuf(Short veuf) {
		this.veuf = veuf;
	}

	public Short getNombreEnfant() {
		return nombreEnfant;
	}

	public void setNombreEnfant(Short nombreEnfant) {
		this.nombreEnfant = nombreEnfant;
	}

	public Short getSerieux() {
		return serieux;
	}

	public void setSerieux(Short serieux) {
		this.serieux = serieux;
	}

	public Short getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Short idGenre) {
		this.idGenre = idGenre;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "usrPhysique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPhysique")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserPhysiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "nbPoids")
	private Integer poids;

	@Column(name = "nbTaille")
	private boolean taille;

	@Column(name = "cdCheveux")
	private boolean couleurCheveux;

	// @OneToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	public UserPhysiqueEntity() {
	}

	public Integer getPoids() {
		return poids;
	}

	public void setPoids(Integer poids) {
		this.poids = poids;
	}

	public boolean isTaille() {
		return taille;
	}

	public void setTaille(boolean taille) {
		this.taille = taille;
	}

	public boolean isCouleurCheveux() {
		return couleurCheveux;
	}

	public void setCouleurCheveux(boolean couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}

}
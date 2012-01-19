package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entité sur les propriétés physique d'une personne (taille, poids, etc...).
 * */
@Entity
@Table(name = "usrPhysique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPhysique")) })
public class UserPhysiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "nbPoids")
	private Integer poids;

	@Column(name = "nbTaille")
	private Integer taille;

	@Column(name = "idCoulCheveux")
	private Integer idCouleurCheveux;

	@Column(name = "idCoulYeux")
	private Integer idCouleurYeux;

	@Column(name = "idUsr")
	private Long idUser;

	public UserPhysiqueEntity() {
	}

	public Integer getPoids() {
		return poids;
	}

	public void setPoids(Integer poids) {
		this.poids = poids;
	}

	public Integer getTaille() {
		return taille;
	}

	public void setTaille(Integer taille) {
		this.taille = taille;
	}

	public Integer getIdCouleurCheveux() {
		return idCouleurCheveux;
	}

	public void setIdCouleurCheveux(Integer idCouleurCheveux) {
		this.idCouleurCheveux = idCouleurCheveux;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Integer getIdCouleurYeux() {
		return idCouleurYeux;
	}

	public void setIdCouleurYeux(Integer idCouleurYeux) {
		this.idCouleurYeux = idCouleurYeux;
	}
}
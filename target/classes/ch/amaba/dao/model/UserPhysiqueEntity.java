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
@Table(name = "usrPhysique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPhysique")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
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

	@PreUpdate
	@PrePersist
	public void sysout(final UserPhysiqueEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserPhysiqueEntity o) {
			o.setLastModificationDate(new Date());
		}
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

	// public UserEntity getUserEntity() {
	// return userEntity;
	// }
	//
	// public void setUserEntity(UserEntity userEntity) {
	// this.userEntity = userEntity;
	// }

}
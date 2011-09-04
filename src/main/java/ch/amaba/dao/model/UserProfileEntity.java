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

import ch.amaba.model.bo.constants.TypeGenreEnum;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrProfil")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrProfil")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserProfileEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "loMarie")
	private boolean marie;

	@Column(name = "loDivorce")
	private boolean divorce;

	@Column(name = "loVeuf")
	private boolean veuf;

	@Column(name = "nbEnfant")
	private Integer nombreEnfant;

	/**
	 * Recherche une relation sérieuse
	 * */
	@Column(name = "loSerieux")
	private boolean serieux;

	TypeGenreEnum typeGenreEnum;

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

	public boolean isSerieux() {
		return serieux;
	}

	public void setSerieux(boolean serieux) {
		this.serieux = serieux;
	}

	public Integer getNombreEnfant() {
		return nombreEnfant;
	}

	public void setNombreEnfant(Integer nombreEnfant) {
		this.nombreEnfant = nombreEnfant;
	}

	public TypeGenreEnum getTypeGenreEnum() {
		return typeGenreEnum;
	}

	public void setTypeGenreEnum(TypeGenreEnum typeGenreEnum) {
		this.typeGenreEnum = typeGenreEnum;
	}

}
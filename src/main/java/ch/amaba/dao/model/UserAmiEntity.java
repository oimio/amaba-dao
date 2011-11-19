package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usrAmi")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrAmi")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")), @AttributeOverride(name = "statut", column = @Column(name = "STATUT")),
    @AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserAmiEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserAmiEntity() {
	}

	@Column(name = "idAmi")
	private Long idAmi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	public Long getIdAmi() {
		return idAmi;
	}

	public void setIdAmi(Long idAmi) {
		this.idAmi = idAmi;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
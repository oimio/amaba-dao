package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.amaba.model.bo.constants.TypeMusiqueEnum;

@Entity

@Table(name = "usrMusique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrMusique")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserMusiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserMusiqueEntity() {
	}

	@Column(name = "idMusique")
	public TypeMusiqueEnum typeMusiqueEnum;

	@ManyToOne
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	public TypeMusiqueEnum getTypeMusiqueEnum() {
		return typeMusiqueEnum;
	}

	public void setTypeMusiqueEnum(TypeMusiqueEnum typeMusiqueEnum) {
		this.typeMusiqueEnum = typeMusiqueEnum;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
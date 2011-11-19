package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "usrInteret")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrInteret")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserInteretEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserInteretEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idInteret")
	public InteretEntity interet;

	@ManyToOne
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public InteretEntity getInteret() {
		return interet;
	}

	public void setInteret(InteretEntity interet) {
		this.interet = interet;
	}

}
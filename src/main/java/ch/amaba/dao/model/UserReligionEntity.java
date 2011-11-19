package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "usrReligion")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrReligion")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserReligionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserReligionEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idReligion")
	public ReligionEntity religion;

	public ReligionEntity getReligion() {
		return religion;
	}

	public void setReligion(ReligionEntity religion) {
		this.religion = religion;
	}

}
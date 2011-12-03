package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "religion")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idReligion")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class ReligionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdReligion")
	private String codeReligion;

	public ReligionEntity() {
	}

	public String getCodeReligion() {
		return codeReligion;
	}

	public void setCodeReligion(String codeReligion) {
		this.codeReligion = codeReligion;
	}

}
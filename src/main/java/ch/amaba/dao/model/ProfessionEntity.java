package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "profession")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idProfession")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class ProfessionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdProfession")
	private String codeProfession;

	public ProfessionEntity() {
	}

	public String getCodeProfession() {
		return codeProfession;
	}

	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}

}
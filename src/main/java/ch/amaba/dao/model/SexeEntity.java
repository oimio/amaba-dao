package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "sexe")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idSexe")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class SexeEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdSexe")
	private String codeSexe;

	public SexeEntity() {
	}

	public String getCodeSexe() {
		return codeSexe;
	}

	public void setCodeSexe(String codeSexe) {
		this.codeSexe = codeSexe;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pays")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idPays")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")), @AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class PaysEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdPays")
	private String codePays;

	public PaysEntity() {
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "preference")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idPreference")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class PreferenceEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "cdPreference")
	private String codePreference;

	public PreferenceEntity() {
	}

	public String getCodePreference() {
		return codePreference;
	}

	public void setCodePreference(String codePreference) {
		this.codePreference = codePreference;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "usrPreference")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPreference")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserPreferenceEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserPreferenceEntity() {
	}

	@Column(name = "idUsr")
	private Long idUsr;
}
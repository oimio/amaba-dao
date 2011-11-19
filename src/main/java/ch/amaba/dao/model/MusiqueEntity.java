package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "musique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idMusique")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),
    @AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")), @AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class MusiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdMusique")
	private String codeMusique;

	public MusiqueEntity() {
	}

	public String getCodeMusique() {
		return codeMusique;
	}

	public void setCodeMusique(String codeMusique) {
		this.codeMusique = codeMusique;
	}

}
package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "canton")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idCanton")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class CantonEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "cdCanton")
	private String codeCanton;

	@Column(name = "idPays")
	private Integer idPays;

	/***/
	public CantonEntity() {
	}

	public String getCodeCanton() {
		return codeCanton;
	}

	public void setCodeCanton(String codeCanton) {
		this.codeCanton = codeCanton;
	}

	public Integer getIdPays() {
		return idPays;
	}

	public void setIdPays(Integer idPays) {
		this.idPays = idPays;
	}

}
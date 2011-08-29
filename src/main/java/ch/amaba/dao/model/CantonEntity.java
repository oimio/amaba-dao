package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "canton")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idCanton")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class CantonEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "cdCanton")
	private String codeCanton;

	@Column(name = "idPays")
	private Integer idPays;

	/***/
	public CantonEntity() {
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final CantonEntity o) {
			o.setLastModificationDate(new Date());
		}
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
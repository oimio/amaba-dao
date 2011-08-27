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

	public CantonEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final CantonEntity o) {
		System.out.println("---------------------");
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

}
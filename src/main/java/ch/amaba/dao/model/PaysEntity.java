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
@Table(name = "pays")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idPays")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class PaysEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdPays")
	private String codePays;

	public PaysEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final PaysEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final PaysEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

}
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
@Table(name = "sexe")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idSexe")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class SexeEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdSexe")
	private String codeSexe;

	public SexeEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final SexeEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final SexeEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeSexe() {
		return codeSexe;
	}

	public void setCodeSexe(String codeSexe) {
		this.codeSexe = codeSexe;
	}

}
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
@Table(name = "profession")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idProfession")), @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class ProfessionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdProfession")
	private String codeProfession;

	public ProfessionEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idReligiProfessionertable = false, updatable = false)
	// private UserProfessionEntity userProfession;

	@PreUpdate
	@PrePersist
	public void sysout(final ProfessionEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final ProfessionEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeProfession() {
		return codeProfession;
	}

	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}

}
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
@Table(name = "religion")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idReligion")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class ReligionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdReligion")
	private String codeReligion;

	public ReligionEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idReligion", insertable = false, updatable = false)
	// private UserReligionEntity userReligion;

	@PreUpdate
	@PrePersist
	public void sysout(final ReligionEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final ReligionEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeReligion() {
		return codeReligion;
	}

	public void setCodeReligion(String codeReligion) {
		this.codeReligion = codeReligion;
	}

}
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
@Table(name = "preference")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idPreference")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class PreferenceEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdPreference")
	private String codePreference;

	public PreferenceEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idPreference", insertable = false, updatable = false)
	// private UserPreferenceEntity userPreference;

	@PreUpdate
	@PrePersist
	public void sysout(final PreferenceEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final PreferenceEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodePreference() {
		return codePreference;
	}

	public void setCodePreference(String codePreference) {
		this.codePreference = codePreference;
	}

}
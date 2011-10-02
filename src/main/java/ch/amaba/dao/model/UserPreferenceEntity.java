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
@Table(name = "usrPreference")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPreference")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserPreferenceEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserPreferenceEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idUsr", insertable = false, updatable = false)
	// private UserEntity userEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserPreferenceEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserPreferenceEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

}
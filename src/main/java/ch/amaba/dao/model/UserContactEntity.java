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
@Table(name = "contact")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrContact")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserContactEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "txValue")
	private String contactValue;

	public UserContactEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final UserContactEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserContactEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

}
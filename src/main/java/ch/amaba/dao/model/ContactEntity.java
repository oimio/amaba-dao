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
@Table(name = "ContactEntity.java")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idContact")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class ContactEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdContact")
	private String codeContact;

	public ContactEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final ContactEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final ContactEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeContact() {
		return codeContact;
	}

	public void setCodeContact(String codeContact) {
		this.codeContact = codeContact;
	}

}
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
@Table(name = "interet")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idInteret")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class InteretEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdInteret")
	private String codeInteret;

	public InteretEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idInteret", insertable = false, updatable = false)
	// private UserInteretEntity userInteret;

	@PreUpdate
	@PrePersist
	public void sysout(final InteretEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final InteretEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeInteret() {
		return codeInteret;
	}

	public void setCodeInteret(String codeInteret) {
		this.codeInteret = codeInteret;
	}

}
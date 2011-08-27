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
@Table(name = "musique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idMusique")), @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class MusiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdMusique")
	private String codeMusique;

	public MusiqueEntity() {
	}

	// @ManyToOne
	// @JoinColumn(name = "idMusique", insertable = false, updatable = false)
	// private UserMusiqueEntity userMusique;

	@PreUpdate
	@PrePersist
	public void sysout(final MusiqueEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final MusiqueEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeMusique() {
		return codeMusique;
	}

	public void setCodeMusique(String codeMusique) {
		this.codeMusique = codeMusique;
	}

}
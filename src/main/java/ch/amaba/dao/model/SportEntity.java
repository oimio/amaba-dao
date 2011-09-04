package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "sport")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idSport")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class SportEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdSport")
	private String codeSport;

	public SportEntity() {
	}

	@ManyToOne
	@JoinColumn(name = "idSport", insertable = false, updatable = false)
	private UserSportEntity userSport;

	@PreUpdate
	@PrePersist
	public void sysout(final SportEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final SportEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeSport() {
		return codeSport;
	}

	public void setCodeSport(String codeSport) {
		this.codeSport = codeSport;
	}

	public UserSportEntity getUserSport() {
		return userSport;
	}

	public void setUserSport(UserSportEntity userSport) {
		this.userSport = userSport;
	}

}
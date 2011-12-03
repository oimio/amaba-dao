package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "sport")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idSport")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class SportEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdSport")
	private String codeSport;

	public SportEntity() {
	}

	@ManyToOne
	@JoinColumn(name = "idSport", insertable = false, updatable = false)
	private UserSportEntity userSport;

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
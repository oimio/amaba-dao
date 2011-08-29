package ch.amaba.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usr")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsr")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "txUsrNom")
	private String nom;

	@Column(name = "txUsrPrenom")
	private String prenom;

	@Column(name = "txUsrEmail")
	private String email;

	@Column(name = "dtUsrNaissance")
	private String dateNaissance;

	@Column(name = "idSexe")
	private Integer idSexe;

	public UserEntity() {
	}

	@OneToOne
	@JoinColumn(name = "idUsr")
	public UserProfileEntity userProfil;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserSportEntity> userSports;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserReligionEntity> userReligions;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserInteretEntity> userInterets;

	@PreUpdate
	@PrePersist
	public void sysout(final UserEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public UserProfileEntity getUserProfil() {
		return userProfil;
	}

	public void setUserProfil(UserProfileEntity userProfil) {
		this.userProfil = userProfil;
	}

	public Set<UserSportEntity> getUserSports() {
		return userSports;
	}

	public void setUserSports(Set<UserSportEntity> userSports) {
		this.userSports = userSports;
	}

	public Set<UserReligionEntity> getUserReligions() {
		return userReligions;
	}

	public void setUserReligions(Set<UserReligionEntity> userReligions) {
		this.userReligions = userReligions;
	}

	public Set<UserInteretEntity> getUserInterets() {
		return userInterets;
	}

	public void setUserInterets(Set<UserInteretEntity> userInterets) {
		this.userInterets = userInterets;
	}

	public void addInteret(final UserInteretEntity interetEntity) {
		getUserInterets().add(interetEntity);
	}

	public Integer getIdSexe() {
		return idSexe;
	}

	public void setIdSexe(Integer idSexe) {
		this.idSexe = idSexe;
	}

}
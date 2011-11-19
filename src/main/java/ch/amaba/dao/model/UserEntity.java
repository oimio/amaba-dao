package ch.amaba.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsr")),

@AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),

@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),

@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),

@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "txUsrNom")
	private String nom;

	@Column(name = "txUsrPrenom")
	private String prenom;

	@Column(name = "txUsrEmail")
	private String email;

	@Column(name = "dtUsrNaissance")
	private Date dateNaissance;

	@Column(name = "idSexe")
	private Integer idSexe;

	@Column(name = "txPassword")
	private String password;

	/**
	 * Raccourci sur la table statut. new(1), valid(2), block(3), wait(4)
	 */
	@Column(name = "isValid")
	private Integer idValid;

	public UserEntity() {
	}

	@OneToMany(mappedBy = "userEntity")
	public Set<UserProfileEntity> userProfil;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserSportEntity> userSports;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserAdressEntity> userAdresses;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserReligionEntity> userReligions;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserInteretEntity> userInterets;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	public Set<UserContactEntity> userContacts;

	@OneToMany
	@JoinColumn(name = "idUsr", insertable = false, updatable = false)
	private Set<UserPhotoEntity> userPhotos;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsr")
	private Set<UserAmiEntity> userAmis;

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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public Set<UserAdressEntity> getUserAdresses() {
		return userAdresses;
	}

	public void setUserAdresses(Set<UserAdressEntity> userAdresses) {
		this.userAdresses = userAdresses;
	}

	public Set<UserContactEntity> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(Set<UserContactEntity> userContacts) {
		this.userContacts = userContacts;
	}

	public Integer getIdValid() {
		return idValid;
	}

	public void setIdValid(Integer idValid) {
		this.idValid = idValid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserProfileEntity> getUserProfil() {
		return userProfil;
	}

	public void setUserProfil(Set<UserProfileEntity> userProfil) {
		this.userProfil = userProfil;
	}

	/**
	 * Retourne l'id du canton en parcourant les adresses.
	 * 
	 * @return idCanton - l'id du canton.
	 */
	public Integer getIdCanton() {
		Integer idCanton = null;
		final Set<UserAdressEntity> userAdresses = getUserAdresses();
		for (final UserAdressEntity userAdressEntity : userAdresses) {
			idCanton = userAdressEntity.getIdCanton();
			break;
		}
		return idCanton;
	}

	public Set<UserPhotoEntity> getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(Set<UserPhotoEntity> userPhotos) {
		this.userPhotos = userPhotos;
	}

	public Set<UserAmiEntity> getUserAmis() {
		return userAmis;
	}

	public void setUserAmis(Set<UserAmiEntity> userAmis) {
		this.userAmis = userAmis;
	}
}
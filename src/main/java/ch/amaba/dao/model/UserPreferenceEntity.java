package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usrPreference")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrPreference")) })
public class UserPreferenceEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserPreferenceEntity() {
	}

	// @Column(name = "idUsr")
	// private Long idUsr;

	@Column(name = "idPreference")
	private Integer idPreference;

	@Column(name = "txPreferenceValue")
	private String preferenceValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	// public Long getIdUsr() {
	// return idUsr;
	// }
	//
	// public void setIdUsr(Long idUsr) {
	// this.idUsr = idUsr;
	// }

	public String getPreferenceValue() {
		return preferenceValue;
	}

	public void setPreferenceValue(String preferenceValue) {
		this.preferenceValue = preferenceValue;
	}

	public Integer getIdPreference() {
		return idPreference;
	}

	public void setIdPreference(Integer idPreference) {
		this.idPreference = idPreference;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
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

import ch.amaba.model.bo.constants.TypeMusiqueEnum;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrMusique")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrMusique")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserMusiqueEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserMusiqueEntity() {
	}

	@Column(name = "idMusique")
	public TypeMusiqueEnum typeMusiqueEnum;

	@ManyToOne
	@JoinColumn(name = "idUsr")
	private UserEntity userEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final UserMusiqueEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserMusiqueEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public TypeMusiqueEnum getTypeMusiqueEnum() {
		return typeMusiqueEnum;
	}

	public void setTypeMusiqueEnum(TypeMusiqueEnum typeMusiqueEnum) {
		this.typeMusiqueEnum = typeMusiqueEnum;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
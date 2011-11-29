package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usrReligion")
@AttributeOverrides({

@AttributeOverride(name = "entityId", column = @Column(name = "idUsrReligion")),

@AttributeOverride(name = "idLink", column = @Column(name = "idReligion")),

})
public class UserReligionEntity extends UserLinkEntity {

	private static final long serialVersionUID = 1L;

	public UserReligionEntity() {
	}

}
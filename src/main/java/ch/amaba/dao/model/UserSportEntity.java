package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usrSport")
@AttributeOverrides({

@AttributeOverride(name = "entityId", column = @Column(name = "idUsrSport")),

@AttributeOverride(name = "idLink", column = @Column(name = "idSport")) })
public class UserSportEntity extends UserLinkEntity {

	private static final long serialVersionUID = 1L;

	public UserSportEntity() {
	}
}
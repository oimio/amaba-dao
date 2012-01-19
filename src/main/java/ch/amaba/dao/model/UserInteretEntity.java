package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usrInteret")
@AttributeOverrides({

@AttributeOverride(name = "entityId", column = @Column(name = "idUsrInteret")),

@AttributeOverride(name = "idLink", column = @Column(name = "idInteret")) })
public class UserInteretEntity extends UserLinkEntity {

	private static final long serialVersionUID = 1L;

	public UserInteretEntity() {
	}
}
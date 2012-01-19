package ch.amaba.dao.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usrMusique")
@AttributeOverrides({

@AttributeOverride(name = "entityId", column = @Column(name = "idUsrMusique")),

@AttributeOverride(name = "idLink", column = @Column(name = "idMusique")) })
public class UserMusiqueEntity extends UserLinkEntity {

	private static final long serialVersionUID = 1L;

	public UserMusiqueEntity() {
	}
}
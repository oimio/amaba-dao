package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "usrAdress")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrAdress")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class UserAdressEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	public UserAdressEntity() {
	}

	@PreUpdate
	@PrePersist
	public void sysout(final UserAdressEntity o) {
		System.out.println("---------------------");
	}

	@Column(name = "idUsr")
	public Long idUser;

	@Column(name = "idCanton")
	public Integer idCanton;

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final UserAdressEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Integer getIdCanton() {
		return idCanton;
	}

	public void setIdCanton(Integer idCanton) {
		this.idCanton = idCanton;
	}

}
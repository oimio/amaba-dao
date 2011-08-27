package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultEntity {

	@Id
	@GeneratedValue
	private Long entityId;

	@Column(name = "ohoptlck")
	private Long version;

	@Column(name = "OHDATMOD")
	private Date lastModificationDate;

	@Column(name = "OHDATCRE")
	private Date creationDate;

	@Column(name = "OHSTATUT")
	private String statut;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DefaultEntity other = (DefaultEntity) obj;
		if (creationDate == null) {
			if (other.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(other.creationDate)) {
			return false;
		}
		if (entityId == null) {
			if (other.entityId != null) {
				return false;
			}
		} else if (!entityId.equals(other.entityId)) {
			return false;
		}
		if (lastModificationDate == null) {
			if (other.lastModificationDate != null) {
				return false;
			}
		} else if (!lastModificationDate.equals(other.lastModificationDate)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}

	/**
	 * @return Returns the entityId.
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * @return Returns the version.
	 */
	public Long getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((lastModificationDate == null) ? 0 : lastModificationDate.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	/**
	 * @param entityId
	 *          The entityId to set.
	 */
	public void setEntityId(final Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * @param version
	 *          The version to set.
	 */
	public void setVersion(final Long version) {
		this.version = version;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(final Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
}

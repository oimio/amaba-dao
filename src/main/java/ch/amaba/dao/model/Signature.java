package ch.amaba.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@MappedSuperclass
public class Signature implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	/**
	 * date de création de l'entité parente
	 */
	private Date creationDate;
	/**
	 * utilisateur ayant créé l'entité partente
	 */
	private String creationUser;
	/**
	 * application ayant crée l'entité parente
	 */
	private String creationApplication;
	/**
	 * dernière date de modification de l'entité parente
	 */

	@Column(name = "ohdatmod")
	private Date lastModificationDate;
	/**
	 * dernière personne ayant modifié l'entité parente
	 */
	private String lastModificationUser;
	/**
	 * dernière application ayant modifié l'entité partente
	 */
	private String lastModificationApplication;
	/**
	 * status de l'entité partente
	 */
	private String status;

	/**
	 * 
	 */
	public Signature() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean isEqual = true;
		if (obj == null) {
			isEqual = false;
		}
		if (obj instanceof Signature == false) {
			isEqual = false;
		}
		if (this == obj) {
			isEqual = true;
		}
		final Signature rhs = (Signature) obj;
		// appendSuper(super.equals(obj))
		isEqual = new EqualsBuilder()
		    .append(creationDate, rhs.getCreationDate())
		    .append(creationUser, rhs.getCreationUser())
		    .append(creationApplication, rhs.getCreationApplication())
		    .append(lastModificationDate, rhs.getLastModificationDate())
		    .append(lastModificationUser, rhs.getLastModificationUser())
		    .append(lastModificationApplication,
		        rhs.getLastModificationApplication())
		    .append(status, rhs.getStatus()).isEquals();
		return isEqual;
	}

	/**
	 * @return Returns the creationApplication.
	 */
	public String getCreationApplication() {
		return creationApplication;
	}

	/**
	 * @return Returns the creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Returns the creationUser.
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * @return Returns the lastModificationApplication.
	 */
	public String getLastModificationApplication() {
		return lastModificationApplication;
	}

	/**
	 * @return Returns the lastModificationDate.
	 */
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @return Returns the lastModificationUser.
	 */
	public String getLastModificationUser() {
		return lastModificationUser;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(creationDate)
		    .append(creationUser).append(creationApplication)
		    .append(lastModificationDate).append(lastModificationUser)
		    .append(lastModificationApplication).toHashCode();
	}

	/**
	 * @param creationApplication
	 *          The creationApplication to set.
	 */
	public void setCreationApplication(final String creationApplication) {
		this.creationApplication = creationApplication;
	}

	/**
	 * @param creationDate
	 *          The creationDate to set.
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param creationUser
	 *          The creationUser to set.
	 */
	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 * @param lastModificationApplication
	 *          The lastModificationApplication to set.
	 */
	public void setLastModificationApplication(
	    final String lastModificationApplication) {
		this.lastModificationApplication = lastModificationApplication;
	}

	/**
	 * @param lastModificationDate
	 *          The lastModificationDate to set.
	 */
	public void setLastModificationDate(final Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @param lastModificationUser
	 *          The lastModificationUser to set.
	 */
	public void setLastModificationUser(final String lastModificationUser) {
		this.lastModificationUser = lastModificationUser;
	}

	/**
	 * @param status
	 *          The status to set.
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
}

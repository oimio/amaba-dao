package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ch.amaba.model.bo.constants.TypeMessageStatutEnum;

@Entity

@Table(name = "usrMessageStatut")
@AttributeOverrides({ @AttributeOverride(name = "entityId", column = @Column(name = "idUsrMessageStatut")),
    @AttributeOverride(name = "dateModification", column = @Column(name = "DTE_MOD")),@AttributeOverride(name = "dateCreation", column = @Column(name = "DTE_CRE")),@AttributeOverride(name = "statut", column = @Column(name = "STATUT")),@AttributeOverride(name = "version", column = @Column(name = "VERSION")) })
public class UserMessageStatutEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "dtStatut")
	private Date dateStatut;

	/**
	 * Le user concerné par le statut. Pour un même idMessage :
	 * 
	 * - il peut être le senderId
	 * 
	 * - ou le receiverId
	 * */
	@Column(name = "idUsr")
	private Long idUser;

	/** Id du message. */
	@Column(name = "idMessage")
	private Long idMessage;

	public UserMessageStatutEntity() {
	}

	@Column(name = "idMessageStatut")
	public Integer idMessageStatut;

	public Date getDateStatut() {
		return dateStatut;
	}

	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public Integer getIdMessageStatut() {
		return idMessageStatut;
	}

	public void setIdMessageStatut(Integer idMessageStatut) {
		this.idMessageStatut = idMessageStatut;
	}

	/**
	 * Methode utile pour gérer le set avec un enum en parametre.
	 * 
	 * @param typeMessageStatutEnum
	 */
	public void setTypeMessageStatutEnum(final TypeMessageStatutEnum typeMessageStatutEnum) {
		setIdMessageStatut(typeMessageStatutEnum.getId());
	}
}
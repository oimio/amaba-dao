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

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "propertyCode")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idPropertyCode")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class PropertyCodeEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdPropertyCode")
	private String codeProperty;

	@Column(name = "txDescription")
	private String description;

	public PropertyCodeEntity() {
	}

	@ManyToOne
	@JoinColumn(name = "idPropDef", insertable = false, updatable = false)
	private PropertyDefinitionEntity propertyDefinitionEntity;

	@PreUpdate
	@PrePersist
	public void sysout(final PropertyCodeEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final PropertyCodeEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeProperty() {
		return codeProperty;
	}

	public void setCodeProperty(String codeProperty) {
		this.codeProperty = codeProperty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PropertyDefinitionEntity getPropertyDefinitionEntity() {
		return propertyDefinitionEntity;
	}

	public void setPropertyDefinitionEntity(
	    PropertyDefinitionEntity propertyDefinitionEntity) {
		this.propertyDefinitionEntity = propertyDefinitionEntity;
	}

}
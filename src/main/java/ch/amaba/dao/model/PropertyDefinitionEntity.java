package ch.amaba.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@EntityListeners({ LastUpdateListener.class })
@Table(name = "propertyDefinition")
@AttributeOverrides({
    @AttributeOverride(name = "entityId", column = @Column(name = "idPropDef")),
    @AttributeOverride(name = "lastModificationDate", column = @Column(name = "ohdatmod")) })
public class PropertyDefinitionEntity extends DefaultEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "cdPropDef")
	private String codeProperty;

	public PropertyDefinitionEntity() {
	}

	@OneToMany
	@JoinColumn(name = "idPropDef")
	public Set<PropertyCodeEntity> propertyValues;

	@PreUpdate
	@PrePersist
	public void sysout(final PropertyDefinitionEntity o) {
		System.out.println("---------------------");
	}

	public class LastUpdateListener {
		/**
		 * automatic property set before any database persistence
		 */
		@PreUpdate
		@PrePersist
		public void setLastUpdate(final PropertyDefinitionEntity o) {
			o.setLastModificationDate(new Date());
		}
	}

	public String getCodeProperty() {
		return codeProperty;
	}

	public void setCodeProperty(String codeProperty) {
		this.codeProperty = codeProperty;
	}

	public Set<PropertyCodeEntity> getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(Set<PropertyCodeEntity> propertyValues) {
		this.propertyValues = propertyValues;
	}

}
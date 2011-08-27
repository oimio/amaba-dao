package ch.amaba.dao.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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

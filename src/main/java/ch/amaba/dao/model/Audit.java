package ch.amaba.dao.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class Audit extends EmptyInterceptor {

	/**
   * 
   */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(final Object entity, final Serializable id, final Object[] state, final String[] propertyNames, final Type[] types) {

		for (int i = 0; i < propertyNames.length; i++) {
			if ("lastModificationDate".equals(propertyNames[i])) {
				state[i] = new Date();
				return true;
			} else if ("creationDate".equals(propertyNames[i])) {
				state[i] = new Date();
				return true;
			}
		}
		// ((DefaultEntity) entity).setLastModificationDate(new Date());
		return false;
	}

}

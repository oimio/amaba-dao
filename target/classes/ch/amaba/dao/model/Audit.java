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

	/**
	 * Retourne vrai si au moins une valeur doit être mise à jour.
	 * */
	@Override
	public boolean onSave(final Object entity, final Serializable id, final Object[] state, final String[] propertyNames, final Type[] types) {
		boolean substitute = false;
		for (int i = 0; i < propertyNames.length; i++) {
			if ("dateModification".equals(propertyNames[i])) {
				state[i] = new Date();
				substitute = true;
			} else if ("dateCreation".equals(propertyNames[i])) {
				state[i] = new Date();
				substitute = true;
			} else if ("statut".equals(propertyNames[i])) {
				state[i] = DefaultEntity.ENTITY_ACTIVE_STATE;
				substitute = true;
			} else if ("version".equals(propertyNames[i])) {
				state[i] = Long.valueOf(0);
				substitute = true;
			}
		}
		return substitute;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		boolean substitute = false;
		for (int i = 0; i < propertyNames.length; i++) {
			if ("dateModification".equals(propertyNames[i])) {
				currentState[i] = new Date();
				substitute = true;
			} else if ("version".equals(propertyNames[i])) {
				Long version = ((DefaultEntity) entity).getVersion();
				if (version == null) {
					version = Long.valueOf(0);
				}
				currentState[i] = Long.valueOf(version + 1);
				substitute = true;
			}
		}
		return substitute;
	}

}

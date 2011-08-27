package ch.amaba.dao.model;

public interface ISignedEntity {

	public final static String ENTITY_ACTIVE_STATE = "A";
	public final static String ENTITY_DELETED_STATE = "D";

	/**
	 * retourne un objet de type signature
	 * 
	 * @return
	 */
	public Signature getSignature();

	/**
	 * place un objet de type signature
	 * 
	 * @param signature
	 */
	public void setSignature(Signature signature);
}

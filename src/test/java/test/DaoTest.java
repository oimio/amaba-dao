package test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.transaction.annotation.Transactional;

import ch.amaba.dao.model.UserAdressEntity;
import ch.amaba.dao.model.UserAmiEntity;
import ch.amaba.dao.model.UserEntity;
import ch.amaba.model.bo.AmiDTO;
import ch.amaba.model.bo.PhotoDTO;
import ch.amaba.model.bo.PhysiqueCriteria;
import ch.amaba.model.bo.ProfileCriteria;
import ch.amaba.model.bo.UserCriteria;
import ch.amaba.model.bo.constants.TypeCouleurCheveux;
import ch.amaba.model.bo.constants.TypeCouleurYeux;
import ch.amaba.model.bo.constants.TypeMessageStatutEnum;
import ch.amaba.model.bo.exception.EntityNotFoundException;

/**
 * Unit test for simple App.
 */
public class DaoTest extends AbstractDaoTest {
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DaoTest.class);
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *          name of the test case
	 */
	public DaoTest(final String testName) {
		super(testName);
	}

	public void testAjouterFavori() {
		try {

			// dao.ajouterFavori(1L, 2L);
			// dao.ajouterFavori(1L, 2L);
			dao.ajouterFavori(2L, 1L);

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test la modification d'une entit�. Les champs version (+1) et
	 * dateModification (new Date()) doivent �tre mis � jour.
	 */
	@Transactional
	public void testModifier() {
		UserEntity load = (UserEntity) dao.get(UserEntity.class, 1L);
		load.setPrenom("Bobby");
		dao.saveOrUpdate(load);
		load = (UserEntity) dao.get(UserEntity.class, 1L);
		Assert.assertEquals("Bobby", load.getPrenom());
	}

	@Transactional
	public void testSupprimer() {
		// Creer un nouvel ami puis le supprimer
		final UserEntity ue = new UserEntity();
		ue.setEntityId(3L);
		final UserAmiEntity ami = new UserAmiEntity();
		ami.setEntityId(1L);
		ami.setUserEntity(ue);
		final int amiId = RandomUtils.nextInt();
		ami.setIdAmi(new Long(amiId));
		dao.save(ami);
		try {
			Thread.sleep(1000);
		} catch (final InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dao.supprimer(ami);
		} catch (final EntityNotFoundException e) {
			Assert.assertEquals(true, false);
		}

	}

	/**
	 * Rigourous Test :-)
	 */
	public void testChangerStatutMessage() {
		dao.changerMessagesStatut(new HashSet<Long>(Arrays.asList(1L)), 2L, TypeMessageStatutEnum.LU);
	}

	public void testCreerAdress() {
		final UserAdressEntity userAdressEntity = new UserAdressEntity();
		userAdressEntity.setIdCanton(27);
		userAdressEntity.setIdUser(3L);
		dao.save(userAdressEntity);
		Assert.assertNotNull(userAdressEntity.getDateModification());
	}

	public void testGetUser() {
		final UserEntity load = (UserEntity) dao.get(UserEntity.class, 1L);
		Assert.assertNotNull(load);
		Assert.assertEquals("Gomes", load.getNom());
	}

	public void testListeFavoris() {
		final Set<AmiDTO> listeFavoris = dao.findAmis(3L);
		for (final AmiDTO userCriteria : listeFavoris) {
			System.out.println(userCriteria.getBusinessObjectId());
		}
		Assert.assertNotNull(listeFavoris);
		Assert.assertEquals(1, listeFavoris.size());
	}

	public void testLoadFullUserData() {
		final UserCriteria user = new UserCriteria();
		user.setIdUser(3L);
		final UserCriteria loadFullUserData = dao.loadFullUserData(user);
		final Set<Integer> idMusiques = loadFullUserData.getIdMusiques();
		final Set<Integer> idProfessions = loadFullUserData.getIdProfessions();
		Assert.assertEquals(3, idMusiques.size());
		Assert.assertEquals(1, idProfessions.size());
	}

	@Transactional
	public void testModifierMusic() {
		final Set<Integer> ids = new HashSet<Integer>();
		// Avant : 1,3,10
		ids.add(4);
		ids.add(5);
		ids.add(6);
		dao.modifierMusics(3L, ids);
	}

	public void testAjouterPhotos() {
		final String[] photos = { "test1.jpg", "toto_titi.png", "été.bmp" };
		dao.savePhotos(Long.valueOf(3L), photos);
	}

	public void testLoadPhotos() {
		final Set<PhotoDTO> photosByUser = dao.loadPhotosByUser(3L);
		Assert.assertNotNull(photosByUser);
		for (final PhotoDTO photoDTO : photosByUser) {
			System.out.println(photoDTO);
		}
	}

	public void testFlagPhotoPrincipale() {
		dao.flagPhotoPrincipale(3L, 17L);
	}

	public void testFindUserBycriteria() {
		final UserCriteria userCriteria = new UserCriteria();
		// userCriteria.addInteret(TypeInteretEnum.CINE.getId());
		final PhysiqueCriteria physiqueCriteria = new PhysiqueCriteria();
		physiqueCriteria.addCouleurCheveux(TypeCouleurCheveux.BRUN);
		userCriteria.setPhysiqueCriteria(physiqueCriteria);

		final Set<UserEntity> findUserBycriteria = dao.findUserBycriteria(userCriteria);
		Assert.assertNotNull(findUserBycriteria);

		for (final UserEntity userEntity : findUserBycriteria) {
			System.out.println(userEntity.getNom() + " " + userEntity.getPrenom());
		}
	}

	public void testAjouterUserPhysique() {
		final UserEntity userEntity = new UserEntity();
		userEntity.setNom("test nom");
		userEntity.setPrenom("test prenom");
		userEntity.setEmail("test email" + RandomUtils.nextInt());
		userEntity.setDateNaissance(new Date());
		userEntity.setIdSexe(1);
		userEntity.setIdValid(0);
		userEntity.setPassword("****");
		dao.save(userEntity);
		System.out.println(userEntity.getEntityId());

		final PhysiqueCriteria physiqueCriteria = new PhysiqueCriteria();
		physiqueCriteria.setTailleMin(171);
		physiqueCriteria.setPoidsMin(72);
		physiqueCriteria.addCouleurCheveux(TypeCouleurCheveux.BRUN);
		physiqueCriteria.addCouleurYeux(TypeCouleurYeux.NOIR);
		dao.ajouterUserPhysique(userEntity.getEntityId(), physiqueCriteria);
	}

	public void testAjouterUserProfil() {
		final UserEntity userEntity = new UserEntity();
		userEntity.setNom("test nom");
		userEntity.setPrenom("test prenom");
		userEntity.setEmail("test email" + RandomUtils.nextInt());
		userEntity.setDateNaissance(new Date());
		userEntity.setIdSexe(1);
		userEntity.setIdValid(0);
		userEntity.setPassword("****");
		dao.save(userEntity);
		System.out.println(userEntity.getEntityId());

		final ProfileCriteria profileCriteria = new ProfileCriteria();
		profileCriteria.setDivorce(Short.valueOf("1"));
		profileCriteria.setGenre(Short.valueOf("1"));
		profileCriteria.setMarie(Short.valueOf("1"));
		profileCriteria.setNombreEnfant(Short.valueOf("5"));
		profileCriteria.setRechercheRelationSerieuse(Short.valueOf("1"));
		profileCriteria.setVeuf(Short.valueOf("1"));
		dao.ajouterUserProfil(userEntity.getEntityId(), profileCriteria);
	}

}

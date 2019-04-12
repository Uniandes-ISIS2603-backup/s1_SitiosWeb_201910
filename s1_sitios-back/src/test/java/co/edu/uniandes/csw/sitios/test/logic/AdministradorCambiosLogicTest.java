/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.AdministradorCambiosLogic;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@RunWith(Arquillian.class)
public class AdministradorCambiosLogicTest {

    private static final Logger LOGGER = Logger.getLogger(AdministradorCambiosLogicTest.class.getName());

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CambioLogic cambioLogic;

    @Inject
    AdministradorCambiosLogic aCLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AdministradorEntity> dataA = new ArrayList<>();

    private List<CambioEntity> dataC = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(CambioEntity.class.getPackage())
                .addPackage(AdministradorCambiosLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addPackage(CambioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from CambioEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entityA = factory.manufacturePojo(AdministradorEntity.class);
            entityA.setCambios(new ArrayList<CambioEntity>());
            em.persist(entityA);
            dataA.add(entityA);
        }

        for (int i = 0; i < 3; i++) {
            CambioEntity entityC = factory.manufacturePojo(CambioEntity.class);

            em.persist(entityC);
            dataC.add(entityC);
            dataA.get(0).getCambios().add(entityC);
        }
    }

    /**
     * Prueba para crear un Administrador.
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void addCambioTest() throws BusinessLogicException {

        CambioEntity newEntity = factory.manufacturePojo(CambioEntity.class);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los cambios del admin con id = {0}", cambioLogic);

        newEntity.setSitioWeb(new SitioWebEntity());
        CambioEntity result = cambioLogic.createCambio(newEntity);
        CambioEntity entity = em.find(CambioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaCambio(), entity.getFechaCambio());
        Assert.assertEquals(newEntity.getLugarCambio(), entity.getLugarCambio());
        Assert.assertEquals(newEntity.getNuevo(), entity.getNuevo());
        Assert.assertEquals(newEntity.getPrevio(), entity.getPrevio());
    }

    /**
     * Prueba para consultar la lista de cambios de un administrador.
     */
    @Test
    public void getCambiosTest() {

        List<CambioEntity> list = aCLogic.getCambios(dataA.get(0).getId());

        Assert.assertEquals(dataA.get(0).getCambios().size(), list.size());
        for (int i = 0; i < dataA.size(); i++) {
            Assert.assertTrue(list.contains(dataA.get(0).getCambios().get(i)));
        }
    }

    /**
     * Prueba para consultar una cambio de un admin.
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void getCambioTest() throws BusinessLogicException {

        CambioEntity cambioE = dataC.get(0);
        AdministradorEntity adminE = dataA.get(0);
        CambioEntity resultCambio = aCLogic.getCambio(adminE.getId(), cambioE.getId());
        Assert.assertNotNull(resultCambio);

        Assert.assertEquals(cambioE.getId(), resultCambio.getId());
        Assert.assertEquals(cambioE.getDescripcion(), resultCambio.getDescripcion());
        Assert.assertEquals(cambioE.getFechaCambio(), resultCambio.getFechaCambio());
        Assert.assertEquals(cambioE.getIdAsociado(), resultCambio.getIdAsociado());
        Assert.assertEquals(cambioE.getLugarCambio(), resultCambio.getLugarCambio());
        Assert.assertEquals(cambioE.getNuevo(), resultCambio.getNuevo());
        Assert.assertEquals(cambioE.getPrevio(), resultCambio.getPrevio());
    }

//    /**
//     * Prueba para actualizar las notificaciones de un admin.
//     *
//     */
//    @Test
//    public void replaceCambioTest() {
//        List<CambioEntity> nuevaLista = new ArrayList<>();
//        AdministradorEntity adminE = dataA.get(0);
//        for (int i = 0; i < 3; i++) {
//            CambioEntity entity = factory.manufacturePojo(CambioEntity.class);
//            nuevaLista.add(entity);
//        }
//        LOGGER.log(Level.INFO, "HOLAAA= {0}", aCLogic);
//
//        aCLogic.replaceCambios(adminE.getId(), nuevaLista);
//        List<CambioEntity> cambioEntities = aCLogic.getCambios(adminE.getId());
//        for (CambioEntity aNuevaLista : nuevaLista) {
//            Assert.assertTrue(cambioEntities.contains(aNuevaLista));
//        }
//    }

    /**
     * Prueba desasociar una notificacion con un administrador.
     *
     */
    @Test
    public void removeCambioTest() {
        for (CambioEntity noti : dataC) {
            aCLogic.removeCambio(dataA.get(0).getId(), noti.getId());
        }
        Assert.assertTrue(aCLogic.getCambios(dataA.get(0).getId()).isEmpty());
    }

}

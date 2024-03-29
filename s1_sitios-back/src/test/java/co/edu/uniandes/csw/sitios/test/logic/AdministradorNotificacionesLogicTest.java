/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.AdministradorNotificacionesLogic;
import co.edu.uniandes.csw.sitios.ejb.NotificacionLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;
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
public class AdministradorNotificacionesLogicTest {

    private static final Logger LOGGER = Logger.getLogger(AdministradorNotificacionesLogicTest.class.getName());

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private NotificacionLogic notiLogic;

    @Inject
    AdministradorNotificacionesLogic aNLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AdministradorEntity> dataA = new ArrayList<>();

    private List<NotificacionEntity> dataN = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(AdministradorNotificacionesLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
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
            entityA.setNotificaciones(new ArrayList<NotificacionEntity>());
            em.persist(entityA);
            dataA.add(entityA);
        }

        for (int i = 0; i < 3; i++) {
            NotificacionEntity entityN = factory.manufacturePojo(NotificacionEntity.class);

            em.persist(entityN);
            dataN.add(entityN);
            dataA.get(0).getNotificaciones().add(entityN);
        }
    }

    /**
     * Prueba para asociar una notificacion a una persona.
     *
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void addNotificacionTest() throws BusinessLogicException {
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los cambios del admin con id = {0}", notiLogic);

        newEntity.setNotificado(new AdministradorEntity());
        newEntity.setCambioSitio(new EstadoWebEntity());
        newEntity.setSitioWeb(new SitioWebEntity());
        NotificacionEntity result = notiLogic.createNotification(newEntity);
        NotificacionEntity entity = em.find(NotificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNotificado(), entity.getNotificado());
        Assert.assertEquals(newEntity.getSitioWeb(), entity.getSitioWeb());
        Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
    }

    /**
     * Prueba para consultar la lista de Notificacion de un admin.
     */
    @Test
    public void getNotificacionesTest() {
    }

    /**
     * Prueba para cpnsultar una notificacion de un admin.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getNotificacionTest() {
    }

    /**
     * Prueba para actualizar las notificaciones de un admin.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
//    @Test
//    public void replaceNotificacionTest() throws BusinessLogicException {
//        List<NotificacionEntity> nuevaLista = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
//            entity.setCambioSitio(estado);
//            entity.setSitioWeb(sitio);
//            entity.setNotificado(admin);
//            notiLogic.createNotification(entity);
//            nuevaLista.add(entity);
//        }
//        administradorNotificacionesLogic.replaceNotificaciones(admin.getId(), nuevaLista);
//        List<NotificacionEntity> notiEntities = administradorNotificacionesLogic.getNotificaciones(admin.getId());
//        for (NotificacionEntity aNuevaLista : nuevaLista) {
//            Assert.assertTrue(notiEntities.contains(aNuevaLista));
//        }
//    }

    /**
     * Prueba desasociar una notificacion con un administrador.
     *
     */
    @Test
    public void removeNotificacionTest() {
    }

}

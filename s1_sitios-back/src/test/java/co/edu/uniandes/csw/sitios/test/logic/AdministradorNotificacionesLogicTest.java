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
import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
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
    private AdministradorNotificacionesLogic administradorNotificacionesLogic;

    @Inject
    private NotificacionLogic notiLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private AdministradorEntity admin = new AdministradorEntity();
    private EstadoWebEntity estado = new EstadoWebEntity();
    private SitioWebEntity sitio = new SitioWebEntity();
    private PersonaEntity notificado = new AdministradorEntity();
    private List<NotificacionEntity> data = new ArrayList<>();

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
        em.createQuery("delete from NotificacionEntity").executeUpdate();
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        sitio = factory.manufacturePojo(SitioWebEntity.class);
        em.persist(sitio);
        
        estado = factory.manufacturePojo(EstadoWebEntity.class);
        em.persist(estado);
        
        notificado = factory.manufacturePojo(AdministradorEntity.class);
        em.persist(notificado);

        admin = factory.manufacturePojo(AdministradorEntity.class);
        admin.setId(1L);
        admin.setNotificaciones(data);
        em.persist(admin);
        LOGGER.log(Level.INFO, "PRUEBA DENTRO DE INSERT_DATA id = {0}", admin.getId());

    }

    /**
     * Prueba para asociar una notificacion a una persona.
     *
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void addNotificacionTest() throws BusinessLogicException {
        NotificacionEntity newNoti = factory.manufacturePojo(NotificacionEntity.class);
        newNoti.setSitioWeb(sitio);
        newNoti.setNotificado(notificado);
        newNoti.setCambioSitio(estado);
        notiLogic.createNotification(newNoti);
        NotificacionEntity notiEntity = administradorNotificacionesLogic.addNotificacion(admin.getId(), newNoti.getId());
        Assert.assertNotNull(notiEntity);
        Assert.assertEquals(notiEntity.getId(), newNoti.getId());
        Assert.assertEquals(notiEntity.getCambioSitio(), newNoti.getCambioSitio());
        Assert.assertEquals(notiEntity.getNotificado(), newNoti.getNotificado());
        Assert.assertEquals(notiEntity.getSitioWeb(), newNoti.getSitioWeb());

        NotificacionEntity lastNoti = administradorNotificacionesLogic.getNotificacion(admin.getId(), newNoti.getId());

        Assert.assertEquals(lastNoti.getId(), newNoti.getId());
        Assert.assertEquals(lastNoti.getId(), newNoti.getId());
        Assert.assertEquals(lastNoti.getCambioSitio(), newNoti.getCambioSitio());
        Assert.assertEquals(lastNoti.getNotificado(), newNoti.getNotificado());
        Assert.assertEquals(lastNoti.getSitioWeb(), newNoti.getSitioWeb());
    }

    /**
     * Prueba para consultar la lista de Notificacion de un admin.
     */
    @Test
    public void getNotificacionesTest() {
        LOGGER.log(Level.INFO, "Empieza proceso de consultar la notificacion con id = {0}", admin.getId());
        List<NotificacionEntity> notiEntities = administradorNotificacionesLogic.getNotificaciones(admin.getId());

        Assert.assertEquals(data.size(), notiEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(notiEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar una notificacion de un admin.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getNotificacionTest() throws BusinessLogicException {
        NotificacionEntity notiEntity = data.get(0);
        NotificacionEntity noti = administradorNotificacionesLogic.getNotificacion(admin.getId(), notiEntity.getId());
        Assert.assertNotNull(noti);

        Assert.assertEquals(notiEntity.getId(), noti.getId());
        Assert.assertEquals(notiEntity.getCambioSitio(), noti.getCambioSitio());
        Assert.assertEquals(notiEntity.getNotificado(), noti.getNotificado());
        Assert.assertEquals(notiEntity.getSitioWeb(), noti.getSitioWeb());
    }

    /**
     * Prueba para actualizar las notificaciones de un admin.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceNotificacionTest() throws BusinessLogicException {
        List<NotificacionEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
            entity.setCambioSitio(estado);
            entity.setSitioWeb(sitio);
            entity.setNotificado(admin);
            notiLogic.createNotification(entity);
            nuevaLista.add(entity);
        }
        administradorNotificacionesLogic.replaceNotificaciones(admin.getId(), nuevaLista);
        List<NotificacionEntity> notiEntities = administradorNotificacionesLogic.getNotificaciones(admin.getId());
        for (NotificacionEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(notiEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar una notificacion con un administrador.
     *
     */
    @Test
    public void removeNotificacionTest() {
        for (NotificacionEntity noti : data) {
            administradorNotificacionesLogic.removeNotificacion(admin.getId(), noti.getId());
        }
        Assert.assertTrue(administradorNotificacionesLogic.getNotificaciones(admin.getId()).isEmpty());
    }

}

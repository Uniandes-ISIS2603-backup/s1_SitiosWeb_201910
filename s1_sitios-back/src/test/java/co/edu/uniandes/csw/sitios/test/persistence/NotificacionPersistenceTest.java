/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author aa.molano
 */
@RunWith(Arquillian.class)
public class NotificacionPersistenceTest {
    
    @Inject
    NotificacionPersistence persistence;
    
     @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
       private List<NotificacionEntity> data = new ArrayList<NotificacionEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    } 
    
     @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from NotificacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createNotificationTest() {
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        NotificacionEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        NotificacionEntity entity = em.find(NotificacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
    }
    
    @Test
    public void getNotificationsTest() {
        List<NotificacionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (NotificacionEntity ent : list) {
            boolean found = false;
            for (NotificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getNotificationTest() {
        NotificacionEntity entity = data.get(0);
        NotificacionEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
        Assert.assertEquals(newEntity.getNotificado(), entity.getNotificado());
        Assert.assertEquals(newEntity.getSitioWeb() , entity.getSitioWeb());
    }
    
     @Test
    public void deleteNotificationTest() {
        NotificacionEntity entity = data.get(0);
        persistence.delete(entity.getId());
        NotificacionEntity deleted = em.find(NotificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     @Test
    public void updateNotificationTest() {
        NotificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        NotificacionEntity resp = em.find(NotificacionEntity.class, entity.getId());

     Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
        Assert.assertEquals(newEntity.getNotificado(), entity.getNotificado());
        Assert.assertEquals(newEntity.getSitioWeb() , entity.getSitioWeb());
        
    }
}

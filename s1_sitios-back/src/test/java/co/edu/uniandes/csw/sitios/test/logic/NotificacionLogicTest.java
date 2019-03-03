/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.NotificacionLogic;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
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
public class NotificacionLogicTest {
    
    
    private PodamFactory factory = new PodamFactoryImpl();
      
    @Inject
    private NotificacionLogic logic;
      
      
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<NotificacionEntity> data = new ArrayList<NotificacionEntity>();
    
    private List<SitioWebEntity> sitesData= new ArrayList<SitioWebEntity>();
    
    private List<PersonaEntity> peopleData= new ArrayList<PersonaEntity>();
    
    private List<EstadoWebEntity> stateData= new ArrayList<EstadoWebEntity>();
    
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(NotificacionLogic.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
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
    
    private void clearData() {
        em.createQuery("delete from NotificacionEntity").executeUpdate();
        em.createQuery("delete from PersonaEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PersonaEntity persona = factory.manufacturePojo(PersonaEntity.class);
            em.persist(persona);
            peopleData.add(persona);
        }
         for (int i = 0; i < 3; i++) {
            SitioWebEntity site = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(site);
            sitesData.add(site);
        }
          for (int i = 0; i < 3; i++) {
            EstadoWebEntity state = factory.manufacturePojo(EstadoWebEntity.class);
            em.persist(state);
            stateData.add(state);
        }
        for (int i = 0; i < 3; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
            entity.setSitioWeb(sitesData.get(0));
            entity.setNotificado(peopleData.get(0));
            entity.setCambioSitio(stateData.get(0));
            em.persist(entity);
            data.add(entity);
        }
    }
    
     @Test
    public void createNotificationTest() throws BusinessLogicException {
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setNotificado(peopleData.get(0));
        newEntity.setSitioWeb(sitesData.get(0));
        newEntity.setCambioSitio(stateData.get(0));
        NotificacionEntity result = logic.createNotification(newEntity);
        Assert.assertNotNull(result);
        NotificacionEntity entity = em.find(NotificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
        Assert.assertEquals(newEntity.getNotificado(), entity.getNotificado());
        Assert.assertEquals(newEntity.getSitioWeb() , entity.getSitioWeb());
    }
    
    
    @Test
    public void getBooksTest() {
        try{
        List<NotificacionEntity> list = logic.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (NotificacionEntity entity : list) {
            boolean found = false;
            for (NotificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    catch(BusinessLogicException e)
    {
    e.printStackTrace();
    }
    }  
    
}

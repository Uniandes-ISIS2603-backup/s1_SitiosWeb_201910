/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.NotificacionLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
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
    
    private List<NotificacionEntity> data = new ArrayList<>();
    
    private List<SitioWebEntity> sitesData= new ArrayList<>();
    
    private List<AdministradorEntity> peopleData= new ArrayList<>();
    
    private List<EstadoWebEntity> stateData= new ArrayList<>();
    
    
    
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
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AdministradorEntity persona = factory.manufacturePojo(AdministradorEntity.class);
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
    public void createNotificationOKTest(){
        try{
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
        catch(BusinessLogicException e)
        {
          Assert.fail("no deberia generar error");
        }
    }
    @Test (expected = BusinessLogicException.class)
    public void createNotificationError1Test()throws BusinessLogicException {
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setNotificado(null);
        newEntity.setSitioWeb(sitesData.get(0));
        newEntity.setCambioSitio(stateData.get(0));
        NotificacionEntity result = logic.createNotification(newEntity);
        Assert.fail("Deberia generar error");
    }
     @Test (expected = BusinessLogicException.class)
    public void createNotificationError2Test()throws BusinessLogicException {
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setNotificado(peopleData.get(0));
        newEntity.setSitioWeb(null);
        newEntity.setCambioSitio(stateData.get(0));
        NotificacionEntity result = logic.createNotification(newEntity);
        Assert.fail("Deberia generar error");
    }
     @Test (expected = BusinessLogicException.class)
    public void createNotificationError3Test()throws BusinessLogicException {
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setNotificado(peopleData.get(0));
        newEntity.setSitioWeb(sitesData.get(0));
        newEntity.setCambioSitio(null);
        NotificacionEntity result = logic.createNotification(newEntity);
        Assert.fail("Deberia generar error");
    }
    
    
    
    @Test
    public void getAllNotificationsTest() {
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
    
    @Test 
    public void getTest()
    {
        try{
        NotificacionEntity test = logic.getNotificacion(data.get(0).getId());
        }
        catch(BusinessLogicException e)
        {
        }
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void getTestFail() throws BusinessLogicException
    {
        NotificacionEntity test = logic.getNotificacion(Long.MAX_VALUE);
    }
    
    @Test
    public void deleteNotificationtest()
    {
        try {
        NotificacionEntity aPrueba= logic.getNotificacion(data.get(0).getId());
        logic.deleteNotificacion(aPrueba.getId());
        logic.getNotificacion(aPrueba.getId());
        } catch (BusinessLogicException e) {
            Assert.assertEquals("No encontrado", e.getMessage());
        }
        
    }
    
    @Test 
    public void updateNotificationTest()
    {
       try{
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setNotificado(peopleData.get(0));
        newEntity.setSitioWeb(sitesData.get(0));
        newEntity.setCambioSitio(stateData.get(0));
        NotificacionEntity result = logic.updateNotificacion(data.get(0).getId(),newEntity);
        Assert.assertNotNull(result);
        NotificacionEntity entity = em.find(NotificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCambioSitio(), entity.getCambioSitio());
        Assert.assertEquals(newEntity.getNotificado(), entity.getNotificado());
        Assert.assertEquals(newEntity.getSitioWeb() , entity.getSitioWeb());
        }
        catch(BusinessLogicException e)
        {
          Assert.fail("no deberia generar error");
        }
    }
}

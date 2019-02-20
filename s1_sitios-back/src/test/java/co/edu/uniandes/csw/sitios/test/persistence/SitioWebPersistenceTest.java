/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
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
public class SitioWebPersistenceTest {
    
    @Inject
    SitioWebPersistence persistence;
     
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<SitioWebEntity> data = new ArrayList<SitioWebEntity>();
     
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SitioWebEntity.class.getPackage())
                .addPackage(SitioWebPersistence.class.getPackage())
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
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SitioWebEntity entity = factory.manufacturePojo(SitioWebEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     /**
     * Prueba para crear un Book.
     */
    @Test
    public void createSiteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SitioWebEntity newEntity = factory.manufacturePojo(SitioWebEntity.class);
        SitioWebEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        SitioWebEntity entity = em.find(SitioWebEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getProposito(), entity.getProposito());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
    }
    
     /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getSitesTest() {
        List<SitioWebEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SitioWebEntity ent : list) {
            boolean found = false;
            for (SitioWebEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getSiteTest() {
        SitioWebEntity entity = data.get(0);
        SitioWebEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getProposito(), entity.getProposito());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
    }
    
     /**
     * Prueba para eliminar un Book.
     */
    @Test
    public void deleteSiteTest() {
        SitioWebEntity entity = data.get(0);
        persistence.delete(entity.getId());
        SitioWebEntity deleted = em.find(SitioWebEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Book.
     */
    @Test
    public void updateSiteTest() {
        SitioWebEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SitioWebEntity newEntity = factory.manufacturePojo(SitioWebEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        SitioWebEntity resp = em.find(SitioWebEntity.class, entity.getId());

       Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getProposito(), resp.getProposito());
        Assert.assertEquals(newEntity.getImagen(), resp.getImagen());
        
    }

}

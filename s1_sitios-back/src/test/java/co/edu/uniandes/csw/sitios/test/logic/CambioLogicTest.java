/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
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
 * @author s.ballesteros
 */
@RunWith(Arquillian.class)
public class CambioLogicTest {
    
    @Inject
    private CambioLogic cambioLogic;
    
    @Inject
    private UserTransaction utx;
    
     @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<CambioEntity> data = new ArrayList<CambioEntity>();
    
    private List<SitioWebEntity> sitioData = new ArrayList<SitioWebEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CambioEntity.class.getPackage())
                .addPackage(CambioLogic.class.getPackage())
                .addPackage(CambioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            utx.rollback();
        }
        catch(Exception e1){
            e1.printStackTrace();
        }
    }
    
    public void clearData(){
        em.createQuery("delete from CambioEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();

    }
    
    private void insertData(){
        for(int i = 0;i<3;i++){
            CambioEntity entity = factory.manufacturePojo(CambioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        } 
        for (int i = 0; i < 3; i++) {
            SitioWebEntity site = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(site);
            sitioData.add(site);
        }
    }
    
    @Test
    public void createCambioTest() throws BusinessLogicException{

        CambioEntity newEntity = factory.manufacturePojo(CambioEntity.class);
        CambioEntity result = cambioLogic.createCambio(newEntity);
        //Verificamos que no sea nulo
        Assert.assertNotNull(result);
        //Verificamos que el id o el objeto este en la base de datos buscando si el id existe
        CambioEntity entity = em.find(CambioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLugarCambio(), entity.getLugarCambio());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaCambio(), entity.getFechaCambio());
        Assert.assertEquals(newEntity.getNuevo(), entity.getNuevo());
        Assert.assertEquals(newEntity.getPrevio(), entity.getPrevio());
        Assert.assertEquals(newEntity.getSitioWeb(), entity.getSitioWeb());
    }
    
    
     @Test
    public void createCambioTest2() throws BusinessLogicException {

        CambioEntity newEntity = factory.manufacturePojo(CambioEntity.class);
        newEntity.setId(data.get(0).getId());
        cambioLogic.createCambio(newEntity);
    }
    
}

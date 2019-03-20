/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.PlataformaDeDespliegueLogic;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
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
public class PlataformaDeDespliegueLogicTest {
    
    @Inject
    private PlataformaDeDespliegueLogic plataformaLogic;
    
    @Inject
    private UserTransaction utx;
    
     @PersistenceContext
    private EntityManager em;
     
    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<PlataformaDeDespliegueEntity> data = new ArrayList<>();
    
    private List<SitioWebEntity> sitioData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlataformaDeDespliegueEntity.class.getPackage())
                .addPackage(PlataformaDeDespliegueLogic.class.getPackage())
                .addPackage(PlataformaDeDesplieguePersistence.class.getPackage())
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
       em.createQuery("delete from PlataformaDeDespliegueEntity").executeUpdate();
       em.createQuery("delete from SitioWebEntity").executeUpdate(); 
    }
    
    private void insertData(){
        for(int i = 0;i<3;i++){
            PlataformaDeDespliegueEntity entity = factory.manufacturePojo(PlataformaDeDespliegueEntity.class);
            
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
    public void createPlataformaDeDespliegueTest() throws BusinessLogicException{

        PlataformaDeDespliegueEntity newEntity = factory.manufacturePojo(PlataformaDeDespliegueEntity.class);
        PlataformaDeDespliegueEntity result = plataformaLogic.createPlataformaDeDespliegue(newEntity);
        //Verificamos que no sea nulo
        Assert.assertNotNull(result);
        //Verificamos que el id o el objeto este en la base de datos buscando si el id existe
        PlataformaDeDespliegueEntity entity = em.find(PlataformaDeDespliegueEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getIp(), entity.getIp());
        Assert.assertEquals(newEntity.getCpu(), entity.getCpu());
        Assert.assertEquals(newEntity.getCores(), entity.getCores());
        Assert.assertEquals(newEntity.getClock(), entity.getClock());
        Assert.assertEquals(newEntity.getHosting(), entity.getHosting());
        Assert.assertEquals(newEntity.getIsVirtualizacion(), entity.getIsVirtualizacion());
        Assert.assertEquals(newEntity.getSitiosWeb(), entity.getSitiosWeb());
    }
    
    
    @Test
    public void createPlataformaDeDespliegueTest2() throws BusinessLogicException {

        PlataformaDeDespliegueEntity newEntity = factory.manufacturePojo(PlataformaDeDespliegueEntity.class);
        newEntity.setId(data.get(0).getId());
        plataformaLogic.createPlataformaDeDespliegue(newEntity);
    }
}
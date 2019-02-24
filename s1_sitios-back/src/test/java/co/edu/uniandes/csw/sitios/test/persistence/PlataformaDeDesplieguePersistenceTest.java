/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.ballesteros
 */
@RunWith(Arquillian.class)
public class PlataformaDeDesplieguePersistenceTest {

    /*
    /atributo pp PlataformaDeDesplieguePersistence
     */
    @Inject
    private PlataformaDeDesplieguePersistence pp;
    
    /*
    / 
    */
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlataformaDeDespliegueEntity.class.getPackage())
                .addPackage(PlataformaDeDesplieguePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    // ep es entitypersistence
    
    @Test
    public void createPlataformaDeDespliegueTest() {

        PodamFactory factory = new PodamFactoryImpl();
        PlataformaDeDespliegueEntity newEntity = factory.manufacturePojo(PlataformaDeDespliegueEntity.class);

        PlataformaDeDespliegueEntity ep = pp.create(newEntity);
       
        //Verificamos que no sea nulo
        Assert.assertNotNull(ep);
        
        //Verificamos que el id o el objeto este en la base de datos buscando si el id existe
        PlataformaDeDespliegueEntity entity = em.find(PlataformaDeDespliegueEntity.class, ep.getId());
        
        //El nombre aleatorio pp dado el metodo inject
  //      Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getIp(), entity.getIp());
        Assert.assertEquals(newEntity.getCPU(), entity.getCPU());
        Assert.assertEquals(newEntity.getCores(), entity.getCores());
        Assert.assertEquals(newEntity.getClock(), entity.getClock());
        Assert.assertEquals(newEntity.getHosting(), entity.getHosting());
        Assert.assertEquals(newEntity.getIsVirtualizacion(), entity.getIsVirtualizacion());
        Assert.assertEquals(newEntity.getSitiosWeb(), entity.getSitiosWeb());
    }

}

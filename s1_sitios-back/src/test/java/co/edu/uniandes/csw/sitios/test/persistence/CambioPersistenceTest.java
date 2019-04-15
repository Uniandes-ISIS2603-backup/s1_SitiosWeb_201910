/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
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
public class CambioPersistenceTest {

    /*
    /atributo pp PlataformaDeDesplieguePersistence
     */
    @Inject
    private CambioPersistence cp;

    /*
    / 
     */
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CambioEntity.class.getPackage())
                .addPackage(CambioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    // ep es entitypersistence
    @Test
    public void createCambioTest() {

        PodamFactory factory = new PodamFactoryImpl();
        CambioEntity newEntity = factory.manufacturePojo(CambioEntity.class);

        CambioEntity ep = cp.create(newEntity);

        //Verificamos que no sea nulo
        Assert.assertNotNull(ep);

        //Verificamos que el id o el objeto este en la base de datos buscando si el id existe
        CambioEntity entity = em.find(CambioEntity.class, ep.getId());

        //El nombre aleatorio pp dado el metodo inject
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLugarCambio(), entity.getLugarCambio());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaCambio(), entity.getFechaCambio());
        Assert.assertEquals(newEntity.getNuevo(), entity.getNuevo());
        Assert.assertEquals(newEntity.getPrevio(), entity.getPrevio());
        Assert.assertEquals(newEntity.getSitioWeb(), entity.getSitioWeb());
    }

}

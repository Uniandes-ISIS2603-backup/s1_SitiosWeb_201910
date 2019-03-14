/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.AdministradorCambiosLogic;
import co.edu.uniandes.csw.sitios.ejb.AdministradorLogic;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@RunWith(Arquillian.class)
public class AdministradorCambiosLogicTest {

    private static final Logger LOGGER = Logger.getLogger(AdministradorCambiosLogic.class.getName());

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CambioLogic cambioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(CambioEntity.class.getPackage())
                .addPackage(AdministradorCambiosLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Prueba para crear un Administrador.
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void addCambioTest() throws BusinessLogicException {

        CambioEntity newEntity = factory.manufacturePojo(CambioEntity.class);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los cambios del admin con id = {0}", cambioLogic);

        CambioEntity result = cambioLogic.createCambio(newEntity);
        CambioEntity entity = em.find(CambioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getFechaCambio(), entity.getFechaCambio());
        Assert.assertEquals(newEntity.getLugarCambio(), entity.getLugarCambio());
        Assert.assertEquals(newEntity.getNuevo(), entity.getNuevo());
        Assert.assertEquals(newEntity.getPrevio(), entity.getPrevio());

    }
}

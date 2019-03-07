/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.AdministradorLogic;
import co.edu.uniandes.csw.sitios.ejb.AdministradorSitiosWebLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import java.util.ArrayList;
import java.util.List;
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
public class AdministradorSitiosWebLogicTest {
     private static final Logger LOGGER = Logger.getLogger(AdministradorSitiosWebLogicTest.class.getName());

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AdministradorSitiosWebLogic adminSitiosLogic;

    @Inject
    private SitioWebLogic sitioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AdministradorEntity> data = new ArrayList<>();
    
    private List<SitioWebEntity> sitioData = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
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
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SitioWebEntity books = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(books);
            sitioData.add(books);
        }
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                sitioData.get(i).setAdministradores(new ArrayList<>());
                sitioData.get(i).setAdministradores(data);
            }
        }
    }
    
    /**
     * Prueba para asociar un Books existente a un Editorial.
     */
    @Test
    public void addSitiosWebTest() {
        AdministradorEntity entity = data.get(0);
        SitioWebEntity sitioEntity = sitioData.get(1);
        SitioWebEntity response = adminSitiosLogic.addSitioWeb(sitioEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(sitioEntity.getId(), response.getId());
    }
}

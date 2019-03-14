/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class TecnologiaLogicTest {

    private static final Logger LOGGER = Logger.getLogger(TecnologiaLogicTest.class.getName());
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TecnologiaEntity> data = new ArrayList<>();
    private List<SitioWebEntity> sitioData = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnologiaEntity.class.getPackage())
                .addPackage(TecnologiaLogic.class.getPackage())
                .addPackage(TecnologiaPersistence.class.getPackage())
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
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 30; i++) {
            SitioWebEntity sitioEntity = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(sitioEntity); //entity
            sitioData.add(sitioEntity);
        }
        for (int i = 0; i < 30; i++) {
            TecnologiaEntity tecnologiaEntity = factory.manufacturePojo(TecnologiaEntity.class);
            em.persist(tecnologiaEntity); //entity
            data.add(tecnologiaEntity);
        }
    }

    /**
     * Prueba para crear una Tecnologia.
     */
    @Test
    public void createTechnologyTest() {
        //TODO rehacer este test
    }

    /**
     * Prueba para consultar la lista de Tecnologias.
     */
    @Test
    public void getTechnologiesTest() {
        List<TecnologiaEntity> techs = tecnologiaLogic.getTechnologies();
        Assert.assertEquals(techs.size(), data.size());
    }

    /**
     * Prueba para consultar una Tecnologia.
     */
    @Test
    public void getTechnologyTest() {
        TecnologiaEntity tecnologiaEntity = tecnologiaLogic.getTechnology(data.get(0).getId());
        Assert.assertEquals(tecnologiaEntity, data.get(0));
    }

    /**
     * Prueba para actualizar una Tecnologia.
     */
    @Test
    public void updateTechnologyTest() {
       //TODO rehacer este test
    }

    /**
     * Prueba para eliminar una tecnología
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteTechnologyTest() throws BusinessLogicException {

        TecnologiaEntity newTech = factory.manufacturePojo(TecnologiaEntity.class);

        newTech.setSitiosWeb(new ArrayList<SitioWebEntity>());

        TecnologiaEntity techEntity = tecnologiaLogic.createTechnology(newTech);
        Long id = techEntity.getId();
        tecnologiaLogic.deleteTechnology(id);
        tecnologiaLogic.getTechnology(id);
    }

}

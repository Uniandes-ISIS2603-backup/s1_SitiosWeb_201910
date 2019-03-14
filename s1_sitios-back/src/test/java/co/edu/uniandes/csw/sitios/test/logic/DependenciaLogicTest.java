/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.DependenciaLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.DependenciaPersistence;
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
public class DependenciaLogicTest {

    private static final Logger LOGGER = Logger.getLogger(DependenciaLogicTest.class.getName());
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private DependenciaLogic dependenciaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DependenciaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DependenciaEntity.class.getPackage())
                .addPackage(DependenciaLogic.class.getPackage())
                .addPackage(DependenciaPersistence.class.getPackage())
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
        em.createQuery("delete from DependenciaEntity").executeUpdate();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 30; i++) {
            DependenciaEntity dependenciaEntity = factory.manufacturePojo(DependenciaEntity.class);
            DependenciaEntity newdep = factory.manufacturePojo(DependenciaEntity.class);
            newdep.setEncargadoDependencia(new AdministradorEntity());
            em.persist(dependenciaEntity); //entity
            data.add(dependenciaEntity);
        }
    }

    /**
     * Prueba para crear una Dependencia.
     */
    @Test
    public void createDependency() {
        //TODO rehacer este test
    }

    /**
     * Prueba para consultar la lista de Dependencias.
     */
    @Test
    public void getDependenciasTest() {
       //TODO rehacer este test
    }

    /**
     * Prueba para consultar una Dependencia.
     */
    @Test
    public void getDependenciaTest() {
     //TODO rehacer este test
    }

    /**
     * Prueba para actualizar una Dependencia.
     */
    @Test
    public void updateDependenciaTest() {
        //TODO rehacer este test
    }

    /**
     * Prueba para eliminar una tecnología
     *
     * @throws BusinessLogicException
     */
    @Test//(expected = BusinessLogicException.class)
    public void deleteDependencia() throws BusinessLogicException {
        //TODO rehacer este test
    }
}
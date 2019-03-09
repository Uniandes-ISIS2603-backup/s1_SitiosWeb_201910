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
        try {
            DependenciaEntity newDep = factory.manufacturePojo(DependenciaEntity.class);
            newDep.setEmail("holaprofesor@prueba.edu.co");
            newDep.setEncargadoDependencia(new AdministradorEntity());
            DependenciaEntity createDep = dependenciaLogic.createDependency(newDep);
            data.add(newDep);
        } catch (BusinessLogicException e) {
            Assert.fail("no deberia generar error: " + e.getMessage());
        }
    }

    /**
     * Prueba para consultar la lista de Dependencias.
     */
    @Test
    public void getDependenciasTest() {
        List<DependenciaEntity> deps = dependenciaLogic.getDependencies();
        Assert.assertEquals(deps.size(), data.size());
    }

    /**
     * Prueba para consultar una Dependencia.
     */
    @Test
    public void getDependenciaTest() {
        DependenciaEntity dependenciaEntity = dependenciaLogic.getDependency(data.get(0).getId());
        Assert.assertEquals(dependenciaEntity, data.get(0));
    }

    /**
     * Prueba para actualizar una Dependencia.
     */
    @Test
    public void updateDependenciaTest() {
        try {
            DependenciaEntity entity = dependenciaLogic.getDependency(data.get(0).getId());
            String value = String.valueOf(new Random().nextInt() + 1);
            String value2 = String.valueOf(new Random().nextInt() + 1);
            entity.setEmail("holaprofesor@uniandes.edu.co");
            entity.setNombreDependencia(value);
            entity.setTelefono(value2);
            dependenciaLogic.updateDependency(entity.getId(), entity);
            DependenciaEntity entity2 = dependenciaLogic.getDependency(data.get(0).getId());
            Assert.assertEquals(entity2.getTelefono(), value2);
            Assert.assertEquals(entity2.getNombreDependencia(), value);

        } catch (BusinessLogicException e) {
            LOGGER.log(Level.INFO, "TESTNUMEROUNOLOGGER = {0}", e  );
            Assert.fail();
        }
    }

    /**
     * Prueba para eliminar una tecnología
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteDependencia() throws BusinessLogicException {

        DependenciaEntity newDep = factory.manufacturePojo(DependenciaEntity.class);

        newDep.setEncargadoDependencia(new AdministradorEntity());

        DependenciaEntity depEntity = dependenciaLogic.createDependency(newDep);
        Long id = depEntity.getId();
        dependenciaLogic.deleteDependency(id);
        dependenciaLogic.getDependency(id);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import co.edu.uniandes.csw.sitios.persistence.DependenciaPersistence;
import java.util.ArrayList;
import java.util.List;
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
public class DependenciaPersistenceTest {

    @Inject
    private DependenciaPersistence dependenciaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<DependenciaEntity> data = new ArrayList<DependenciaEntity>();
    
    private static final Logger LOGGER = Logger.getLogger(DependenciaPersistenceTest.class.getName());

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DependenciaEntity.class.getPackage())
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
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from DependenciaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DependenciaEntity entity = factory.manufacturePojo(DependenciaEntity.class);
            AdministradorEntity adminEntity = factory.manufacturePojo(AdministradorEntity.class);

            entity.setEncargadoDependencia(adminEntity);
            adminEntity.setDependencia(entity);

            em.persist(entity);
            em.persist(adminEntity);
            data.add(entity);
        }
        DependenciaEntity entity = factory.manufacturePojo(DependenciaEntity.class);
        em.persist(entity);
        data.add(entity);

    }

    /**
     * Prueba para crear un Dependencia.
     */
    @Test
    public void createDependenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DependenciaEntity newEntity = factory.manufacturePojo(DependenciaEntity.class);

        DependenciaEntity result = dependenciaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        DependenciaEntity entity = em.find(DependenciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombreDependencia(), entity.getNombreDependencia());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());

    }

    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getDependenciasTest() {
        List<DependenciaEntity> list = dependenciaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DependenciaEntity ent : list) {
            boolean found = false;
            for (DependenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Dependencia.
     */
    @Test
    public void getDependenciaTest() {
        DependenciaEntity entity = data.get(0);
        DependenciaEntity newEntity = dependenciaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombreDependencia(), newEntity.getNombreDependencia());
        Assert.assertEquals(entity.getEmail(), newEntity.getEmail());
        Assert.assertEquals(entity.getTelefono(), newEntity.getTelefono());
    }

    /**
     * Prueba para eliminar un Dependencia.
     */
    @Test
    public void deleteDependenciaTest() {
        DependenciaEntity entity = data.get(3);
        dependenciaPersistence.delete(entity.getId());
        DependenciaEntity deleted = em.find(DependenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Dependencia.
     */
    @Test
    public void updateDependenciaTest() {
        DependenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DependenciaEntity newEntity = factory.manufacturePojo(DependenciaEntity.class);

        entity.setId(newEntity.id);
        entity.setEmail(newEntity.getEmail());
        entity.setEncargadoDependencia(newEntity.getEncargadoDependencia());
        entity.setNombreDependencia(newEntity.getNombreDependencia());
        entity.setTelefono(newEntity.getTelefono());

        dependenciaPersistence.update(entity);

        DependenciaEntity resp = em.find(DependenciaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombreDependencia(), entity.getNombreDependencia());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
    }
}  
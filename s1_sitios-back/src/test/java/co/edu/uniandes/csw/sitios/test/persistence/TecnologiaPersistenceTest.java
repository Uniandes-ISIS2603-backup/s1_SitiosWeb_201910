/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class TecnologiaPersistenceTest {

    @Inject
    private TecnologiaPersistence tecnologiaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<TecnologiaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnologiaEntity.class.getPackage())
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
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TecnologiaEntity entity = factory.manufacturePojo(TecnologiaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Tecnologia.
     */
    @Test
    public void createTecnologiaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        TecnologiaEntity result = tecnologiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TecnologiaEntity entity = em.find(TecnologiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Tecnologias.
     */
    @Test
    public void getTecnologiasTest() {
        List<TecnologiaEntity> list = tecnologiaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TecnologiaEntity ent : list) {
            boolean found = false;
            for (TecnologiaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Tecnologia.
     */
    @Test
    public void getTecnologiaTest() {
        TecnologiaEntity entity = data.get(0);
        TecnologiaEntity newEntity = tecnologiaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getTechCategory(), newEntity.getTechCategory());
        Assert.assertEquals(entity.getUrl(), newEntity.getUrl());
        Assert.assertEquals(entity.getVersion(), newEntity.getVersion());
        
    }

    /**
     * Prueba para actualizar un Tecnologia.
     */
    @Test
    public void updateTecnologiaTest() {
        TecnologiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);

        newEntity.setId(entity.getId());

        tecnologiaPersistence.update(newEntity);

        TecnologiaEntity resp = em.find(TecnologiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Prueba para eliminar un Tecnologia.
     */
    @Test
    public void deleteTecnologiaTest() {
        TecnologiaEntity entity = data.get(0);
        tecnologiaPersistence.delete(entity.getId());
        TecnologiaEntity deleted = em.find(TecnologiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}

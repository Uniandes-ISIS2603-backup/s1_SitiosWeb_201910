/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.persistence;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.persistence.EstadoWebPersistence;
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
 * Pruebas de persistencia para un EstadoWeb
 * 
 * @author Daniel Preciado
 */
@RunWith(Arquillian.class)
public class EstadoWebPersistenceTest {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * Inyección de la dependencia a la clase EstadoWebPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EstadoWebPersistence estadoWebDB;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la DB
     * por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * lista que tiene los datos de prueba.
     */
    private List<EstadoWebEntity> dateTest = new ArrayList<EstadoWebEntity>();
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de EstadoWeb, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoWebEntity.class.getPackage())
                .addPackage(EstadoWebPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
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
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {

            EstadoWebEntity entity = factory.manufacturePojo(EstadoWebEntity.class);

            em.persist(entity);

            dateTest.add(entity);
        }
    }

    /**
     * Prueba para crear una EstadoWeb.
     */
    @Test
    public void createEstadoWebTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        try
        {
            EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
            EstadoWebEntity result = estadoWebDB.create(newEntity);

            Assert.assertNotNull(result);

            EstadoWebEntity entity = em.find(EstadoWebEntity.class, result.getId());

            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
            
        }catch(Exception msg)
        {
            
        }
        

    }

    /**
     * Prueba para consultar la lista de estados Web.
     */
    @Test
    public void getEstadosWebTest()
    {
        List<EstadoWebEntity> list = estadoWebDB.findAll();
        Assert.assertEquals(dateTest.size(), list.size());
        for (EstadoWebEntity ent : list) 
        {
            boolean found = false;
            for (EstadoWebEntity entity : dateTest) 
            {
                if (ent.getId().equals(entity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un EstadoWeb.
     */
    @Test
    public void getEstadoWebTest() 
    {
        EstadoWebEntity entity = dateTest.get(0);
        EstadoWebEntity newEntity = estadoWebDB.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());

    }

    /**
     * Prueba para eliminar una EstadoWeb.
     */
    @Test
    public void deleteEstadoWebTest() 
    {
        EstadoWebEntity entity = dateTest.get(0);
        estadoWebDB.delete(entity.getId());
        EstadoWebEntity deleted = em.find(EstadoWebEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una EstadoWeb.
     */
    @Test
    public void updateEstadoWebTest() {
        EstadoWebEntity entity = dateTest.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);

        newEntity.setId(entity.getId());

        estadoWebDB.update(newEntity);

        EstadoWebEntity resp = em.find(EstadoWebEntity.class, entity.getId());

        Assert.assertEquals(resp.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(resp.getEstado(), newEntity.getEstado());
    }

}

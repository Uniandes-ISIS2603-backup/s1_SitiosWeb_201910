/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.EstadoWebLogic;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
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
 * Pruebas de logica diseñadas para la clase EstadoWebLogic
 * 
 * @author Daniel PReciado
 */
@RunWith(Arquillian.class)
public class EstadoWebLogicTest {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
   
    /**
     * clase que ayudara  a fabricar objetos de prueba
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyeccion de dependencias a la logica de un EstadoWeb
     */
    @Inject
    private EstadoWebLogic estadoWebLogic;

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
    private UserTransaction utx;
    

    /**
     * list de data para las pruebas
     */
    private List<EstadoWebEntity> data = new ArrayList<EstadoWebEntity>();
    
    /**
     * list de data para las pruebas
     */
    private List<SitioWebEntity> auxData = new ArrayList<SitioWebEntity>();
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstadoWebEntity.class.getPackage())
                .addPackage(EstadoWebLogic.class.getPackage())
                .addPackage(EstadoWebPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial del test.
     *
     */
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     */
    private void clearData() 
    {
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() 
    {
        for (int i = 0; i < 3; i++) 
        {
           SitioWebEntity newSitio=  factory.manufacturePojo(SitioWebEntity.class);
        
           em.persist(newSitio);
           auxData.add(newSitio);
        }
        
        for (int i = 0; i < 3; i++)
        {
            EstadoWebEntity entity = factory.manufacturePojo(EstadoWebEntity.class);
            entity.setSitioAsociado(auxData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un EstadoWeb.
     *
     */
     
    @Test
    public void createEstadoWebTest()  
    {
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
        newEntity.setSitioAsociado(auxData.get(0));
        try
        {
            EstadoWebEntity result = estadoWebLogic.createEstadoWeb(newEntity);
            Assert.assertNotNull(result);
            EstadoWebEntity entity = em.find(EstadoWebEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
        }
        catch(BusinessLogicException e)
        {
            Assert.fail();
        }

    }
    
    /**
     * Prueba para crear un EstadoWeb.
     * 
     * en esta prueba se intenta crear un estado web que incumple con las 
     * reglas de negocio
     * caso 1: no se le asigna un sitio al estado web
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createEstadoWebTestFail1()  throws BusinessLogicException
    {
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
        EstadoWebEntity result = estadoWebLogic.createEstadoWeb(newEntity);
 
    }
    
    /**
     * Prueba para crear un EstadoWeb.
     * 
     * en esta prueba se intenta crear un estado web que incumple con las 
     * reglas de negocio
     * caso 2: se envia una descripcion vacia
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createEstadoWebTestFail2()  throws BusinessLogicException
    {
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
        newEntity.setSitioAsociado(auxData.get(0));
        newEntity.setDescripcion("");
        EstadoWebEntity result = estadoWebLogic.createEstadoWeb(newEntity);

    }
    
    /**
     * Prueba para crear un EstadoWeb.
     * 
     * en esta prueba se intenta crear un estado web que incumple con las 
     * reglas de negocio
     * caso 3: se intenta crear el estadoWeb sin un estado
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createEstadoWebTestFail3()  throws BusinessLogicException
    {
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
        newEntity.setSitioAsociado(auxData.get(0));
        newEntity.setEstado(null);
        EstadoWebEntity result = estadoWebLogic.createEstadoWeb(newEntity);

    }
    
    /**
     * Prueba para crear un EstadoWeb.
     * 
     * en esta prueba se intenta crear un estado web que incumple con las 
     * reglas de negocio
     * caso 4: se intenta crear el estadoWeb sin una fecha
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createEstadoWebTestFail4()  throws BusinessLogicException
    {
        EstadoWebEntity newEntity = factory.manufacturePojo(EstadoWebEntity.class);
        newEntity.setSitioAsociado(auxData.get(0));
        newEntity.setFechaCambio(null);
        EstadoWebEntity result = estadoWebLogic.createEstadoWeb(newEntity);

    }
    
   
    /**
     * Prueba para consultar la lista de todos los EstadosWeb.
     */
    @Test
    public void getEstadosWebTest() 
    {
        List<EstadoWebEntity> list = estadoWebLogic.getEstadosWeb();
        Assert.assertEquals(data.size(), list.size());
        for (EstadoWebEntity entity : list) 
        {
            boolean found = false;
            for (EstadoWebEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) 
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
        EstadoWebEntity entity = data.get(0);
        EstadoWebEntity resultEntity = estadoWebLogic.getEstadoWeb(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
    }

    /**
     * Prueba para actualizar un EstadoWeb.
     */
    @Test
    public void updateEstadoWebTest()
    {
        try
        {
            EstadoWebEntity entity = data.get(0);
            EstadoWebEntity pojoEntity = factory.manufacturePojo(EstadoWebEntity.class);

            pojoEntity.setId(entity.getId());

            estadoWebLogic.updateEstadoWeb(pojoEntity.getId(), pojoEntity);
            
            EstadoWebEntity resp = em.find(EstadoWebEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado());
        } 
        catch (Exception e) 
        {
            Assert.fail();
        }
    }

    /**
     * Prueba para eliminar un EstadoWeb.
     */
    @Test
    public void deleteEstadoWebTest() 
    {
        EstadoWebEntity entity = data.get(0);
        estadoWebLogic.deleteEstadoWeb(entity.getId());
        EstadoWebEntity deleted = em.find(EstadoWebEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}

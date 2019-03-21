/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
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
 * Pruebas de logica diseñadas para la clase TicketLogic
 * 
 * @author Daniel PReciado
 */
@RunWith(Arquillian.class)
public class TicketLogicTest {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
   
    /**
     * clase que ayudara  a fabricar objetos de prueba
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Inyeccion de dependencias a la logica de un Ticket
     */
    @Inject
    private TicketLogic ticketLogic;

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
    private List<TicketEntity> data = new ArrayList<TicketEntity>();
    
    /**
     * list de data para las pruebas
     */
    private List<SitioWebEntity> sitiosData = new ArrayList<SitioWebEntity>();
    
    /**
     * list de data para las pruebas
     */
    private List<UsuarioEntity> usuariosData = new ArrayList<UsuarioEntity>();
    
    //__________________________________________________________________________
    // Metodos
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
                .addPackage(TicketEntity.class.getPackage())
                .addPackage(TicketLogic.class.getPackage())
                .addPackage(TicketPersistence.class.getPackage())
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
        em.createQuery("delete from TicketEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        
        
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
           sitiosData.add(newSitio);
        }
        
        for (int i = 0; i < 3; i++)
        {
           UsuarioEntity newUsuario =  factory.manufacturePojo(UsuarioEntity.class);
        
           em.persist(newUsuario);
           usuariosData.add(newUsuario);
        }
        
        for (int i = 0; i < 3; i++)
        {
            TicketEntity entity = factory.manufacturePojo(TicketEntity.class);
            entity.setSitioAsociado(sitiosData.get(0));
            entity.setUsuarioAsociado(usuariosData.get(0));

            em.persist(entity);
            data.add(entity);
        }
        
    }

    /**
     * Prueba para crear un Ticket.
     *
     */
     
    @Test
    public void createTicketTest()  
    {
        //TODO rehacer este test
        
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        newEntity.setSitioAsociado(sitiosData.get(0));
        newEntity.setUsuarioAsociado(usuariosData.get(0));
        try
        {
            TicketEntity result = ticketLogic.createTicket(newEntity);
            Assert.assertNotNull(result);
            TicketEntity entity = em.find(TicketEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals(newEntity.getEstado(), entity.getEstado());
            Assert.assertEquals(newEntity.getSitioAsociado(), entity.getSitioAsociado());
            Assert.assertEquals(newEntity.getUsuarioAsociado(), entity.getUsuarioAsociado());

            
        }
        catch(BusinessLogicException e)
        {
             e.printStackTrace();
            Assert.fail();
           
        }
    }
    
    /**
     * Prueba para crear un Ticket.
     * 
     * en esta prueba se intenta crear un ticket que incumple con las 
     * reglas de negocio
     * caso 1: no se le asigna un sitio al ticket
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createTicketTestFail1()  throws BusinessLogicException
    {
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        TicketEntity result = ticketLogic.createTicket(newEntity);
 
    }
    
    /**
     * Prueba para crear un Ticket.
     * 
     * en esta prueba se intenta crear un ticket que incumple con las 
     * reglas de negocio
     * caso 2: se envia una descripcion vacia
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createTicketTestFail2()  throws BusinessLogicException
    {
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        newEntity.setSitioAsociado(sitiosData.get(0));
        newEntity.setDescripcion("");
        TicketEntity result = ticketLogic.createTicket(newEntity);

    }
    
    /**
     * Prueba para crear un Ticket.
     * 
     * en esta prueba se intenta crear un ticket que incumple con las 
     * reglas de negocio
     * caso 3: se intenta crear el ticket sin un estado
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createTicketTestFail3()  throws BusinessLogicException
    {
       //TODO rehacer este test
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        newEntity.setSitioAsociado(sitiosData.get(0));
        newEntity.setEstado(10);
        TicketEntity result = ticketLogic.createTicket(newEntity);
    }
    
    /**
     * Prueba para crear un Ticket.
     * 
     * en esta prueba se intenta crear un ticket que incumple con las 
     * reglas de negocio
     * caso 4: se intenta crear el ticket sin una fecha
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createTicketTestFail4()  throws BusinessLogicException
    {
        //TODO rehacer este test
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        newEntity.setSitioAsociado(sitiosData.get(0));
        newEntity.setFecha(null);
        TicketEntity result = ticketLogic.createTicket(newEntity);
    }
    
    
    /**
     * Prueba para crear un Ticket.
     * 
     * en esta prueba se intenta crear un ticket que incumple con las 
     * reglas de negocio
     * caso 1: no se le asigna un usuario al ticket
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
     
    @Test (expected = BusinessLogicException.class)
    public void createTicketTestFail5()  throws BusinessLogicException
    {
        TicketEntity newEntity = factory.manufacturePojo(TicketEntity.class);
        newEntity.setSitioAsociado(sitiosData.get(0));
        TicketEntity result = ticketLogic.createTicket(newEntity);
 
    }
   
    /**
     * Prueba para consultar la lista de todos los Tickets.
     */
    @Test
    public void getTicketsTest() 
    {
       //TODO rehacer este test
        List<TicketEntity> list = ticketLogic.getTickets();
        Assert.assertEquals(data.size(), list.size());
        for (TicketEntity entity : list) 
        {
            boolean found = false;
            for (TicketEntity storedEntity : data)
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
     * Prueba para consultar un Ticket.
     */
    @Test
    public void getTicketTest() 
    {
      //TODO rehacer este test
        TicketEntity entity = data.get(0);
        TicketEntity resultEntity = ticketLogic.getTicket(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getEstado(), resultEntity.getEstado());
        Assert.assertEquals(entity.getSitioAsociado(), resultEntity.getSitioAsociado());
        Assert.assertEquals(entity.getUsuarioAsociado(), resultEntity.getUsuarioAsociado());
    }

    /**
     * Prueba para actualizar un Ticket.
     */
    @Test
    public void updateTicketTest()
    {
      //TODO rehacer este test}
        try
        {
            TicketEntity entity = data.get(0);
            TicketEntity pojoEntity = factory.manufacturePojo(TicketEntity.class);

            pojoEntity.setId(entity.getId());

            ticketLogic.updateTicket(pojoEntity.getId(), pojoEntity);
            
            TicketEntity resp = em.find(TicketEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojoEntity.getEstado(), resp.getEstado());
            Assert.assertEquals(pojoEntity.getSitioAsociado(), resp.getSitioAsociado());
            Assert.assertEquals(pojoEntity.getUsuarioAsociado(), resp.getUsuarioAsociado());
        } 
        catch (Exception e) 
        {
            Assert.fail();
        }
        
        
    }

    /**
     * Prueba para eliminar un Ticket.
     */
    @Test
    public void deleteTicketTest() 
    {
       //TODO rehacer este test
        TicketEntity entity = data.get(0);
        ticketLogic.deleteTicket(entity.getId());
        TicketEntity deleted = em.find(TicketEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketSitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *pruebas de la logica de la relacion Ticket-SitioWeb
 * 
 * @author Daniel Preciado
 */
@RunWith(Arquillian.class)
public class TicketSitioWebLogicTest {
    
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
     * Inyeccion de dependencias a la logica de un ticketSitioWebLogic
     */
    @Inject
    private TicketSitioWebLogic ticketSitioWebLogic;

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
    private List<SitioWebEntity> data = new ArrayList<SitioWebEntity>();

    /**
     * list de data para las pruebas
     */
    private List<TicketEntity> ticketsData = new ArrayList();
    
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
                .addPackage(SitioWebEntity.class.getPackage())
                .addPackage(TicketLogic.class.getPackage())
                .addPackage(SitioWebPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TicketEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) 
        {
            TicketEntity tickets = factory.manufacturePojo(TicketEntity.class);
            em.persist(tickets);
            ticketsData.add(tickets);
        }
        for (int i = 0; i < 3; i++) 
        {
            SitioWebEntity entity = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) 
            {
                ticketsData.get(i).setSitioAsociado(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Tickets asociadas a una instancia
     * de SitioWeb.
     */
    @Test
    public void replaceSitioAsociadoTest() 
    {
        //TODO rehacer este test
    }

    /**
     * Prueba para desasociar un Ticket existente de un SitioWeb existente
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void removeTicketsTest() throws BusinessLogicException 
    {
       //TODO rehacer este test
    }
    
}

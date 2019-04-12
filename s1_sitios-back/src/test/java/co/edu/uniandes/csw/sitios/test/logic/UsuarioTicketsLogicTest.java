/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.UsuarioTicketsLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import co.edu.uniandes.csw.sitios.persistence.UsuarioPersistence;
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
 * @author Allan Roy Corinaldi
 */
@RunWith(Arquillian.class)
public class UsuarioTicketsLogicTest {

    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketsLogicTest.class.getName());

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TicketLogic tLogic;

    @Inject
    UsuarioTicketsLogic uTLogic;

    @Inject
    UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UsuarioEntity userE;

    private final List<TicketEntity> dataT = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(TicketEntity.class.getPackage())
                .addPackage(UsuarioTicketsLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(TicketPersistence.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from TicketEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            userE = factory.manufacturePojo(UsuarioEntity.class);
            userE.setTickets(new ArrayList<TicketEntity>());
            em.persist(userE);
        }
        for (int i = 0; i < 3; i++) {
            TicketEntity entityC = factory.manufacturePojo(TicketEntity.class);
            em.persist(entityC);
            dataT.add(entityC);
            userE.getTickets().add(entityC);
        }
        LOGGER.log(Level.INFO, "HOLA0 = {0}", userE.getTickets().get(0).getFecha());
    }

//    /**
//     * Prueba para crear un Usuario.
//     *
//     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
//     */
//    @Test
//    public void addTicketTest() throws BusinessLogicException {
//        dataT.get(0).setSitioAsociado(new SitioWebEntity());
//        dataT.get(0).setUsuarioAsociado(new UsuarioEntity());
//                LOGGER.log(Level.INFO, "HOLAAA= {0}", dataT.get(0));
//        TicketEntity result = tLogic.createTicket(dataT.get(0));
//        TicketEntity entity = userE.getTickets().get(0);
//        Assert.assertEquals(result.getId(), entity.getId());
//        Assert.assertEquals(result.getDescripcion(), entity.getDescripcion());
//        Assert.assertEquals(result.getEstado(), entity.getEstado());
//        Assert.assertEquals(result.getFecha(), entity.getFecha());
//    }

    /**
     * Prueba para consultar la lista de cambios de un administrador.
     */
    @Test
    public void getTicketsTest() {

        List<TicketEntity> list = uTLogic.getTickets(userE.getId());

        Assert.assertEquals(userE.getTickets().size(), list.size());
        for (int i = 0; i < dataT.size(); i++) {
            Assert.assertTrue(list.contains(userE.getTickets().get(i)));
        }
    }

    /**
     * Prueba para consultar una cambio de un admin.
     *
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @Test
    public void getTicketTest() throws BusinessLogicException {

        TicketEntity ticketE = dataT.get(0);
        TicketEntity resultCambio = uTLogic.getTicket(userE.getId(), ticketE.getId());
        Assert.assertNotNull(resultCambio);
        LOGGER.log(Level.INFO, "HOLA1 = {0}", ticketE.getFecha());
        LOGGER.log(Level.INFO, "HOLA2 = {0}", resultCambio.getFecha());
        Assert.assertEquals(ticketE, resultCambio);
        Assert.assertEquals(ticketE.getId(), resultCambio.getId());
        Assert.assertEquals(ticketE.getDescripcion(), resultCambio.getDescripcion());
        Assert.assertEquals(ticketE.getEstado(), resultCambio.getEstado());
        Assert.assertEquals(ticketE.getFecha().getDay(), resultCambio.getFecha().getDay());
        Assert.assertEquals(ticketE.getFecha().getMonth(), resultCambio.getFecha().getMonth());
        Assert.assertEquals(ticketE.getFecha().getYear(), resultCambio.getFecha().getYear());

    }

    /**
     * Prueba desasociar una ticjet con un usuario.
     *
     */
    @Test
    public void removeCambioTest() {
        for (TicketEntity ticket : dataT) {
            uTLogic.removeTicket(userE.getId(), ticket.getId());
        }
        Assert.assertTrue(uTLogic.getTickets(userE.getId()).isEmpty());
    }

}

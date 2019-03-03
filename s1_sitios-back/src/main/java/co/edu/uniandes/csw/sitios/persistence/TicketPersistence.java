/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class TicketPersistence {

    private static final Logger LOGGER = Logger.getLogger(TicketPersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param ticketEntity objeto ticket que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TicketEntity create(TicketEntity ticketEntity) {
        LOGGER.log(Level.INFO, "Creando un ticket nuevo");
        em.persist(ticketEntity);
        LOGGER.log(Level.INFO, "ticket creado");

        return ticketEntity;
    }

    /**
     * Devuelve todos los tickets de la base de datos.
     *
     * @return una lista con todos los tickets que encuentre en la base de
     * datos, "select u from TicketEntity u" es como un "select * from
     * TicketEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<TicketEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los tickets");
        Query q = em.createQuery("select u from TicketEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun ticket con el id que se envía de argumento
     *
     * @param ticketId: id correspondiente al ticket buscado.
     * @return un ticket.
     */
    public TicketEntity find(Long ticketId) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", ticketId);
        return em.find(TicketEntity.class, ticketId);
    }

    /**
     * Actualiza un usuario.
     *
     * @param TicketEntity: el ticket que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un ticket con los cambios aplicados.
     */
    public TicketEntity update(TicketEntity ticketEntity) {
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", ticketEntity.getId());
        return em.merge(ticketEntity);
    }
    
    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id del
     * usuario
     *
     * @param ticketId: id correspondiente al usuario a borrar.
     */
    public void delete(Long ticketId) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id={0}", ticketId);
        TicketEntity ticketEntity = em.find(TicketEntity.class, ticketId);
        em.remove(ticketEntity);
    }
    
    /**
     * Busca si hay algun ticket con el id que se envía de argumento
     *
     * @param id: id de la ticket que se está buscando
     * @return null si no existe ningun ticket con el id del argumento. Si
     * existe alguno devuelve el primero.
     */
    public TicketEntity findByid(String id) {
        LOGGER.log(Level.INFO, "Consultando usuario por id ", id);
        // Se crea un query para buscar usuarios con el id que recibe el método como argumento. ":id" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.id = :id", TicketEntity.class);
        // Se remplaza el placeholder ":id" con el valor del argumento 
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<TicketEntity> sameID = query.getResultList();
        TicketEntity result;
        if (sameID == null) {
            result = null;
        } else {
            result = sameID.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar usuarios por id ", id);
        return result;
    }

}

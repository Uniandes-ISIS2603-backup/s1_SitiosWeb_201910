/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
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
public class UsuarioPersistence {

    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param usuarioEntity objeto usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Creando un usuario nuevo");
        em.persist(usuarioEntity);
        LOGGER.log(Level.INFO, "usuario creado");

        return usuarioEntity;
    }

    /**
     * Devuelve todos los usuarios de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from UsuarioEntity u" es como un "select * from UsuarioEntity;"
     * - "SELECT * FROM table_name" en SQL.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param usuarioId: id correspondiente al usuario buscado.
     * @return un libro.
     */
    public UsuarioEntity find(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", usuarioId);
        return em.find(UsuarioEntity.class, usuarioId);
    }

    /**
     * Actualiza un usuario.
     *
     * @param usuarioEntity: el usuario que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un usuario con los cambios aplicados.
     */
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", usuarioEntity.getId());
        return em.merge(usuarioEntity);
    }

    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id del
     * usuario
     *
     * @param usuarioId: id correspondiente al usuario a borrar.
     */
    public void delete(Long usuarioId) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id={0}", usuarioId);
        UsuarioEntity usuarioEntity = em.find(UsuarioEntity.class, usuarioId);
        em.remove(usuarioEntity);
    }

    /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param id: id de la editorial que se está buscando
     * @return null si no existe ningun usuario con el id del argumento. Si
     * existe alguno devuelve el primero.
     */
    public UsuarioEntity findByid(String id) {
        LOGGER.log(Level.INFO, "Consultando usuario por id ", id);
        // Se crea un query para buscar usuarios con el id que recibe el método como argumento. ":id" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.id = :id", UsuarioEntity.class);
        // Se remplaza el placeholder ":id" con el valor del argumento 
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameID = query.getResultList();
        UsuarioEntity result;
        if (sameID == null) {
            result = null;
        } else {
            result = sameID.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar usuarios por id ", id);
        return result;
    }
}

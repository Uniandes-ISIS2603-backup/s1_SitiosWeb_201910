    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.ballesteros
 */
@Stateless
public class CambioLogic {

    private static final Logger LOGGER = Logger.getLogger(CambioLogic.class.getName());

    @Inject
    private CambioPersistence persistence;

    // El metodo recibe editorial entity porque back no conoce a los DTOS
    public CambioEntity createCambio(CambioEntity cambioEntidad) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Creando un Cambio nuevo");
        ///////                    Reglas De Negocio                  ////////
        verificarReglasDeNegocio(cambioEntidad);
        //Invoco a la persistencia para crear a la plataforma
        persistence.create(cambioEntidad);
        LOGGER.log(Level.FINE, "Cambio creado");
        return cambioEntidad;
    }

    public CambioEntity getCambio(Long id) throws BusinessLogicException 
    {
        return persistence.find(id);
    }

    public CambioEntity updateCambio(Long Id, CambioEntity cambioEntity) throws BusinessLogicException {
        verificarReglasDeNegocio(cambioEntity);
        CambioEntity newEntity = persistence.update(cambioEntity);

        return newEntity;
    }

    public List<CambioEntity> getCambios() {

        List<CambioEntity> editorials = persistence.findAll();

        return editorials;
    }
    
    public List<CambioEntity> getCambiosFiltro(String atribute, String parameter){
        List<CambioEntity> filtro = persistence.findBy(atribute, parameter);

        return filtro;
    }

    public void deleteCambio(Long id) {

        persistence.delete(id);
    }
    
    public void verificarReglasDeNegocio(CambioEntity cambioEntidad) throws BusinessLogicException
    {
        String lugarCambio = cambioEntidad.getLugarCambio();

        //ip = no puede ser null
        if (lugarCambio == null) {
            throw new BusinessLogicException("El lugar que se esta buscando no puede ser nulo");
        }

        //2.lugarCambio != ""
        if (lugarCambio.equals("")) {
            throw new BusinessLogicException("El lugar no puede ser vacio");
        }

        String descripcion = cambioEntidad.getDescripcion();

        //4.descripcion != null
        if (descripcion == null) {
            throw new BusinessLogicException("La descripcion no puede ser nula");
        }
        //5.descripcion != ""
        if (descripcion.equals("")) {
            throw new BusinessLogicException("La descripcion no puede estar vacia");
        }

        Long idAsociado = cambioEntidad.getIdAsociado();

        //6.idAsociado != null
        if (idAsociado == null) {
            throw new BusinessLogicException("El idAsociado no puede ser nulo");
        }
        /*
        //idAsociado != ""
        if(idAsociado==0){
            throw new BusinessLogicException("El idAsociado es vacia");
        }
         */
        Date fechaCambio = cambioEntidad.getFechaCambio();

        //9.fechaCambio != null
        if (fechaCambio == null) {
            throw new BusinessLogicException("La fechaCambio no puede ser nula");
        }

        //10.fechaCambio != debe ser mayor a la fecha actual
        //Esto se verifica en el podam del atributo en entities
        String previo = cambioEntidad.getPrevio();

        //11.previo != null
        if (previo == null) {
            throw new BusinessLogicException("Previo no puede ser nulo");
        }

        String nuevo = cambioEntidad.getNuevo();

        //12. nuevo != null
        if (nuevo == null) {
            throw new BusinessLogicException("Nuevo no puede ser nulo");
        }
    }
}

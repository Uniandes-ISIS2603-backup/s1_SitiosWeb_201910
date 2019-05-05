/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link EstadoWebDTO} con el fin de manejar las relaciones
 * entre los estadosWebDTO y otros DTO del mundo del problema
 * @author Daniel Preciado
 */
public class EstadoWebDetailDTO extends EstadoWebDTO implements Serializable{
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    // relación  cero o muchos notificaciones 
    private List<NotificacionDTO> notificaciones;


    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________
    public EstadoWebDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param estadoWebEntity La entidad de la cual se construye el DTO
     */
    public EstadoWebDetailDTO(EstadoWebEntity estadoWebEntity) {
        super(estadoWebEntity);
        if (estadoWebEntity.getNotificaciones()!= null) 
        {
            notificaciones = new ArrayList<>();
            for (NotificacionEntity notificacionEntity : estadoWebEntity.getNotificaciones()) 
            {
                notificaciones.add(new NotificacionDTO(notificacionEntity));
            }
        }
    }

    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el EstadoWeb.
     */
    @Override
    public EstadoWebEntity toEntity() 
    {
        EstadoWebEntity estadoWebEntity = super.toEntity();
        if (notificaciones != null) 
        {
            List<NotificacionEntity> notificacionesEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificcacion : getNotificaciones())
            {
                notificacionesEntity.add(dtoNotificcacion.toEntity());
            }
            estadoWebEntity.setNotificaciones(notificacionesEntity);
        }

        return estadoWebEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este EstadoWeb
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<NotificacionDTO> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Modifica las reseñas de este EstadoWeb.
     *
     * @param notificaciones Las nuevas reseñas
     */
    public void setReviews(List<NotificacionDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity.estado;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que representa un Estado web 
 * en un determinado momento de tiempo
 * @author Daniel Preciado
 */
public class EstadoWebDTO implements Serializable{
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    /**
     * id de un estado web
     */
    private Long id;
    
    /**
     * represnta el estado del sitio web
     */
    private estado estado;
    
    /**
     * descripcion general del estado del sitio
     */
    private String descripcion;
    
    /**
     * fecha en la que se actualizo el estado web
     */
    private Date fechaCambio;
    
    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________

    /**
     * Constructor por defecto para la clase
     * 
     */
    public EstadoWebDTO() {
    }

    /**
     * Convertir de Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pEstadoWebEntity: Es la entidad que se va a convertir a DTO
     */
    public EstadoWebDTO(EstadoWebEntity pEstadoWebEntity) 
    {
        if (pEstadoWebEntity != null) 
        {
            this.id = pEstadoWebEntity.getId();
            this.estado = pEstadoWebEntity.getEstado();
            this.descripcion = pEstadoWebEntity.getDescripcion();
            this.fechaCambio = pEstadoWebEntity.getFechaCambio();
            
        }
    }
    
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    /**
     * Devuelve el ID del estado Web.
     *
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica el ID del estado Web.
     *
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devuelve el estado correspondiente al Sitio web.
     * 
     * @return estado
     */
    public estado getEstado() 
    {
        return estado;
    }

    /**
     * Modifica el estado corrrespondiente al sitio web.
     *
     * @param estado the estado to set
     */
    public void setEstado(estado estado) 
    {
        this.estado = estado;
    }
   
    
    /**
     * Devuelve la descripcion correspondiente al estado del Sitio web.
     *
     * @return the descripcion
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * Modifica la descripcion al corrrespondiente estado del sitio web.
     *
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }
    
     /**
     * Devuelve la fecha correspondiente al estado del Sitio web.
     *
     * @return the fechaCambio
     */
    public Date getFechaCambio() 
    {
        return fechaCambio;
    }

    /**
     * Modifica la fecha al corrrespondiente estado del sitio web.
     *
     * @param fechaCambio the fechaCambio to set
     */
    public void setFechaCambio(Date fechaCambio) 
    {
        this.fechaCambio = fechaCambio;
    }

    /**
     * convierte el objeto DTO a entity
     * @return un estadoWebEntity con los valores del DTO
     */
    public EstadoWebEntity toEntity()
    {
        EstadoWebEntity retorno = new EstadoWebEntity();
        retorno.setId(this.id);
        retorno.setEstado(this.estado);
        retorno.setDescripcion(this.descripcion);
        retorno.setFechaCambio(this.fechaCambio);
        return retorno;
    }
    
    /**
     * se sobreescribe el to string
     * 
     * @return 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TicketDTO implementa Serializable
 * @author  Daniel Preciado / Allan Corinaldi 
 */
public class TicketDTO implements Serializable{

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * id de un ticket.
     */
    private Long id;
    
    /**
     * descripcion del ticket.
     */
    private String descripcion;
    
    /**
     * fecha en la que se genero el ticket.
     */
    private Date fecha;
    
    /**
     * estado del ticket, tiene 3: 1, 2, 3.
     */
    private Integer estado;
    
    /**
     * Relación a un usuario  
     * dado que esta tiene cardinalidad 1.
     */
    private UsuarioDTO usuarioAsociado;

    
    /**
     * Relación a un sitioWeb  
     * dado que esta tiene cardinalidad 1.
     */
    private SitioWebDTO sitioAsociado;
    
    
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
    /**
     * Constructor TicketDTO vacio.
     */
    public TicketDTO()
    {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public TicketDTO( TicketEntity entity )
    {
        if(entity != null) 
        {
            this.id = entity.getId();
            this.descripcion = entity.getDescripcion();
            this.estado = entity.getEstado();
            this.fecha = entity.getFecha();
            
            if (entity.getSitioAsociado()!= null) 
            {
                this.sitioAsociado = new SitioWebDTO(entity.getSitioAsociado());
            } else 
            {
                this.sitioAsociado = null;
            }
            if (entity.getUsuarioAsociado()!= null) 
            {
                this.usuarioAsociado = new UsuarioDTO(entity.getUsuarioAsociado());
            } else 
            {
                this.usuarioAsociado = null;
            }
        }
    }
    
    /**
     * obtiene el id de u  ticket
     * @return id 
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * asigna el id de un ticket
     * @param id id to set
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    /**
     * obtiene la descripcion de un ticket
     * @return the descripcion
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * asigna la descripcion a un ticket
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    /**
     * obtiene la fecha de un ticket
     * @return the fecha
     */
    public Date getFecha()
    {
        return fecha;
    }

    /**
     * asigna la fecha de un ticket
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) 
    {
        this.fecha = fecha;
    }

    /**
     * obtiene el estado de un ticket
     * @return the estado
     */
    public Integer getEstado() 
    {
        return estado;
    }

    /**
     * asigna el estado de un ticket
     * @param estado the estado to set
     */
    public void setEstado(int estado)
    {
        this.estado = estado;
    }
    
    /**
     * obtiene el sitio al cual esta asociado el ticket
     * @return the sitioAsociado
     */
    public SitioWebDTO getSitioAsociado()
    {
        return sitioAsociado;
    }

    /**
     * asigna el sitio al cual se asocia el ticket
     * @param sitioAsociado the sitioAsociado to set
     */
    public void setSitioAsociado(SitioWebDTO sitioAsociado) 
    {
        this.sitioAsociado = sitioAsociado;
    }
    
    /**
     * obtiene el usuario asociado a este ticket
     * @return usuarioAsociado
     */
    public UsuarioDTO getUsuarioAsociado() 
    {
        return usuarioAsociado;
    }

    /**
     * asigna el usuario asociado a este ticket
     * @param usuarioAsociado 
     */
    public void setUsuarioAsociado(UsuarioDTO usuarioAsociado) 
    {
        this.usuarioAsociado = usuarioAsociado;
    }
    
    /**
     * Convierte un DTO a Entity
     * 
     * @return TicketEntity con los valores del DTO
     */
    public TicketEntity toEntity() 
    {
        TicketEntity entity = new TicketEntity();
        entity.setId(this.id);
        entity.setDescripcion(this.descripcion);
        entity.setEstado(this.estado);
        entity.setFecha(this.fecha);
        
        if (this.sitioAsociado != null) 
        {
            entity.setSitioAsociado(this.sitioAsociado.toEntity());
        }
        if (this.usuarioAsociado != null) 
        {
            entity.setUsuarioAsociado(this.usuarioAsociado.toEntity());
        }
        return entity;
    }
    
    
    /**
     * se sobreescribe el to string
     * 
     * @return 
     */
     @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}

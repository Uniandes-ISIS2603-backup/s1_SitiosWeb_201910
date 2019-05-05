/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * Clase que representa la entidad de un ticket que va a permitir ser
 * persistido y serializado
 * 
 * @author Daniel Preciado
 */
@Entity
public class TicketEntity extends BaseEntity  implements Serializable{

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * descripcion del ticket.
     */
    private String descripcion;

    /**
     * fecha que se saco el ticket, diferente de null.
     */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    /**
     * estado del ticket, tiene 3: 1, 2, 3.
     */
    @PodamIntValue(minValue = 1,maxValue = 3)
    private Integer estado; //Solo 3 estados

    /**
     * Usuario al cual pertenece el ticket.
     */
    @ManyToOne
    @PodamExclude
    private UsuarioEntity usuarioAsociado;
    
    /**
     * sitio al que corresponde el estado web.
     */
    @ManyToOne
    @PodamExclude
    private SitioWebEntity sitioAsociado;


    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Constructor TicketEntity vacio.
     */
    public TicketEntity() 
    {
    }

    /**
     * Devuelve la descripcion del ticket
     * 
     * @return the descripcion
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * Asigna la descripcion asignada al ticket
     * 
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha en la que se genero el ticket
     * 
     * @return the fecha
     */
    public Date getFecha() 
    {
        return fecha;
    }

    /**
     * Establece la fecha en la que se genero el ticket
     * 
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) 
    {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado del ticket 
     * el valor corresponde a 1|2|3
     * 
     * @return the estado
     */
    public Integer getEstado() 
    {
        return estado;
    }

    /**
     * Asigna el estado del ticket
     * el valor corresponde a 1|2|3
     * 
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) 
    {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario asociado al ticket
     * 
     * @return the usuarioAsociado
     */
    public UsuarioEntity getUsuarioAsociado() 
    {
        return usuarioAsociado;
    }

    /**
     * Asigna el usuario asociado a un ticket
     * 
     * @param usuarioAsociado the usuarioAsociado to set
     */
    public void setUsuarioAsociado(UsuarioEntity usuarioAsociado) 
    {
        this.usuarioAsociado = usuarioAsociado;
    }
    
    /**
     * Obtiene el sitioWeb al cual esta asociado el ticket
     * 
     * @return  the sitioAsociado
     */
    public SitioWebEntity getSitioAsociado() 
    {
        return sitioAsociado;
    }

    /**
     * Asigna el sitioWeb asociado a un ticket
     * 
     * @param sitioAsociado  the sitioAsociado to set
     */
    public void setSitioAsociado(SitioWebEntity sitioAsociado) 
    {
        this.sitioAsociado = sitioAsociado;
    }
}

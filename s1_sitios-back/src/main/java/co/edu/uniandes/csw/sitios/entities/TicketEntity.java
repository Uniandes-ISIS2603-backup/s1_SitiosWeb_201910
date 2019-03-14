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
     * estado del ticket, tiene 3: 1, 2, 3
     */
    @PodamIntValue(minValue = 1,maxValue = 3)
    private Integer estado; //Solo 3 estados

    /**
     *
     */
    @javax.persistence.ManyToOne
    private UsuarioEntity usuarioEntity;
    
    /**
     * sitio al que corresponde el estado web
     */
    @ManyToOne
    @PodamExclude
    private SitioWebEntity sitioAsociado;



    /**
     * Constructor TicketEntity vacio
     */
    public TicketEntity() {
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the usuarioEntity
     */
    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    /**
     * @param usuarioEntity the usuarioEntity to set
     */
    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
    
        public SitioWebEntity getSitioAsociado() {
        return sitioAsociado;
    }

    public void setSitioAsociado(SitioWebEntity sitioAsociado) {
        this.sitioAsociado = sitioAsociado;
    }
}

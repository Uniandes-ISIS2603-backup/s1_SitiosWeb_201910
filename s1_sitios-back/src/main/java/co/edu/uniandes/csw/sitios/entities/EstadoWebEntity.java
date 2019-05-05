package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa la entidad de un estado web que va a permitir ser
 * persistido y serializado
 * 
 * @author Daniel Preciado
 */
@Entity
public class EstadoWebEntity extends BaseEntity implements Serializable {

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * enumerador correspondiente a los tipos de estado mas 
     * comunes para un sitio web
     */
    public enum estado
    {
        ACTIVO, INACTIVO, ENFALLA, OTROS
    }  
   
    /**
     * represnta el estado del sitio web
     */
    @Enumerated(EnumType.STRING)
    private estado estado;

    /**
     * descripcion general del estado del sitio
     */
    private String descripcion;

    /**
     * fecha en la que se actualizo el estado web
     */
    @Temporal(TemporalType.DATE)
    private Date fechaCambio;

    /**
     * sitio al que corresponde el estado web
     */
    @ManyToOne
    @PodamExclude
    private SitioWebEntity sitioAsociado;
    
    /**
     * lista de notificaciones asociadas a un cambio de estado
     */
    @PodamExclude
    @OneToMany(mappedBy = "cambioSitio", fetch = FetchType.LAZY)
    private List<NotificacionEntity> notificaciones;

    
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
 
    /**
     * modifica el tipo de estado del estado web
     * 
     * @param estado the estado to set
     */
    public void setEstado(estado estado) {
        this.estado = estado;
    }   
    
    
    /**
     * devuelve el tipo de estado del estado web
     * 
     * @return estado
     */
    public estado getEstado() {
        return estado;
    }
    
    /**
     * devuelve la descripcion del estado web
     * 
     * @return  descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * modifica la descripcion del estado web
     * 
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * devuelve la fecha de cambio del estado web
     * 
     * @return fechaCambio
     */
    public Date getFechaCambio() {
        return fechaCambio;
    }

    /**
     * modifica la fecha de cambio del estado web
     * 
     * @param fechaCambio the fechaCambio to set
     */
    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
    
    /**
     * devuelve el sitio asociado al estado Web
     * 
     * @return sitioAsociado
     */
    public SitioWebEntity getSitioAsociado() {
        return sitioAsociado;
    }

    /**
     * modifica el sitio asociado a un estado web
     * @param asociado  the sitioAsociado to set
     */
    public void setSitioAsociado( SitioWebEntity asociado) {
        this.sitioAsociado = asociado;
    }

    /**
     * obtiene la lista de notificaciones asociadas a un estado
     * @return notifiaciones
     */
    public List<NotificacionEntity> getNotificaciones() {
        return notificaciones;
    }

    /**
     * asigna la lista de notificaciones asociadas a un estado
     * @param notificaciones 
     */
    public void setNotificaciones(List<NotificacionEntity> notificaciones) {
        this.notificaciones = notificaciones;
    }
    


    
}

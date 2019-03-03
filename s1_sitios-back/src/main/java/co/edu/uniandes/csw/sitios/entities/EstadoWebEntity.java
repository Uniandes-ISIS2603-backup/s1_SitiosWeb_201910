package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    
    @ManyToMany
    @PodamExclude
    private List<SitioWebEntity> asociados;
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
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

    public List<SitioWebEntity> getAsociados() {
        return asociados;
    }

    public void setAsociados(List<SitioWebEntity> asociados) {
        this.asociados = asociados;
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

    


    
}

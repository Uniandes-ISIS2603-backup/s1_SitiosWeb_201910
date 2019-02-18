package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.Date;

public class EstadoWebEntity extends BaseEntity implements Serializable {
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
    //private Estado estado;

    /**
     * descripcion general del estado del sitio
     */
    private String descripcion;

    /**
     * fecha en la que se actualizo el estado web
     */
    private Date fechaCambio;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public  EstadoWebEntity()
    {

    }

}

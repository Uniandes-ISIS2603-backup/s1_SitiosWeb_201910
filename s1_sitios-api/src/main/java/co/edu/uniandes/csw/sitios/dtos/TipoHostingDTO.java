package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.dtos.PlataformaDeDespliegueDTO;
import java.io.Serializable;

/**
 *
 * @author s.ballesteros
 */
public class TipoHostingDTO implements Serializable {
    
/*
* Nombre del tipoHosting
*/    
private String nombre;

/*
* Categoria del tipoHosting
*/
private String categoria;

/*
* plataforma de despliegue asociada a un tipo hosting
*/
private PlataformaDeDespliegueDTO plataformaDeDespliegue;

/*
* Constructor vacio de tipoHosting
*/
public TipoHostingDTO(){
    
}

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }
 
    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the plataformaDeDespliegue
     */
    public PlataformaDeDespliegueDTO getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    /**
     * @param plataformaDeDespliegue the plataformaDeDespliegue to set
     */
    public void setPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }
    
}


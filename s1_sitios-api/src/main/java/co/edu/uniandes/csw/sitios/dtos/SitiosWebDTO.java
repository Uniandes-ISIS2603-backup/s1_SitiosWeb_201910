/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class SitiosWebDTO implements Serializable {
    
    private int id;
    private String nombre;
    private String descripcion;
    private String proposito;
    private int audienciaEsperada;
    private Date fechaLanzamiento;
    private String[] urlsExternas;
    private String imagen;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the proposito
     */
    public String getProposito() {
        return proposito;
    }

    /**
     * @param proposito the proposito to set
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * @return the audienciaEsperada
     */
    public int getAudienciaEsperada() {
        return audienciaEsperada;
    }

    /**
     * @param audienciaEsperada the audienciaEsperada to set
     */
    public void setAudienciaEsperada(int audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    /**
     * @return the fechaLanzamiento
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * @param fechaLanzamiento the fechaLanzamiento to set
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * @return the urlsExternas
     */
    public String[] getUrlsExternas() {
        return urlsExternas;
    }

    /**
     * @param urlsExternas the urlsExternas to set
     */
    public void setUrlsExternas(String[] urlsExternas) {
        this.urlsExternas = urlsExternas;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}

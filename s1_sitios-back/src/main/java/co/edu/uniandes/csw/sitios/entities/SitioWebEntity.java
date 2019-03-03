/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import uk.co.jemos.podam.common.PodamLongValue;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author estudiante
 */
@Entity
public class SitioWebEntity extends BaseEntity implements Serializable {

    /**
     * nombre del sitio web nomre != "" && nombre !=null
     */
    private String nombre;

    /**
     * descripcion del sitio
     */
    @PodamStringValue(length = 20)
    private String descripcion;

    /**
     * proposito del sitio
     */
    private String proposito;

    /**
     * audiencia esperada del sitio web
     */
    @PodamLongValue(minValue = 0)
    private long audienciaEsperada;

    /**
     * fecha de publicacion del sitio web
     */
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;

    /**
     * ruta a la imagen representativa del sitio
     */
    private String imagen;

    /**
     * estado actual del sitio web
     */
    /**
     * Categoria a la cual pertenece el sitio
     */
    @Enumerated(EnumType.STRING)
    private Categoria categoriaSitio;


    /**
     * Lugar donde se encuentra desplegado el sitio web
     */
    //TODO asignar multiplicidad
    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private PlataformaDeDespliegueEntity plataformaDeDespliegue;

    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)//(targetEntity = NotificacionEntity.class)((fetch=FetchType.LAZY)
    private NotificacionEntity notificacion;

    /**
     * Tecnologias usadas en el desarrollo del sitio
     */
    @PodamExclude
    @ManyToMany
    private List<TecnologiaEntity> technologies;

    /**
     * Personas que solicitaron el sitio web
     */
    @PodamExclude
    @ManyToMany
    private List<AdministradorEntity> administradores;

    /**
     * Sitios web que estan asociados a este
     */
    @PodamExclude
    @ManyToMany
    private List<SitioWebEntity> sitiosRelacionados;

    /**
     * historial completo de estados que ha tenido este sitio
     */
    @PodamExclude
    @ManyToMany
    private List<EstadoWebEntity> historialDeEstados;

    public SitioWebEntity() {
    }

    public NotificacionEntity getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(NotificacionEntity notificacion) {
        this.notificacion = notificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public long getAudienciaEsperada() {
        return audienciaEsperada;
    }

    public void setAudienciaEsperada(long audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Categoria getCategoriaSitio() {
        return categoriaSitio;
    }

    public void setCategoriaSitio(Categoria categoriaSitio) {
        this.categoriaSitio = categoriaSitio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

 

    public List<EstadoWebEntity> getHistorialDeEstados() {
        return historialDeEstados;
    }

    public void setHistorialDeEstados(List<EstadoWebEntity> historialDeEstados) {
        this.historialDeEstados = historialDeEstados;
    }

    public PlataformaDeDespliegueEntity getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    public void setPlataformaDeDespliegue(PlataformaDeDespliegueEntity plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }

    public List<TecnologiaEntity> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TecnologiaEntity> technologies) {
        this.technologies = technologies;
    }

    public List<AdministradorEntity> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<AdministradorEntity> administradores) {
        this.administradores = administradores;
    }

    public List<SitioWebEntity> getSitiosRelacionados() {
        return sitiosRelacionados;
    }

    public void setSitiosRelacionados(List<SitioWebEntity> sitiosRelacionados) {
        this.sitiosRelacionados = sitiosRelacionados;
    }

    /**
     * Categoria que puede tener un sitio web
     */
    public enum Categoria {
        ADMINISTRATIVO,
        INFORMATIVO,
        ACADEMICO,
        OTRO
    }

}

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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamLongValue;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author estudiante
 */
@Entity
public class SitioWebEntity extends BaseEntity implements Serializable {
    
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * Categoria que puede tener un sitio web.
     */
    public enum Categoria {
        ADMINISTRATIVO,
        INFORMATIVO,
        ACADEMICO,
        OTRO
    }

    @PodamStringValue(strValue = "http://www.test.com")
    private String url;
    /**
     * nombre del sitio web nomre != "" && nombre !=null.
     */
    private String nombre;

    /**
     * descripcion del sitio.
     */
    @PodamStringValue(length = 20)
    private String descripcion;

    /**
     * proposito del sitio.
     */
    private String proposito;

    /**
     * audiencia esperada del sitio web.
     */
    @PodamLongValue(minValue = 0)
    private long audienciaEsperada;

    /**
     * fecha de publicacion del sitio web.
     */
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;

    /**
     * ruta a la imagen representativa del sitio.
     */
    @PodamStringValue(strValue = "http://www.test.com/test/test.png")
    private String imagen;


    /**
     * Categoria a la cual pertenece el sitio.
     */
    @Enumerated(EnumType.STRING)
    private Categoria categoriaSitio;


    /**
     * Lugar donde se encuentra desplegado el sitio web.
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private PlataformaDeDespliegueEntity plataformaDeDespliegue;


    /**
     * Tecnologias usadas en el desarrollo del sitio.
     */
    @PodamExclude
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<TecnologiaEntity> technologies;

    /**
     * Personas que administran el sitio web.
     */
    @PodamExclude
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<AdministradorEntity> administradores;

    /**
     * Sitios web que estan asociados a este.
     */
    @PodamExclude
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY )
    private List<SitioWebEntity> sitiosRelacionados;

    /**
     * historial completo de estados que ha tenido este sitio.
     * @auhtor Daniel Preciado
     */
    @PodamExclude
    @OneToMany(mappedBy = "sitioAsociado",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<EstadoWebEntity> estadosWeb;
    
     /**
     * Tickets que ha tenido este sitio.
     * @auhtor Daniel Preciado
     */
    @PodamExclude
    @OneToMany(mappedBy = "sitioAsociado",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TicketEntity> ticketsSitio;
    
    /**
     * notificaciones que ha tenido este sitio.
     * @auhtor Daniel Preciado
     */
    @PodamExclude
    @OneToMany(mappedBy = "sitioWeb",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<NotificacionEntity> notificaciones;

    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
    /**
     * obtiene la URL del sitioWeb
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * asigna la URL del sitioWeb
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url;
    }    

    /**
     * obtiene el nombre del sitioWeb
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * asigna el nombre del sitioWeb
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * obtiene la descripcion del sitioWeb
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * asigna la descripcion del sitioWeb
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * obtiene el proposito del sitioWeb
     * @return proposito
     */
    public String getProposito() {
        return proposito;
    }
    
    /**
     * asigna el proposito del sitioWeb
     * @param proposito 
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * obtiene la audiencia esperada para el sitioWeb
     * @return audienciaEsperada
     */
    public long getAudienciaEsperada() {
        return audienciaEsperada;
    }
    
    /**
     * asigna la audiencia esperada para el sitioWeb
     * @param audienciaEsperada 
     */
    public void setAudienciaEsperada(long audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    /**
     * obtiene la fecha de lanzamiento del sitioWeb
     * @return fechaLanzamiento
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * asigna la fecha de lanzamiento de un  sitioWeb
     * @param fechaLanzamiento 
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * obtiene la categoria del sitioWeb
     * @return categoriaSitio
     */
    public Categoria getCategoriaSitio() {
        return categoriaSitio;
    }
    
    /**
     * asigna la categoria del sitioWeb
     * @param categoriaSitio 
     */
    public void setCategoriaSitio(Categoria categoriaSitio) {
        this.categoriaSitio = categoriaSitio;
    }

    /**
     * obtiene la imagen representativa del sitioWeb
     * @return imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * asigna la imagen representativa del sitioWeb
     * @param imagen 
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * devuelve los estados web que ha tenido  un sitio
     * @return 
     */
    public List<EstadoWebEntity> getEstadosWeb() 
    {
        return estadosWeb;
    }

    /**
     * modifica los estados web de un sitio
     * @param estadosWeb 
     */
    public void setEstadosWeb(List<EstadoWebEntity> estadosWeb)
    {
        this.estadosWeb = estadosWeb;
    }
    
    /**
     * devuelve los ticket generados para el sitio
     * @return 
     */
    public List<TicketEntity> getTicketsSitio() 
    {
        return ticketsSitio;
    }

    /**
     * modifica los ticket asignados a un sitio
     * @param ticketsSitio 
     */
    public void setTicketsSitio(List<TicketEntity> ticketsSitio) 
    {
        this.ticketsSitio = ticketsSitio;
    }
    
    /**
     * obtiene las notificaciones asociadas a un sitioWeb
     * @return notificaciones
     */
    public List<NotificacionEntity> getNotificaciones() {
        return notificaciones;
    }
    
    /**
     * asigna las notificaciones asociadas a un sitioWeb
     * @param notificaciones 
     */
    public void setNotificaciones(List<NotificacionEntity> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * obtiene la plataforma donde esta desplegado el sitioWeb
     * @return plataformaDeDespliegue
     */
    public PlataformaDeDespliegueEntity getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    /**
     * asigna la plataforma donde esta desplegado el sitioWeb
     * @param plataformaDeDespliegue 
     */
    public void setPlataformaDeDespliegue(PlataformaDeDespliegueEntity plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }

    /**
     * obtiene las tecnologias utilizadas en el desarrollo del sitioWeb
     * @return technologies
     */
    public List<TecnologiaEntity> getTechnologies() {
        return technologies;
    }

    /**
     * asigna las tecnologias utilizadas en el desarrollo del sitioWeb
     * @param technologies 
     */
    public void setTechnologies(List<TecnologiaEntity> technologies) {
        this.technologies = technologies;
    }

    /**
     * obtiene la lista de administradores del sitioWeb
     * @return administradores
     */
    public List<AdministradorEntity> getAdministradores() {
        return administradores;
    }

    /**
     * asigna la lista de administradores del sitioWeb
     * @param administradores 
     */
    public void setAdministradores(List<AdministradorEntity> administradores) {
        this.administradores = administradores;
    }

    /**
     * obtiene la lista de sitiosWeb relacionados a este
     * @return sitiosRelacionados
     */
    public List<SitioWebEntity> getSitiosRelacionados() {
        return sitiosRelacionados;
    }

    /**
     * asigna  la lista de sitiosWeb relacionados a este
     * @param sitiosRelacionados 
     */
    public void setSitiosRelacionados(List<SitioWebEntity> sitiosRelacionados) {
        this.sitiosRelacionados = sitiosRelacionados;
    }
    

}

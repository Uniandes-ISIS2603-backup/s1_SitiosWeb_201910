/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Albert Adolfo Molano Cubillos
 */
public class SitioWebDTO  implements Serializable{

    	/**
	 * identificador unico
	 */
	private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public EstadoWebDTO getEstadoActual() {
        return EstadoActual;
    }

    public void setEstadoActual(EstadoWebDTO EstadoActual) {
        this.EstadoActual = EstadoActual;
    }

    public List<EstadoWebDTO> getHistorialDeEstados() {
        return historialDeEstados;
    }

    public void setHistorialDeEstados(List<EstadoWebDTO> historialDeEstados) {
        this.historialDeEstados = historialDeEstados;
    }

    public PlataformaDeDespliegueDTO getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    public void setPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }

    public List<TecnologiaDTO> getTecnologiasDeDesarrollo() {
        return tecnologiasDeDesarrollo;
    }

    public void setTecnologiasDeDesarrollo(List<TecnologiaDTO> tecnologiasDeDesarrollo) {
        this.tecnologiasDeDesarrollo = tecnologiasDeDesarrollo;
    }

    public List<AdministradorDTO> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(List<AdministradorDTO> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public List<SitioWebDTO> getSitiosRelacionados() {
        return sitiosRelacionados;
    }

    public void setSitiosRelacionados(List<SitioWebDTO> sitiosRelacionados) {
        this.sitiosRelacionados = sitiosRelacionados;
    }

    public List<AdministradorDTO> getSoportes() {
        return soportes;
    }

    public void setSoportes(List<AdministradorDTO> soportes) {
        this.soportes = soportes;
    }

    public AdministradorDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(AdministradorDTO responsable) {
        this.responsable = responsable;
    }

	/**
	 * nombre del sitio web
         * nomre != "" && nombre !=null
	 */
	private String nombre;

	/**
	 * descripcion del sitio
         */
	private String descripcion;

	/**
	 * proposito del sitio
	 */
	
	private String proposito;

	/**
	 * audiencia esperada del sitio web
	 */
	private long audienciaEsperada;

	/**
	 * fecha de publicacion del sitio web
	 */
	private Date fechaLanzamiento;

	/**
	 * Categoria a la cual pertenece el sitio
	 */
	private Categoria categoriaSitio;

	/**
	 * ruta a la imagen representativa del sitio
	 */
	
	private String imagen;

	/**
	 * estado actual del sitio web
	 */
	
	private EstadoWebDTO EstadoActual;

	/**
	 * historial completo de estados que ha tenido este sitio
	 */
	private List<EstadoWebDTO> historialDeEstados;

	/**
	 * Lugar donde se encuentra desplegado el sitio web
	 */
	private PlataformaDeDespliegueDTO plataformaDeDespliegue;

	/**
	 * Tecnologias usadas en el desarrollo del sitio
	 */
	private List<TecnologiaDTO> tecnologiasDeDesarrollo;

	/**
	 * Personas que solicitaron el sitio web
	 */
	private List<AdministradorDTO> solicitantes;

	/**
	 * Sitios web que estan asociados a este
	 */
	private List<SitioWebDTO> sitiosRelacionados;

	/**
	 * Personas encargadas del soporte del sitio
	 */
	private List<AdministradorDTO> soportes;

	/**
	 * Responsable del sitio web
	 */
	private AdministradorDTO responsable;
    
        /**
         * Categoria que puede tener un sitio web
         */
        public enum Categoria
        {
            Administrativo,
            Informativo,
            Academico,
            Otros
        }
        
        
        /**
         * Constructor por defecto
         */
        public SitioWebDTO()
        {
        }

        
        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity.TipoHosting;
import java.io.Serializable;

/**
 *
 * @author s.ballesteros
 */
public class PlataformaDeDespliegueDTO implements Serializable {

    /**
     * Id de la plataforma de despliegue.
     */
    private Long id;
    /**
     * Codigo ip correspondiente a la plataforma utilizada.
     */    
    private String ip;

    /**
     * Codigo de la CPU correspondiente a la plataforma utilizada.
     */    
    private String cpu;
    
    /**
     * Numero de cores la plataforma utilizada.
     */    
    private Integer cores;
    
    /**
     * Clock de la plataforma utilizada.
     */    
    private String clock;
    
    /**
     * Estado de la plataforma utilizada, virtualizada o no virtualizada.
     */    
    private Boolean virtualizacion;
    
    /*
    * Tipo de Hosting usado por la plataforma de despliegue.
    */
    private TipoHosting tipoHosting;
    

    
    /**
     * Constructor PlataformaDeDespliegueDTO vacio
     */
    public PlataformaDeDespliegueDTO() {
    }
    
     public PlataformaDeDespliegueDTO(PlataformaDeDespliegueEntity entidad) {
         setClock(entidad.getClock());
         setCores(entidad.getCores());
         setCpu(entidad.getCpu());
         setIp(entidad.getIp());
         setTipoHosting(entidad.getHosting());
         setId(entidad.getId());
         setVirtualizacion(entidad.getIsVirtualizacion());
      }
    //
    public PlataformaDeDespliegueEntity toEntity(){
        PlataformaDeDespliegueEntity entidad = new PlataformaDeDespliegueEntity();
        entidad.setClock(this.getClock());
        entidad.setCores(this.getCores());
        entidad.setCpu(this.getCpu());
        entidad.setIp(this.getIp());
        entidad.setHosting(this.getTipoHosting());
        entidad.setId(this.getId());
        entidad.setIsVirtualizacion(this.getVirtualizacion());
        return entidad;
    }
    
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the CPU
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * @param cpu the CPU to set
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the cores
     */
    public Integer getCores() {
        return cores;
    }

    /**
     * @param cores the cores to set
     */
    public void setCores(Integer cores) {
        this.cores = cores;
    }

    /**
     * @return the clock
     */
    public String getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(String clock) {
        this.clock = clock;
    }

    /**
     * @return the virtualizacion
     */
    public Boolean getVirtualizacion() {
        return virtualizacion;
    }

    /**
     * @param virtualizacion the virtualizacion to set
     */
    public void setVirtualizacion(Boolean virtualizacion) {
        this.virtualizacion = virtualizacion;
    }

    /**
     * @return the tipoHosting
     */
    public TipoHosting getTipoHosting() {
        return tipoHosting;
    }

    /**
     * @param tipoHosting the tipoHosting to set
     */
    public void setTipoHosting(TipoHosting tipoHosting) {
        this.tipoHosting = tipoHosting;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}

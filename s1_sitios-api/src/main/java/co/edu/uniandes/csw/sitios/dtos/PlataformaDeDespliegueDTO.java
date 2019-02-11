/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;

/**
 *
 * @author s.ballesteros
 */
public class PlataformaDeDespliegueDTO implements Serializable {

    /**
     * Codigo ip correspondiente a la plataforma utilizada.
     */    
    private String ip;

    /**
     * Codigo de la CPU correspondiente a la plataforma utilizada.
     */    
    private String CPU;
    
    /**
     * Numero de cores la plataforma utilizada.
     */    
    private Integer cores;
    
    /**
     * Clock de la plataforma utilizada.
     */    
    private Double clock;
    
    /**
     * El servicio de hosting que tiene la plataforma utilizada. 
     */    
    private String servicioDeHosting;
    
    /**
     * Estado de la plataforma utilizada, virtualizada o no virtualizada.
     */    
    private Boolean virtualizacion;
    
    /*
    * Tipo de Hosting usado por la plataforma de despliegue.
    */
    private TipoHostingDTO tipoHosting;

    
    /**
     * Constructor PlataformaDeDespliegueDTO vacio
     */
    public PlataformaDeDespliegueDTO() {
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
    public String getCPU() {
        return CPU;
    }

    /**
     * @param CPU the CPU to set
     */
    public void setCPU(String CPU) {
        this.CPU = CPU;
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
    public Double getClock() {
        return clock;
    }

    /**
     * @param clock the clock to set
     */
    public void setClock(Double clock) {
        this.clock = clock;
    }

    /**
     * @return the servicioDeHosting
     */
    public String getServicioDeHosting() {
        return servicioDeHosting;
    }

    /**
     * @param servicioDeHosting the servicioDeHosting to set
     */
    public void setServicioDeHosting(String servicioDeHosting) {
        this.servicioDeHosting = servicioDeHosting;
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
    public TipoHostingDTO getTipoHosting() {
        return tipoHosting;
    }

    /**
     * @param tipoHosting the tipoHosting to set
     */
    public void setTipoHosting(TipoHostingDTO tipoHosting) {
        this.tipoHosting = tipoHosting;
    }

}

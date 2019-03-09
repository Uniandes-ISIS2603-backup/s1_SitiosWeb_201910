/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author estudiante
 */
@Entity
public class PlataformaDeDespliegueEntity extends BaseEntity implements Serializable {
 
    /**
     * Codigo ip correspondiente a la plataforma utilizada.
     */    
    @PodamStringValue(strValue = "000.000.000.000", length = 15)
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
    @PodamStringValue(strValue = "15 GHz")
    private String clock;
    
    /**
     * El servicio de hosting que tiene la plataforma utilizada. 
     */    
    @Enumerated(EnumType.STRING)
    private TipoHosting hosting;
    
    /**
     * Estado de la plataforma utilizada, virtualizada o no virtualizada.
     */    
    private Boolean isVirtualizacion;
    
      /**
       * TipoHosting que puede ser una plataforma de despliegue
      */
        public enum TipoHosting
        {
            SAAS,
            PAAS,
            IAAS,
            ONPREMISE
        }
    /*
    * La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
    * plataforma de Despliegue    
    */
    @PodamExclude
    @OneToMany     (fetch = FetchType.LAZY)
    private List<SitioWebEntity> sitiosWeb = new ArrayList<>();
    
public PlataformaDeDespliegueEntity(){
        //Como se necesitan futuros constructores se deja uno vacio para evitar errores
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        
        this.ip = ip;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public TipoHosting getHosting() {
        return hosting;
    }

    public void setHosting(TipoHosting hosting) {
        this.hosting = hosting;
    }

    public Boolean getIsVirtualizacion() {
        return isVirtualizacion;
    }

    public void setIsVirtualizacion(Boolean isVirtualizacion) {
        this.isVirtualizacion = isVirtualizacion;
    }

    public List<SitioWebEntity> getSitiosWeb() {
        return sitiosWeb;
    }

    public void setSitiosWeb(List<SitioWebEntity> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class PlataformaDeDespliegueEntity extends BaseEntity implements Serializable {
 
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
            SaaS,
            PaaS,
            IaaS,
            OnPremise
        }
    
    public PlataformaDeDespliegueEntity(){
    }

   /*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
*/
@PodamExclude
@OneToMany(mappedBy = "plataformaDeDespliegue", cascade = CascadeType.PERSIST,orphanRemoval = false)
private ArrayList<SitioWebEntity> sitiosWeb = new ArrayList<SitioWebEntity>();
   
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
     * @return the virtualizacion
     */
    public Boolean getIsVirtualizacion() {
        return isVirtualizacion;
    }

    /**
     * @param virtualizacion the virtualizacion to set
     */
    public void setIsVirtualizacion(Boolean virtualizacion) {
        this.isVirtualizacion = virtualizacion;
    }

    /**
     * @return the hosting
     */
    public TipoHosting getHosting() {
        return hosting;
    }

    /**
     * @param hosting the hosting to set
     */
    public void setHosting(TipoHosting hosting) {
        this.hosting = hosting;
    }

     /**
     * @return the sitiosWeb
     */
    public ArrayList<SitioWebEntity> getSitiosWeb() {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb the sitiosWeb to set
     */
    public void setSitiosWeb(ArrayList<SitioWebEntity> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }
   
}

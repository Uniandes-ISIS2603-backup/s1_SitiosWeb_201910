package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SitioWebDetailDTO extends SitioWebDTO implements Serializable {
    /**
     * Tecnologias usadas en el desarrollo del sitio
     */
    private List<TecnologiaDTO> tecnologiasDeDesarrollo;

    /**
     * Personas que solicitaron el sitio web
     */
    private List<AdministradorDTO> administradores;

    /**
     * Sitios web que estan asociados a este
     */
    private List<SitioWebDTO> sitiosRelacionados;
    
    public SitioWebDetailDTO(SitioWebEntity entity)
    {
        super(entity);
        if(entity!=null)
        {
            if(entity.getTechnologies()!=null)
            {
                for(TecnologiaEntity tec :entity.getTechnologies())
                {
                tecnologiasDeDesarrollo.add(new TecnologiaDTO(tec));
                }
            }
            if(entity.getSolicitantes()!=null)
            {
                for(AdministradorEntity adm: entity.getSolicitantes())
                {
                    administradores.add(new AdministradorDTO(adm));
                }
            }
            if(entity.getSitiosRelacionados()!=null)
            {
                for(SitioWebEntity site:entity.getSitiosRelacionados())
                {
                    sitiosRelacionados.add(new SitioWebDTO(site));
                }

            }
           
        }
    }

    @Override
    public SitioWebEntity toEntity()
    {
        SitioWebEntity entity = super.toEntity();
        if(tecnologiasDeDesarrollo!=null)
        {
            List<TecnologiaEntity> tecEntity= new ArrayList<>();
            for(TecnologiaDTO tecs:tecnologiasDeDesarrollo)
            {
                tecEntity.add(tecs.toEntity());
            }
            entity.setTechnologies(tecEntity);
        }
        if(administradores!=null)
        {
            List<AdministradorEntity> admEntity= new ArrayList<>();
            for(AdministradorDTO adms: administradores)
            {
                admEntity.add(adms.toEntity());
            }
            entity.setSolicitantes(admEntity);
        }
        if(sitiosRelacionados!=null)
        {

        }
        
        return entity;
    }

    public List<TecnologiaDTO> getTecnologiasDeDesarrollo() {
        return tecnologiasDeDesarrollo;
    }

    public void setTecnologiasDeDesarrollo(List<TecnologiaDTO> tecnologiasDeDesarrollo) {
        this.tecnologiasDeDesarrollo = tecnologiasDeDesarrollo;
    }

    public List<AdministradorDTO> getSolicitantes() {
        return administradores;
    }

    public void setSolicitantes(List<AdministradorDTO> solicitantes) {

        this.administradores = solicitantes;
    }

    public List<SitioWebDTO> getSitiosRelacionados() {

        return sitiosRelacionados;
    }

    public void setSitiosRelacionados(List<SitioWebDTO> sitiosRelacionados) {
        this.sitiosRelacionados = sitiosRelacionados;
    }



    public void setSoportes(List<AdministradorDTO> soportes) {

        this.administradores = soportes;
    }

}

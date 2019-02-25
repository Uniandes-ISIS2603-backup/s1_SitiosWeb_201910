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
    private List<AdministradorDTO> solicitantes;

    /**
     * Sitios web que estan asociados a este
     */
    private List<SitioWebDTO> sitiosRelacionados;

    /**
     * Personas encargadas del soporte del sitio
     */
    private List<AdministradorDTO> soportes;

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
                    solicitantes.add(new AdministradorDTO(adm));
                }
            }
            if(entity.getSitiosRelacionados()!=null)
            {
                for(SitioWebEntity site:entity.getSitiosRelacionados())
                {
                    sitiosRelacionados.add(new SitioWebDTO(site));
                }

            }
            if(entity.getSoportes()!=null)
            {
                for(AdministradorEntity supp: entity.getSoportes())
                {
                    soportes.add(new AdministradorDTO(supp));
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
        if(solicitantes!=null)
        {
            List<AdministradorEntity> admEntity= new ArrayList<>();
            for(AdministradorDTO adms: solicitantes)
            {
                admEntity.add(adms.toEntity());
            }
            entity.setSolicitantes(admEntity);
        }
        if(sitiosRelacionados!=null)
        {

        }
        if(soportes!=null)
        {
            List<AdministradorEntity> admEntity= new ArrayList<>();
            for(AdministradorDTO adms: soportes)
            {
                admEntity.add(adms.toEntity());
            }
            entity.setSoportes(admEntity);

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

}

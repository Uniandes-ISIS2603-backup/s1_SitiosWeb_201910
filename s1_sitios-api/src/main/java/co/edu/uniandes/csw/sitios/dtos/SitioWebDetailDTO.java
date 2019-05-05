package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 */
public class SitioWebDetailDTO extends SitioWebDTO implements Serializable {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * Tecnologias usadas en el desarrollo del sitio
     */
    private List<TecnologiaDTO> tecnologiasDeDesarrollo;

    /**
     * Personas que administran el sitio web
     */
    private List<AdministradorDTO> administradores;

    /**
     * Sitios web que estan asociados a este
     */
    private List<SitioWebDTO> sitiosRelacionados;
    
    /**
     * EstadosWeb que estan asociados a un sitio web
     * @author Daniel Preciado
     */
    private List <EstadoWebDTO> estadosWeb;
    
    /**
     * Ticket que estan asociados a un sitio web
     * @author Daniel Preciado
     */
    private List <TicketDTO> ticketsSitio;
    
    /**
     * Notificaciones que estan asociadas a un sitio web
     * @author Daniel Preciado
     */
    private List <NotificacionDTO> notificaciones;
    
    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________    
    
    /**
     * Constructor para transformar un entity a DTO
     * 
     * @param entity  la entidad a partir de la cual se construye el DTO
     */
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
            else{
            tecnologiasDeDesarrollo=new ArrayList<>();
            }
            if(entity.getAdministradores()!=null)
            {
                for(AdministradorEntity adm: entity.getAdministradores())
                {
                    administradores.add(new AdministradorDTO(adm));
                }
            }
            else{
                administradores=new ArrayList();
            }
            if(entity.getSitiosRelacionados()!=null)
            {
                for(SitioWebEntity site:entity.getSitiosRelacionados())
                {
                    sitiosRelacionados.add(new SitioWebDTO(site));
                }

            }
            else
            {
                sitiosRelacionados =new ArrayList();
            }
            
            //se comprueba que los estados web no sea una lista nula
            //y se llenan los estados web que llegan por parametro
            if (entity.getEstadosWeb() != null) 
            {
                estadosWeb = new ArrayList<>();
                for (EstadoWebEntity entityEstado : entity.getEstadosWeb())
                {
                    estadosWeb.add(new EstadoWebDTO(entityEstado));
                }
            }
            else
            {
                estadosWeb=new ArrayList();
            }
            
            //se comprueba que los tickets no sea una lista nula
            //y se llenan los tickets que llegan por parametro
            if (entity.getTicketsSitio()!= null) 
            {
                ticketsSitio = new ArrayList<>();
                for (TicketEntity entityTicket : entity.getTicketsSitio())
                {
                    ticketsSitio.add(new TicketDTO(entityTicket));
                }
            }
            else
            {
                ticketsSitio =new ArrayList();
            }
            
            //se comprueba que las notificaciones no sean una lista nula
            //y se llenan con las notificaciones que llegan por parametro
            if (entity.getNotificaciones()!= null) 
            {
                notificaciones = new ArrayList<>();
                for (NotificacionEntity entityNotificacion: entity.getNotificaciones())
                {
                    notificaciones.add(new NotificacionDTO(entityNotificacion));
                }
            }
            else
            {
                notificaciones =new ArrayList();
            }
           
        }
        
        else
        {
            tecnologiasDeDesarrollo=new ArrayList();
            administradores=new ArrayList();
            sitiosRelacionados =new ArrayList();
            estadosWeb=new ArrayList();
            ticketsSitio =new ArrayList();
            notificaciones = new ArrayList();
        }
    }
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el SitioWeb.
     */
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
            entity.setAdministradores(admEntity);
        }
        if(sitiosRelacionados!=null)
        {
             List<SitioWebEntity> siteEntity= new ArrayList<>();
            for(SitioWebDTO ste: sitiosRelacionados)
            {
                siteEntity.add(ste.toEntity());
            }
            entity.setSitiosRelacionados(siteEntity);

        }
        
        //se verifican los estados web y si son diferentes de null
        //se agregan al entity a retornar
        if (estadosWeb != null) 
        {
            List<EstadoWebEntity> estadosWebEntity = new ArrayList<>();
            for (EstadoWebDTO dtoEstado : estadosWeb) 
            {
                estadosWebEntity.add(dtoEstado.toEntity());
            }
            entity.setEstadosWeb(estadosWebEntity);
        }
        
        //se verifican los tickets y si son diferentes de null
        //se agregan al entity a retornar
        if (ticketsSitio != null) 
        {
            List<TicketEntity> ticketsEntity = new ArrayList<>();
            for (TicketDTO dtoTicket : ticketsSitio) 
            {
                ticketsEntity.add(dtoTicket.toEntity());
            }
            entity.setTicketsSitio(ticketsEntity);
        }
        
        //se verifican las notificaciones y si son diferentes de null
        //se agregan al entity a retornar
        if (notificaciones != null) 
        {
            List<NotificacionEntity> notificacionesEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificacion : notificaciones) 
            {
                notificacionesEntity.add(dtoNotificacion.toEntity());
            }
            entity.setNotificaciones(notificacionesEntity);
        }
        
        return entity;
    }

    /**
     * obtiene las tecnologias de desarrollo empleas en el sitio
     * @return tecnologiasDeDesarrollo
     */
    public List<TecnologiaDTO> getTecnologiasDeDesarrollo() {
        return tecnologiasDeDesarrollo;
    }

    /**
     *  asigna las tecnologias de desarrollo empleas en el sitio
     * @param tecnologiasDeDesarrollo 
     */
    public void setTecnologiasDeDesarrollo(List<TecnologiaDTO> tecnologiasDeDesarrollo) {
        this.tecnologiasDeDesarrollo = tecnologiasDeDesarrollo;
    }

    /**
     * obtiene los administradores del sitioWeb
     * @return administradores
     */
    public List<AdministradorDTO> getAdministradores() {
        return administradores;
    }

    /**
     * asigna los administradores del sitioWeb
     * @param administradores 
     */
    public void setAdministradores(List<AdministradorDTO> administradores) {
        this.administradores = administradores;
    }

    /**
     * obtiene los sitios relacionados a este sitioWeb
     * @return sitiosRelacionados
     */
    public List<SitioWebDTO> getSitiosRelacionados() {

        return sitiosRelacionados;
    }

    /**
     * asigna los sitios relacionados a este sitioWeb
     * @param sitiosRelacionados 
     */
    public void setSitiosRelacionados(List<SitioWebDTO> sitiosRelacionados) {
        this.sitiosRelacionados = sitiosRelacionados;
    }

    
    /**
     * Devuelve la lista de estados web de un sitio.
     * @auhtor Daniel Preciado 
     * 
     * @return estadosWeb 
     */
    public List<EstadoWebDTO> getEstadosWeb() 
    {
        return estadosWeb;
    }

    /**
     * Modifica la lista de estados Web de un sitio.
     * @auhtor Daniel Preciado
     * 
     * @param estadosWeb 
     */
    public void setEstadosWeb(List<EstadoWebDTO> estadosWeb) 
    {
        this.estadosWeb = estadosWeb;
    }
    
    /**
     * Devuelve la lista de tickets de un sitio.
     * @auhtor Daniel Preciado 
     * 
     * @return ticketsSitio 
     */
    public List<TicketDTO> getTicketsSitio() 
    {
        return ticketsSitio;
    }

    /**
     * Modifica la lista de tickets de un sitio.
     * @auhtor Daniel Preciado
     * 
     * @param ticketsSitio 
     */
    public void setTicketsSitio(List<TicketDTO> ticketsSitio) 
    {
        this.ticketsSitio = ticketsSitio;
    }
    
    /**
     * obtiene la lista de notificaciones que ha tenido un sitio
     * @return 
     */
    public List <NotificacionDTO> getNotificaciones() {
        return notificaciones;
    }

    /**
     * asigna las notifiaciones de un sitioWeb
     * @param notificaciones 
     */
    public void setNotificaciones(List <NotificacionDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }
}

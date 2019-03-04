/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author estudiante
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(TecnologiaSitioWebResourse.class);
        resources.add(co.edu.uniandes.csw.sitios.filters.CORSFilter.class);
        resources.add(co.edu.uniandes.csw.sitios.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.sitios.mappers.ExceptionMapperA.class);
        resources.add(co.edu.uniandes.csw.sitios.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.AdministradorResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.DependenciaResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.EstadoWebResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.NotificacionResourse.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.PlataformaDeDespliegueResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.SitioWebResource.class);
//        resources.add(co.edu.uniandes.csw.sitios.resources.TecnologiaResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.SitioWebTecnologiaResourse.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.TecnologiaResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.TicketResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.TipoHostingResource.class);
        resources.add(co.edu.uniandes.csw.sitios.resources.UsuarioResource.class);
    }
    
}

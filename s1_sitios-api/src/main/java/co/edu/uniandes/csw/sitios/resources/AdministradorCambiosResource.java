/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.ejb.AdministradorCambiosLogic;
import co.edu.uniandes.csw.sitios.ejb.NotificacionLogic;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi
 */
public class AdministradorCambiosResource {
    private static final Logger LOGGER = Logger.getLogger(AdministradorCambiosResource.class.getName());

    @Inject
    private AdministradorCambiosLogic adminCambioLogic;

    @Inject
    private NotificacionLogic cambioLogic;
}

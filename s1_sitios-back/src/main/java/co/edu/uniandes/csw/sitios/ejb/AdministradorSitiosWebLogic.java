/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.util.List;
import javax.ejb.Stateless;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class AdministradorSitiosWebLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorSitiosWebLogic.class.getName());

    @Inject
    private AdministradorPersistence adminPersistence;

    @Inject
    private SitioWebPersistence sitioWebPersistence;

    /**
     * Asocia un Sitio web existente a un Admin
     *
     * @param adminsId Identificador de la instancia de Author
     * @param sitiosWebId Identificador de la instancia de Book
     * @return Instancia de SitioWebEntity que fue asociada a Admin
     */
    public SitioWebEntity addSitioWeb(Long adminsId, Long sitiosWebId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un sitio web al admin con id = {0}", adminsId);
        AdministradorEntity adminEntity = adminPersistence.find(adminsId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitiosWebId);
        sitioWebEntity.getAdministradores().add(adminEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un sitio web al admin con id = {0}", adminsId);
        return sitioWebPersistence.find(sitiosWebId);
    }

    /**
     * Obtiene una colección de instancias de SitioWebEntity asociadas a una
     * instancia de Administrador
     *
     * @param adminsId Identificador de la instancia de Administrador
     * @return Colección de instancias de SitioWebEntity asociadas a la
     * instancia de Admin
     */
    public List<SitioWebEntity> getSitiosWeb(Long adminsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los sitios web del admin con id = {0}", adminsId);
        AdministradorEntity adminEntity = adminPersistence.find(adminsId);
        List<SitioWebEntity> sitiosEntityList = adminEntity.getSitiosWebEntity();
        return sitiosEntityList;
    }

    /**
     * Obtiene una instancia de SitioWebEntity asociada a una instancia de Admin
     *
     * @param adminsId Identificador de la instancia de administrador
     * @param websitesId Identificador de la instancia de sitio web
     * @return La entidadd de sitio web del Admin
     * @throws BusinessLogicException Si el sitioWeb no está asociado al
     * administrador
     */
    public SitioWebEntity getSitioWeb(Long adminsId, Long websitesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el sitio web con id = {0} del admin con id = " + adminsId, websitesId);
        AdministradorEntity ae = adminPersistence.find(adminsId);
        SitioWebEntity swe = sitioWebPersistence.find(websitesId);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el sitio web con id = {0} del admin con id = " + adminsId, websitesId);
        if (!(ae.getSitiosWebEntity().contains(swe))) {
            throw new UnsupportedOperationException("El libro no está asociado al autor");
        }
        return swe;
    }

    /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param adminsId Identificador de la instancia de administrador
     * @param websites Colección de instancias de SitioWebEntity a asociar a
     * instancia de administrador
     * @return Nueva colección de SitioWebEntity asociada a la instancia de
     * Admin
     */
    public List<SitioWebEntity> replaceSitiosWeb(Long adminId, List<SitioWebEntity> sitiosWeb) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los sitio web asocidos al administrador con id = {0}", adminId);
        AdministradorEntity ae = adminPersistence.find(adminId);
        List<SitioWebEntity> swel = sitioWebPersistence.findAll();
        for (SitioWebEntity sitio : swel) {
            if (sitiosWeb.contains(sitio)) {
                if (!sitio.getAdministradores().contains(ae)) {
                    sitio.getAdministradores().add(ae);
                } else {
                    sitio.getAdministradores().remove(ae);
                }

            }
        }

        ae.setSitiosWebEntity(sitiosWeb);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los sitios web asocidos al admin con id = {0}", adminId);
        return ae.getSitiosWebEntity();
    }

    /**
     * Desasocia un Sitio web existente de un administrador existente
     *
     * @param adminId Identificador de la instancia de administrador
     * @param websitId Identificador de la instancia de sitio web
     */
    public void removeSitioWeb(Long adminId, Long websitId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un sitio web del administrador con id = {0}", adminId);
        AdministradorEntity ae = adminPersistence.find(adminId);
        SitioWebEntity swe = sitioWebPersistence.find(websitId);
        ae.getSitiosWebEntity().remove(swe);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un sitio web del administrador con id = {0}", adminId);
    }

}

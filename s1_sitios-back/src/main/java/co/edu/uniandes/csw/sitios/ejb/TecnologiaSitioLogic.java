package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class TecnologiaSitioLogic {

    private static final Logger LOGGER = Logger.getLogger(TecnologiaSitioLogic.class.getName());

    @Inject
    private SitioWebPersistence sitioWebPersistence;

    @Inject
    TecnologiaPersistence tecnologiaPersistence;

}

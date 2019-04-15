/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PlataformaDeDespliegueSitiosWebLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private SitioWebLogic sitioLogic;
    
    //@Inject
    //private SitiosWebPlataformaDeDespliegueLogic sitioPlataformaDeDespliegueLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PlataformaDeDespliegueEntity> data = new ArrayList<>();

    private List<SitioWebEntity> sitiosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlataformaDeDespliegueEntity.class.getPackage())
                .addPackage(SitioWebLogic.class.getPackage())
                .addPackage(PlataformaDeDesplieguePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from SitioWebEntity").executeUpdate();
        em.createQuery("delete from PlataformaDeDespliegueEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SitioWebEntity sitios = factory.manufacturePojo(SitioWebEntity.class);
            em.persist(sitios);
            sitiosData.add(sitios);
        }
        for (int i = 0; i < 3; i++) {
            PlataformaDeDespliegueEntity entity = factory.manufacturePojo(PlataformaDeDespliegueEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                sitiosData.get(i).setPlataformaDeDespliegue(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de SitiosWeb asociadas a una
     * instancia de PlataformaDeDespliegue.
     */
    @Test
    public void replacePlataformaDeDespliegueTest() throws BusinessLogicException {
      //  SitioWebEntity entity = sitiosData.get(0);
        //   sitioPlataformaDeDespliegueLogic.replacePlataformaDeDespliegue(entity.getId(), data.get(1).getId());
        //entity = sitioLogic.getWebSite(entity.getId());
        //Assert.assertEquals(entity.getPlataformaDeDespliegue(), data.get(1));
    //}

    /**
     * Prueba para desasociar un SitioWeb existente de un PlataformaDeDespliegue
     * existente
     *
     * @throws co.edu.uniandes.csw.sitiostore.exceptions.BusinessLogicException
     */
    //@Test
    //public void removeSitiosWebTest() throws BusinessLogicException {
        //    sitioPlataformaDeDespliegueLogic.removePlataformaDeDespliegue(sitiosData.get(0).getId());
      //  SitioWebEntity response = sitioLogic.getWebSite(sitiosData.get(0).getId());
        //Assert.assertNull(response.getPlataformaDeDespliegue());
    }
}

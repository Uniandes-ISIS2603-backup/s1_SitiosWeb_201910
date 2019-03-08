package co.edu.uniandes.csw.sitios.test.logic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.*;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.AssertTrue;
import org.apache.commons.lang.RandomStringUtils;
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

@RunWith(Arquillian.class)
public class SitioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private SitioWebLogic logic;


    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;


    private List<SitioWebEntity> data= new ArrayList<>();

    private List<AdministradorEntity> peopleData= new ArrayList<>();

    private List<EstadoWebEntity> stateData= new ArrayList<>();

    private List<TecnologiaEntity> tecsData= new ArrayList<>();
    
    private List<NotificacionEntity> notData= new ArrayList<>();


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SitioWebEntity.class.getPackage())
                .addPackage(SitioWebLogic.class.getPackage())
                .addPackage(SitioWebPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

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

    private void clearData() {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        em.createQuery("delete from SitioWebEntity").executeUpdate(); 
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
        em.createQuery("delete from NotificacionEntity").executeUpdate();         
        em.createQuery("delete from EstadoWebEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AdministradorEntity persona = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(persona);
            peopleData.add(persona);
        }
        for (int i = 0; i < 3; i++) {
            EstadoWebEntity site = factory.manufacturePojo(EstadoWebEntity.class);
            em.persist(site);
            stateData.add(site);
        }
        for (int i = 0; i < 3; i++) {
            TecnologiaEntity state = factory.manufacturePojo(TecnologiaEntity.class);
            em.persist(state);
            tecsData.add(state);
        }
        for (int i = 0; i < 3; i++) {
            NotificacionEntity not = factory.manufacturePojo(NotificacionEntity.class);
            em.persist(not);
            notData.add(not);
        }
        for (int i = 0; i < 3; i++) {
           SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
           newsite.setEstadosWeb(stateData);
           newsite.setSitiosRelacionados(data);
           newsite.setAdministradores(peopleData);
           newsite.setTechnologies(tecsData);
           newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
           newsite.setNotificacion(notData.get(0));
           em.persist(newsite);
           data.add(newsite);
        }
          
           
    }
    
    @Test
    public void createSiteTestOK()
    {
        try {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
            //em.persist(newsite);
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
             System.out.println("imagen:"+newsite.getImagen());

             data.add(newsite);
        } catch (BusinessLogicException e) {
            Assert.fail("no deberia generar error: "+e.getMessage());
        }  
    }
     @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail1() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(null);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
     @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail2() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(null);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail3() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(null);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail4() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(null);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail5() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(null);
             newsite.setNotificacion(notData.get(0));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail6() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             long value=-20;
             newsite.setAudienciaEsperada(value);
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail8() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setNombre("");
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
       @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail12() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setNombre(null);
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail7() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setDescripcion(null);
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail9() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setDescripcion(RandomStringUtils.randomAlphabetic(19));
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
       @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail10() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setCategoriaSitio(null);
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
      @Test (expected = BusinessLogicException.class)
    public void createSiteTestFail11() throws BusinessLogicException
    {
             SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
             newsite.setEstadosWeb(stateData);
             newsite.setSitiosRelacionados(data);
             newsite.setAdministradores(peopleData);
             newsite.setTechnologies(tecsData);
             newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
             newsite.setNotificacion(notData.get(0));
             newsite.setImagen("wrong format");
             SitioWebEntity createWebSite = logic.createWebSite(newsite);
    }
    
    @Test
    public void getWebSiteOKTest()
    {
        try {
            SitioWebEntity sitioWebEntity = logic.getWebSite(data.get(0).getId());
            Assert.assertEquals(sitioWebEntity, data.get(0));
        } catch (BusinessLogicException e) {
        }
     
    }
    
    @Test (expected = BusinessLogicException.class)
    public void getWebSiteFailTest() throws BusinessLogicException
    {
         logic.getWebSite(Long.MAX_VALUE);
    }
    
    @Test public void getAllSitesTest()
    {
        List<SitioWebEntity> sites = logic.getSites();
        Assert.assertEquals(sites.size(),data.size());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void deleteSiteTest() throws BusinessLogicException
    {
       
       SitioWebEntity newsite= factory.manufacturePojo(SitioWebEntity.class);
     
       newsite.setEstadosWeb(new ArrayList<EstadoWebEntity>());
       newsite.setSitiosRelacionados(new ArrayList<SitioWebEntity>());
       newsite.setAdministradores(new ArrayList<AdministradorEntity>());
       newsite.setTechnologies(new ArrayList<TecnologiaEntity>());
       newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
       newsite.setNotificacion(new NotificacionEntity());
      
       SitioWebEntity sitioWebEntity =logic.createWebSite(newsite);
       Long id= sitioWebEntity.getId();
       logic.deleteSite(id);
       logic.getWebSite(id);
    }
    
    @Test
    public void updateSiteTest()
    {
        try {
            
        SitioWebEntity entity = logic.getWebSite(data.get(0).getId());
        int value = new Random().nextInt()+1;
        entity.setAudienciaEsperada(value);
        logic.updateSitio(entity.getId(), entity);
        SitioWebEntity entity2 = logic.getWebSite(data.get(0).getId());
        Assert.assertEquals(entity2.getAudienciaEsperada(), value);
        
        } catch (BusinessLogicException e) {
            Assert.fail();
        }
    }
}


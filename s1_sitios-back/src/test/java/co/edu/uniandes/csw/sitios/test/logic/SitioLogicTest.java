package co.edu.uniandes.csw.sitios.test.logic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.*;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
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

@RunWith(Arquillian.class)
public class SitioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private SitioWebLogic logic;


    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;


    private List<SitioWebEntity> data= new ArrayList<SitioWebEntity>();

    private List<AdministradorEntity> peopleData= new ArrayList<AdministradorEntity>();

    private List<EstadoWebEntity> stateData= new ArrayList<EstadoWebEntity>();

    private ArrayList<TecnologiaEntity> tecsData= new ArrayList<TecnologiaEntity>();


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
        em.createQuery("delete from SitioWebEntity").executeUpdate();
        em.createQuery("delete from TecnologiaEntity").executeUpdate();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
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
           SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
    newsite.setHistorialDeEstados(stateData);
    newsite.setSitiosRelacionados(data);
    newsite.setSolicitantes(peopleData);
    newsite.setSoportes(peopleData);
    newsite.setTechnologies(tecsData);
    newsite.setEstadoActual(stateData.get(0));
    newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
    newsite.setResponsable(peopleData.get(0));
    em.persist(newsite);
    data.add(newsite);
        }
    }
    
    @Test
    public void createSiteTestOK()
    {
    SitioWebEntity newsite = factory.manufacturePojo(SitioWebEntity.class);
    newsite.setHistorialDeEstados(stateData);
    newsite.setSitiosRelacionados(data);
    newsite.setSolicitantes(peopleData);
    newsite.setSoportes(peopleData);
    newsite.setTechnologies(tecsData);
    newsite.setEstadoActual(stateData.get(0));
    newsite.setPlataformaDeDespliegue(new PlataformaDeDespliegueEntity());
    newsite.setResponsable(peopleData.get(0));
   try{
    SitioWebEntity entity=logic.createWebSite(newsite);
    Assert.assertEquals(entity.getEstadoActual(),stateData.get((0)));
    }
    catch(BusinessLogicException e)
    {
        Assert.fail("no deberia generar error: "+e.getMessage());
    }
    catch(Exception e)
    { 
        e.printStackTrace();
        //nunca deberia llegar aca , hay un error de compilacion si es asi
        //TODO uncomment if there its not java.lang.IllegalStateException on b
        Assert.fail("no deberia generar error: "+e.getMessage());
    }   
        
    }
}

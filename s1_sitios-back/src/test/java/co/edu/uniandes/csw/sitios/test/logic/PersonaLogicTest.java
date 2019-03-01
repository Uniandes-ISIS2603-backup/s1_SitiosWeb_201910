/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.test.logic;

import org.junit.Assert;
import co.edu.uniandes.csw.sitios.ejb.PersonaLogic;
import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PersonaPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Persona
 *
 * @author Allan Roy Corinaldi.
 */
@RunWith(Arquillian.class)
public class PersonaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PersonaLogic personaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PersonaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PersonaEntity.class.getPackage())
                .addPackage(PersonaLogic.class.getPackage())
                .addPackage(PersonaPersistence.class.getPackage())
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
        em.createQuery("delete from PersonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PersonaEntity entity = factory.manufacturePojo(PersonaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }


    /**
     * Prueba para crear un Persona.
     */
    @Test
    public void createPersonaTest() {
        PersonaEntity newEntity = factory.manufacturePojo(PersonaEntity.class);
        PersonaEntity result = personaLogic.createPersona(newEntity);
        Assert.assertNotNull(result);
        PersonaEntity entity = em.find(PersonaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

//    /**
//     * Prueba para eliminar un Persona
//     *
//     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
//     */
//    @Test
//    public void deletePersonaTest() throws BusinessLogicException {
//        PersonaEntity entity = data.get(0);
//        personaLogic.deletePersona(entity.getId());
//        PersonaEntity deleted = em.find(PersonaEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }

}

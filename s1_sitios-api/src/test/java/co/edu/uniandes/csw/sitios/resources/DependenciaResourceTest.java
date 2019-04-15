/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.DependenciaDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author estudiante
 */
public class DependenciaResourceTest {
    
    public DependenciaResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDependencia method, of class DependenciaResource.
     */
    @Test
    public void testCreateDependencia() throws Exception {
        System.out.println("createDependencia");
        DependenciaDTO dependencia = null;
        DependenciaResource instance = new DependenciaResource();
        DependenciaDTO expResult = null;
        DependenciaDTO result = instance.createDependencia(dependencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDependencia method, of class DependenciaResource.
     */
    @Test
    public void testGetDependencia() {
        System.out.println("getDependencia");
        long id = 0L;
        DependenciaResource instance = new DependenciaResource();
        DependenciaDTO expResult = null;
        DependenciaDTO result = instance.getDependencia(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDependencia method, of class DependenciaResource.
     */
    @Test
    public void testUpdateDependencia() {
        System.out.println("updateDependencia");
        int id = 0;
        DependenciaDTO dependencia = null;
        DependenciaResource instance = new DependenciaResource();
        DependenciaDTO expResult = null;
        DependenciaDTO result = instance.updateDependencia(id, dependencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDependencia method, of class DependenciaResource.
     */
    @Test
    public void testDeleteDependencia() {
        System.out.println("deleteDependencia");
        int id = 0;
        DependenciaResource instance = new DependenciaResource();
        instance.deleteDependencia(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

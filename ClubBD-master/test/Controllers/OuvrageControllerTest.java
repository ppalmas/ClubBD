/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Utilisateur
 */
public class OuvrageControllerTest {
    
    public OuvrageControllerTest() {
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
     * Test of ouvrageGet method, of class OuvrageController.
     */
    @Test
    public void testOuvrageGet() {
        System.out.println("ouvrageGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String idco = "0";
        String iddoc = "2";
        OuvrageController instance = new OuvrageController();
        ModelAndView expResult = new ModelAndView("ouvrage");
        expResult.addObject("idco",idco);
        expResult.addObject("idco",iddoc);
        ModelAndView result = instance.ouvrageGet(request, response, idco, iddoc);
        assertEquals(expResult.toString(), result.toString());
    }
    
}

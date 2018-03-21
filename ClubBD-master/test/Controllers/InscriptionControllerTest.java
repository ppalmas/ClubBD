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
public class InscriptionControllerTest {
    
    public InscriptionControllerTest() {
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
     * Test of handleInscription method, of class InscriptionController.
     */
    @Test
    public void testHandleInscription() {
        System.out.println("handleInscription");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        InscriptionController instance = new InscriptionController();
        ModelAndView expResult = new ModelAndView("inscription");;
        ModelAndView result = instance.handleInscription(request, response);
        assertEquals(expResult.toString(), result.toString());
    }
    
}

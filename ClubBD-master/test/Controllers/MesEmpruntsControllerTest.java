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
public class MesEmpruntsControllerTest {
    
    public MesEmpruntsControllerTest() {
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
     * Test of mesEmpruntsGet method, of class MesEmpruntsController.
     */
    @Test
    public void testMesEmpruntsGet() {
        System.out.println("mesEmpruntsGet");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String idco = "0";
        MesEmpruntsController instance = new MesEmpruntsController();
        ModelAndView expResult = new ModelAndView("mesemprunts");
        expResult.addObject("idco", idco);
        ModelAndView result = instance.mesEmpruntsGet(request, response, idco);
        assertEquals(expResult.toString(), result.toString());
    }
    
}

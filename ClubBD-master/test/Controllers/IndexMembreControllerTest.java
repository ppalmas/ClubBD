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
public class IndexMembreControllerTest {
    
    public IndexMembreControllerTest() {
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
     * Test of deco method, of class IndexMembreController.
     */
    @Test
    public void testDeco() {
        System.out.println("deco");
        IndexMembreController instance = new IndexMembreController();
        ModelAndView expResult = new ModelAndView("redirect:index.htm");
        ModelAndView result = instance.deco();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of handleDeco method, of class IndexMembreController.
     */
    @Test
    public void testHandleDeco() {
        System.out.println("handleDeco");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        IndexMembreController instance = new IndexMembreController();
        ModelAndView expResult = new ModelAndView("index");
        ModelAndView result = instance.handleDeco(request, response);
        assertEquals(expResult.toString(), result.toString());
    }
    
}

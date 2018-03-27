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
public class IndexControllerTest {
    
    public IndexControllerTest() {
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
     * Test of deco method, of class IndexController.
     */
    @Test
    public void testDeco() {
        System.out.println("deco");
        String idco = "0";
        IndexController instance = new IndexController();
        ModelAndView expResult = new ModelAndView("redirect:index.htm");
        expResult.addObject("idco", idco);
        System.out.println(expResult);
        ModelAndView result = instance.deco(idco);
        System.out.println(result);
        assertTrue(expResult.toString().equals(result.toString()));
    }

    /**
     * Test of handleDeco method, of class IndexController.
     */
    @Test
    public void testHandleDeco() {
        System.out.println("handleDeco");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String idco = "0";
        IndexController instance = new IndexController();
        ModelAndView expResult = new ModelAndView("index");
        ModelAndView result = instance.handleDeco(request, response, idco);
        assertTrue(expResult.toString().equals(result.toString()));
    }
    
}

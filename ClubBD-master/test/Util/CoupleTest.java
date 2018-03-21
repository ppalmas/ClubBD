/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilisateur
 */
public class CoupleTest {
    
    public CoupleTest() {
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
     * Test of getName method, of class Couple.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Couple instance = new Couple("nom","oui");
        String expResult = "nom";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Couple.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "nom";
        Couple instance = new Couple("oui","oui");
        instance.setName(name);
        assertEquals(instance.getName(),name);
    }

    /**
     * Test of getValue method, of class Couple.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Couple instance = new Couple("nom","oui");
        String expResult = "oui";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValue method, of class Couple.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        String value = "nom";
        Couple instance = new Couple("nom","oui");
        instance.setValue(value);
        assertEquals(instance.getValue(),value);
    }
    
}

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
public class LevenshteinTest {
    
    public LevenshteinTest() {
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
     * Test of distance method, of class Levenshtein.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        String mot1 = "ah";
        String mot2 = "Ah";
        int expResult = 1;
        int result = Levenshtein.distance(mot1, mot2);
        System.out.println(result);
        assertEquals(expResult, result);
        mot1 = "bla";
        mot2 = "blabla";
        expResult = 3;
        result = Levenshtein.distance(mot1, mot2);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of mini method, of class Levenshtein.
     */
    @Test
    public void testMini() {
        System.out.println("mini");
        int x1 = 1;
        int x2 = 2;
        int x3 = 3;
        int expResult = 1;
        int result = Levenshtein.mini(x1, x2, x3);
        assertEquals(expResult, result);
        x1 = 2;
        x2 = 2;
        x3 = 3;
        expResult = 2;
        result = Levenshtein.mini(x1, x2, x3);
        assertEquals(expResult, result);
        x1 = 2;
        x2 = 2;
        x3 = 1;
        expResult = 1;
        result = Levenshtein.mini(x1, x2, x3);
        assertEquals(expResult, result);
    }
    
}

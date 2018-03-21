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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Utilisateur
 */
public class PasswordHashTest {
    
    public PasswordHashTest() {
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
     * Test of hash method, of class PasswordHash.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        String password = "oheohe";
        String expResult = BCrypt.hashpw(password, BCrypt.gensalt(10));
        String result = PasswordHash.hash(password);
        assertTrue(PasswordHash.match(password, result)&&PasswordHash.match(password, expResult));
    }

    /**
     * Test of match method, of class PasswordHash.
     */
    @Test
    public void testMatch() {
        System.out.println("match");
        String mdp = "oheohe";
        String hashed = BCrypt.hashpw(mdp, BCrypt.gensalt(10));
        boolean expResult = BCrypt.checkpw(mdp, hashed);
        boolean result = PasswordHash.match(mdp, hashed);
        assertEquals(expResult, result);
    }
    
}

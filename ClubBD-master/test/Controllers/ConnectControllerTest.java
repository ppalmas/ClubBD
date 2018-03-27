/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Membre;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
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
public class ConnectControllerTest {
    
    public ConnectControllerTest() {
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
     * Test of post method, of class ConnectController.
     */
    @Test
    public void testPost() {
        System.out.println("post");
        String email = "typical.user@eleves.ec-nantes.fr";
        String mdp = "ouioui";
        ConnectController instance = new ConnectController();
        ModelAndView expResult = new ModelAndView("redirect:index_membre.htm");
        //Mise à jour des connexions dans la base de données
        ConnectManager cm = ConnectManagerImpl.getInstance();
        cm.checkConnection(); 

        //Récupération de l'utilisateur
        MembreManager pm = MembreManagerImpl.getInstance();
        Membre m = pm.findMembreByEmail(email);

        //Connexion de l'utilisateur 
        String idco = cm.connect(m);

        expResult.addObject("idco", idco);

        ModelAndView result = instance.post(email, mdp);
        System.out.println(result.toString());
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of get method, of class ConnectController.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String idco = "0";
        ConnectController instance = new ConnectController();
        ModelAndView expResult = null;
        MembreManager pm = MembreManagerImpl.getInstance();
        Membre m = pm.findMembre(idco);

        //Si cette personne existe (identifiant valide), on renvoie la page d'accueil d'un membre
        if (m != null) {
            expResult = new ModelAndView("index_membre");

            //Récupération de l'utilisateur
            expResult.addObject("email", m.getEmail());
            expResult.addObject("nom", m.getNom());
            expResult.addObject("prenom", m.getPrenom());
            expResult.addObject("id", m.getIdMembre());
            expResult.addObject("idStatut", m.getIdStatut().getIdStatut());
            //Connexion de l'utilisateur 
            expResult.addObject("idco", idco);

        } else {
            //Si la connexion est invalide, on renvoie la page d'accueil
            expResult = new ModelAndView("index");
            expResult.addObject("idco", 0);
        }
        ModelAndView result = instance.get(request, response, idco);
        assertEquals(expResult.toString(), result.toString());
    }
    
}

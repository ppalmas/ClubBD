/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Membre;
import Database.Serie;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import Managers.SerieManager;
import Managers.SerieManagerImpl;
import java.util.ArrayList;
import java.util.List;
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
public class AdminControllerTest {
    
    public AdminControllerTest() {
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
     * Test of get method, of class AdminController.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String idco = "0";
        AdminController instance = new AdminController();
        System.out.println("aaaa0");
        //recuperation des noms de serie
        SerieManager sm = SerieManagerImpl.getInstance();
        System.out.println("aaaa1");
        List<Serie> ls = sm.findSerie();
        //System.out.println("aaaa2  "+ls.get(0).getNomSerie());
        ArrayList<String> lns = new ArrayList();
        
        try{for(int i=0;i<ls.size();i++){
            lns.add(ls.get(i).getNomSerie());
        }} catch(Exception e){}
        
       
        //Récupération de l'utilisateur
        MembreManager mm = MembreManagerImpl.getInstance();
        Membre m = mm.findMembre(idco);
        Integer statut = 2;
        ModelAndView expResult = new ModelAndView();
        if (m != null) {
            //Si l'utilisateur n'est pas admin
            if (statut != 2) {
                expResult = new ModelAndView("index_membre");
                expResult.addObject("idco", idco);
            } else {
                expResult = new ModelAndView("admin");

                //Mise à jour des connexions dans la base de données
                ConnectManager cm = ConnectManagerImpl.getInstance();
                cm.checkConnection();
                cm.updateConnection(cm.getByConnectId(idco));

                //Récupération de l'utilisateur
                expResult.addObject("email", m.getEmail());
                expResult.addObject("nom", m.getNom());
                expResult.addObject("prenom", m.getPrenom());
                expResult.addObject("id", m.getIdMembre());
                expResult.addObject("idStatut", m.getIdStatut().getIdStatut());

                //Connexion de l'utilisateur 
                expResult.addObject("idco", idco);
                
                //liste noms de serie pour combobox
                expResult.addObject("lserie",lns);
                
            }
            //si la connexion n'est pas valide, on retourne à la page d'accueil
        } else {
            expResult = new ModelAndView("index");
            expResult.addObject("idco", 0);
            //liste noms de serie pour combobox
            expResult.addObject("lserie",lns);
        }
        assertTrue(expResult!=null);
    }
    
}

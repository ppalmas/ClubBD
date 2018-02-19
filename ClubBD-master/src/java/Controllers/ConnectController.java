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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author Paola
 */
public class ConnectController {
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@RequestParam("email") String email, @RequestParam("mdp") String mdp) {
        ModelAndView r = new ModelAndView("redirect:index_membre.htm");

        //Mise à jour des connexions dans la base de données
        ConnectManager cm = ConnectManagerImpl.getInstance();
        cm.checkConnection(); 

        //Récupération de l'utilisateur
        MembreManager pm = MembreManagerImpl.getInstance();
        Membre m = pm.findMembreByEmail(email);

        //Connexion de l'utilisateur 
        String idco = cm.connect(m);
        r.addObject("idco", idco);
        

        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco){

        //On récupère la personne associée à l'identifiant de connexion
        MembreManager pm = MembreManagerImpl.getInstance();
        Membre m = pm.findMembre(idco);

        //Si cette personne existe (identifiant valide), on renvoie la page d'accueil d'un membre
        if (m != null) {
            ModelAndView result = new ModelAndView("index_membre");

            //Récupération de l'utilisateur
            result.addObject("email", m.getEmail());
            result.addObject("nom", m.getNom());
            result.addObject("prenom", m.getPrenom());
            result.addObject("id", m.getIdMembre());

            //Connexion de l'utilisateur 
            result.addObject("idco", idco);


            return result;

        } else {
            //Si la connexion est invalide, on renvoie la page d'accueil
            ModelAndView result = new ModelAndView("index");
            result.addObject("idco", 0);
            return result;
        }
    }
}

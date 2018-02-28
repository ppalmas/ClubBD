/**
 * ********************************************************************
 * Controller Admin
 * --------------------------------------------------------------------
 * Affichage de la page d'admin
 *********************************************************************
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


public class AdminController {

    /**
     * Etablir la connexion avec la page admin
     *
     * @param request
     * @param response
     * @param idco
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco) {

        //Récupération de l'utilisateur
        MembreManager mm = MembreManagerImpl.getInstance();
        Membre m = mm.findMembre(idco);
        Integer statut = m.getIdStatut().getIdStatut();

        if (m != null) {
            //Si l'utilisateur n'est pas admin
            if (statut != 2) {
                ModelAndView result = new ModelAndView("index_membre");
                result.addObject("idco", idco);
                return result;
            } else {
                ModelAndView result = new ModelAndView("admin");

                //Mise à jour des connexions dans la base de données
                ConnectManager cm = ConnectManagerImpl.getInstance();
                cm.checkConnection();
                cm.updateConnection(cm.getByConnectId(idco));

                //Récupération de l'utilisateur
                result.addObject("email", m.getEmail());
                result.addObject("nom", m.getNom());
                result.addObject("prenom", m.getPrenom());
                result.addObject("id", m.getIdMembre());
                result.addObject("idStatut", m.getIdStatut().getIdStatut());

                //Connexion de l'utilisateur 
                result.addObject("idco", idco);

                return result;

            }
            //si la connexion n'est pas valide, on retourne à la page d'accueil
        } else {
            ModelAndView result = new ModelAndView("index");
            result.addObject("idco", 0);
            return result;
        }
    }

}

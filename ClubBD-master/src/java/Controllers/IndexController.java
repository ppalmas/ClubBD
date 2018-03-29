/**
 * ********************************************************************
 * Controller le page index
 * --------------------------------------------------------------------
 * Affichage de la page d'accueil
 *********************************************************************
 */
package Controllers;


import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Affichage de la page d'accueil
 * @author Utilisateur
 */
@Controller
public class IndexController {

    /**
     * Méthode POST : redirection vers la page index
     * @param idco
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deco(@RequestParam("idco") String idco) {
        ModelAndView r = new ModelAndView("redirect:index.htm");
        r.addObject("idco", idco);
        return r;
    }

    /**
     * Méthode GET
     * @param request
     * @param response
     * @param idco
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleDeco(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco) {

        //Résultat
        ModelAndView r = new ModelAndView("index");

        //Instanciation des managers
        ConnectManager cm = ConnectManagerImpl.getInstance();
        MembreManager pm = MembreManagerImpl.getInstance();

        //Si il y a une personne à déconnecter
        if (!idco.equals("0")) {
            cm.deconnect(pm.findMembre(idco));
        }

        //Mise à jour des connexion dans la base de données
        cm.checkConnection();
        return r;
    }    

}

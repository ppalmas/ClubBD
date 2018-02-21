/**
 * ********************************************************************
 * Controller Inscription
 * --------------------------------------------------------------------
 * Affichage de la page d'inscription
 *********************************************************************
 */
package Controllers;

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
public class InscriptionController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleInscription(HttpServletRequest request, HttpServletResponse response) {

        //Résultat
        ModelAndView r = new ModelAndView("inscription");
        return r;
    }
}

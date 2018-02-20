/**
 * ********************************************************************
 * Controller IndexMembre
 * --------------------------------------------------------------------
 * Gère la déconnexion
 *********************************************************************
 */
package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Paola
 */
public class IndexMembreController {
 
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deco() {
        ModelAndView r = new ModelAndView("redirect:index.htm");
        
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleDeco(HttpServletRequest request, HttpServletResponse response) {

        //Résultat
        ModelAndView r = new ModelAndView("index");
        return r;
    }
}

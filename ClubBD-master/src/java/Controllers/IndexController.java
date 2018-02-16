/**
 * ********************************************************************
 * Controller Home page (index)
 * --------------------------------------------------------------------
 * Affichage de la page d'accueil
 *********************************************************************
 */
package Controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

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

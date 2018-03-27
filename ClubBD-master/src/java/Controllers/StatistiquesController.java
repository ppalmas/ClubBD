/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Recherche;
import Managers.StatistiquesManager;
import Managers.StatistiquesManagerImpl;
import Util.CoupleStats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

    

/**
 *
 * @author centrale
 */
public class StatistiquesController {

    public StatistiquesController() {
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleStatistiques(HttpServletRequest request, HttpServletResponse response) {
        StatistiquesManager theStatistiquesManager = StatistiquesManagerImpl.getInstance();
        List<CoupleStats> theRecherches = theStatistiquesManager.stats();
        //Résultat
        ModelAndView r = new ModelAndView("admin");
        r.addObject("stats", theRecherches);
        r.addObject("crit", theStatistiquesManager.getCrit());
        r.addObject("nb_recherche", theStatistiquesManager.getNb_recherche());
        return r;
    }
}

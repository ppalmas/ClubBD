/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kevin
 */
public class MesEmpruntsController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleInscription(HttpServletRequest request, HttpServletResponse response) {

        //Résultat
        ModelAndView r = new ModelAndView("mesemprunts");
        return r;
    }    
}

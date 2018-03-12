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
 * @author Kevin
 */
public class MesEmpruntsController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mesEmpruntsGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco) {

        //Récupération de l'utilisateur
//        MembreManager mm = MembreManagerImpl.getInstance();
//        Membre m = mm.findMembre(idco);
//        Integer statut = m.getIdStatut().getIdStatut();

        ModelAndView result = new ModelAndView("mesemprunts");
        result.addObject("idco", idco);
        return result;
    }
}

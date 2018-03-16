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
public class OuvrageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView ouvrageGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco) {
        
        ModelAndView result = new ModelAndView("ouvrage");
        if (idco.equals("0")){
            result.addObject("idco",idco);
        }
        else{
            MembreManager mm = MembreManagerImpl.getInstance();
            Membre m = mm.findMembre(idco);

            ConnectManager cm = ConnectManagerImpl.getInstance();
            cm.checkConnection();
            cm.updateConnection(cm.getByConnectId(idco));
            //Résultat
            result.addObject("idStatut", m.getIdStatut().getIdStatut());
            result.addObject("idco", idco);
            //result.addObject("idStatut", idStatut);
        }
        return result;
    }
}

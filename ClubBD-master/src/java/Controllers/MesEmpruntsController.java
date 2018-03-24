/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import Database.Createurdocument;
import Database.Document;
import Database.Emprunt;
import Database.Membre;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import Managers.EmpruntManager;
import Managers.EmpruntManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import Utilities.Emprunts;
import java.util.ArrayList;
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
        
        
//        Récupération de l'utilisateur
//        MembreManager mm = MembreManagerImpl.getInstance();
//        Membre m = mm.findMembre(idco);
//        Integer statut = m.getIdStatut().getIdStatut();

        ModelAndView result = new ModelAndView("mesemprunts");
        result.addObject("idco", idco);
        
        MembreManager mm = MembreManagerImpl.getInstance();
        Membre id = mm.findMembre(idco);
        
        EmpruntManager em = EmpruntManagerImpl.getInstance();
        ArrayList<Emprunt> emp = new ArrayList<>(em.findEmprunt(id));  
        ArrayList<Emprunts> emps = new ArrayList<>();
        ArrayList<Emprunts> reservs = new ArrayList<>();
        
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        for(Emprunt e : emp){
            Document d = e.getIdDocument();
            
            Date dRet = e.getDateRetourne();
            if (dRet == null){
                
                Date dEmp = e.getDateEmprunt();
                if (dEmp == null){
                    Emprunts es2 = new Emprunts(d.getIdDocument(), d.getTitre(), d.getCote(), "", "", "", "");
                    reservs.add(es2);
                }
                else{
                    Emprunts es = new Emprunts(d.getIdDocument(), d.getTitre(), d.getCote(), formatter.format(e.getDateReserve()), formatter.format(dEmp), formatter.format(e.getDateRetour()), ""); 
                    emps.add(es);   
                }
            }
        }
        result.addObject("emprunts", emps);
        result.addObject("reservations", reservs);
        return result;
    }
}

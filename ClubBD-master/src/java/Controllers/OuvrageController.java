/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Createurdocument;
import Database.Document;
import Database.Emprunt;
import Database.Etat;
import Database.Genre;
import Database.Genredocument;
import Database.Membre;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import Managers.EmpruntManager;
import Managers.EmpruntManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import Utilities.AuteurIllustrateur;
import java.util.ArrayList;
import java.util.Date;
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
    public ModelAndView ouvrageGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco, @RequestParam("iddoc") String iddoc) {
    
        ModelAndView result = new ModelAndView("ouvrage");
        
        if (idco.equals("0")){
            result.addObject("idco",idco);
            result.addObject("idStatut", 0);
        }
        
        else{
            MembreManager mm = MembreManagerImpl.getInstance();
            Membre m = mm.findMembre(idco);

            ConnectManager cm = ConnectManagerImpl.getInstance();
            cm.checkConnection();
            cm.updateConnection(cm.getByConnectId(idco));

            result.addObject("idStatut", m.getIdStatut().getIdStatut());
            result.addObject("idco", idco);
        }
        
        Integer idDoc = Integer.parseInt(iddoc);
        
        DocumentManager dm = DocumentManagerImpl.getInstance();
        Document d = dm.findDocument(idDoc);
        result.addObject("titre", d.getTitre());
        result.addObject("cote", d.getCote());
        result.addObject("numero", d.getNumero());
        result.addObject("description", d.getDescription());
        
        String etat = d.getIdEtat().getEtat();
        result.addObject("etat", etat);
        
        String serie = d.getIdSerie().getNomSerie();
        result.addObject("serie", serie);
        
        boolean disponible = dm.isAvailable(idDoc);
        if (disponible){
            result.addObject("dispo", "disponible");
            result.addObject("emprunt", "libre");
        }
        else{
            result.addObject("dispo", "nondisponible");
            
            EmpruntManager em = EmpruntManagerImpl.getInstance();
            ArrayList<Emprunt> emp = new ArrayList<>(em.findEmpruntDoc(d));
            String emprunt = "";
            
            for(Emprunt e : emp){
                Date dRet = e.getDateRetourne();
                if (dRet == null){
                    Date dEmp = e.getDateEmprunt();
                    Integer idEmp = e.getIdEmprunt();
                    result.addObject("idemp", idEmp);
                    if (dEmp == null){
                        emprunt = "reserve";
                    }
                    else{
                        emprunt = "emprunte";
                    }
                    break;
                }
            }
            result.addObject("emprunt", emprunt);
        }
        
        ArrayList<Createurdocument> cd = dm.findCreateur(idDoc);
        ArrayList<AuteurIllustrateur> createurs = new ArrayList<AuteurIllustrateur>();
        
        for(Createurdocument c : cd){
            AuteurIllustrateur ai = new AuteurIllustrateur(c.getIdCreateur().getNomCreateur(), c.getIdCreateur().getPrenomCreateur(), c.getPoste());
            createurs.add(ai);
        }
        result.addObject("createurs", createurs);
        
        ArrayList<Genredocument> gd = dm.findGenre(idDoc);
        ArrayList<String> genres = new ArrayList<>();
        
        String s = "";
        for(Genredocument g : gd){
            s = g.getIdGenre().getNomGenre();
            genres.add(s);
        }
        
        result.addObject("genres", genres);
        
        //ArrayList<Createurdocument> findCreateur(idDoc)
        
        result.addObject("image", d.getImageDocument());
        //result.addObject("image", "theprohecyistrue.jpg");
        
        result.addObject("iddoc", iddoc);
        
        
        return result;
    }
}

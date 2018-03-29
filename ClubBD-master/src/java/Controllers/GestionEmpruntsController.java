/*
 * Controller GestionEmprunts
 * Gère l'affichage des emprunts pour la gestion par les membres du club (validation, retour).
 * --------------------------------------------------------------------
 * Gestion de l'affichage de la page d'admin
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
import Utilities.EmpruntEmprunteur;
import Utilities.Emprunts;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Gère l'affichage des emprunts pour la gestion par les membres du club (validation, retour).
 * Gestion de l'affichage de la page d'admin
 * @author Kevin
 */
public class GestionEmpruntsController {

    /**
     *
     * @param request
     * @param response
     * @param idco
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView gestionEmpruntsGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String idco) {

//        Récupération de l'utilisateur
        MembreManager mm = MembreManagerImpl.getInstance();
        Membre m = mm.findMembre(idco);
        Integer statut = m.getIdStatut().getIdStatut();
        if (statut == 3) {
            ModelAndView result = new ModelAndView("index_membre");
            result.addObject("idco", idco);
            return result;
        } else {
            ModelAndView result = new ModelAndView("gestionemprunts");
            result.addObject("idco", idco);

            Membre id = mm.findMembre(idco);

            EmpruntManager em = EmpruntManagerImpl.getInstance();
            ArrayList<Emprunt> emp = new ArrayList<>(em.findEmprunts());
            ArrayList<EmpruntEmprunteur> emps = new ArrayList<>();
            ArrayList<EmpruntEmprunteur> reservs = new ArrayList<>();

            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            for (Emprunt e : emp) {
                Document d = e.getIdDocument();

                Date dRet = e.getDateRetourne();
                if (dRet == null) {
                    Membre emprunteur = e.getIdMembre();

                    Date dEmp = e.getDateEmprunt();
                    if (dEmp == null) {
                        EmpruntEmprunteur es2 = new EmpruntEmprunteur(d.getIdDocument(), d.getTitre(), d.getCote(), formatter.format(e.getDateReserve()), "", "", "", emprunteur.getEmail(), emprunteur.getNom(), emprunteur.getPrenom());
                        //Emprunts es2 = new Emprunts(d.getIdDocument(), d.getTitre(), d.getCote(), "", "", "", "");
                        reservs.add(es2);
                    } else {
                        EmpruntEmprunteur es = new EmpruntEmprunteur(d.getIdDocument(), d.getTitre(), d.getCote(), formatter.format(e.getDateReserve()), formatter.format(dEmp), formatter.format(e.getDateRetour()), "", emprunteur.getEmail(), emprunteur.getNom(), emprunteur.getPrenom());
                        emps.add(es);
                    }
                }
            }
            result.addObject("emprunts", emps);
            result.addObject("reservations", reservs);
            return result;
        }
    }
}

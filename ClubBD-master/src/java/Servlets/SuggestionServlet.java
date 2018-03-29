/**
 * ********************************************************************
 * Servlet Suggestion
 * --------------------------------------------------------------------
 * Servlet pour renvoyer un résultat aléatoire parmi la liste des ouvrages
 * de la base de données, comme suggestion
 *********************************************************************
 */
package Servlets;

import Database.Createur;
import Database.Createurdocument;
import Database.Document;
import Managers.ConnectManager;
import Managers.ConnectManagerImpl;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import java.io.IOException;
import java.util.List;

import java.util.ArrayList;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "SuggestionServlet", urlPatterns = {"/SuggestionServlet"})
public class SuggestionServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Initialisation variable
        String res = "";
        //Récupération de l'id max des documents de la bdd
        DocumentManager dm = DocumentManagerImpl.getInstance();
        int n = dm.getMaxId();

        //Générer un nombre aléatoire parmi les id existants
        int idOuvrage = ThreadLocalRandom.current().nextInt(1, n);
        int idOuvrage2 = idOuvrage;
        while (idOuvrage == idOuvrage2) {
            idOuvrage2 = ThreadLocalRandom.current().nextInt(1, n);
        }
        //Trouver les 2 ouvrages  par leur id
        Document d = dm.findDocument(idOuvrage);
        Document d2 = dm.findDocument(idOuvrage2);
        //Récupérer la disponibilité des documents
        Boolean dispo1 = dm.isAvailable(idOuvrage);
        Boolean dispo2 = dm.isAvailable(idOuvrage2);
        //Récupérer le titre
        String titre = d.getTitre();
        String titre2 = d2.getTitre();
        //Récupérer les auteurs, en passant par createurDocument d'abord
        ArrayList<Createurdocument> liste = dm.findCreateur(idOuvrage);
        ArrayList<Createurdocument> liste2 = dm.findCreateur(idOuvrage2);
        String auteurs = "";
        String auteurs2 = "";
        String id1 = String.valueOf(idOuvrage);
        String id2 = String.valueOf(idOuvrage2);
        for (int i = 0; i < liste.size(); i++) {
            auteurs += liste.get(i).getIdCreateur().getNomCreateur() + " " + liste.get(i).getIdCreateur().getPrenomCreateur() + ";*";           
        }
        for (int i = 0; i < liste2.size(); i++) {
            auteurs2 += liste2.get(i).getIdCreateur().getNomCreateur() + " " + liste2.get(i).getIdCreateur().getPrenomCreateur() + " "+
                    idOuvrage2 + ";*";           
        }
        
        res=titre + "*/*" + auteurs + "*/*" + dispo1 + "*/*" + id1 + "*//*" + titre2 + "*/*" + auteurs2 + "*/*" + dispo2 + "*/*" + id2;

        //Envoi de la réponse : true si les login/mdp correspondent et false sinon
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(res + "");

    }
}

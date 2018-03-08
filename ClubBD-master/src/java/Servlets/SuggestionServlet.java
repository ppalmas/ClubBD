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

        //Récupération de l'id max des documents de la bdd
        DocumentManager dm = DocumentManagerImpl.getInstance();
        int n = dm.getMaxId();

        //Générer un nombre aléatoire parmi les id existants
        int idOuvrage = ThreadLocalRandom.current().nextInt(1, n);
        //TRouver l'ouvrage par son id
        Document d = dm.findDocument(idOuvrage);
        String titre = d.getTitre();
        ArrayList<Createurdocument> liste = dm.findCreateur(idOuvrage);
        String auteurs = "";
        for (int i=0; i<liste.size();i++){
            auteurs += liste.get(i).getIdCreateur().getNomCreateur() + " " + liste.get(i).getIdCreateur().getPrenomCreateur() + ";*";
        }
        String res = titre + "*/*" + auteurs;

        //Envoi de la réponse : true si les login/mdp correspondent et false sinon
        ConnectManager m = ConnectManagerImpl.getInstance();
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(res + "");

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Document;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author centrale
 */
@WebServlet(name = "RerchercheDocServlet", urlPatterns = {"/RechercheDocServlet"})
public class RechercheDocServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres via le javascript recherche2.js
        String titre = request.getParameter("titre");
        String serie = request.getParameter("serie");
        String auteur = request.getParameter("auteur");
        String sujet = request.getParameter("sujet");
        String all = request.getParameter("all");
        String res = "";

        //transformation en liste critères
        ArrayList<String> criteres = new ArrayList();
        if (all == null || all == "") {
            if ((titre != "") || (serie != "") || (auteur != "") || (sujet != "")) {
                criteres.set(0, "titre:"+titre);
                criteres.set(1, "serie:"+serie);
                criteres.set(2, "auteur:"+auteur);
                criteres.set(3, "sujet:"+sujet);
            }
        } else {
            criteres.set(0, all);
        }
        List<Document> l = null;
        //Création d'une entité Document
        DocumentManager dm = DocumentManagerImpl.getInstance();
        try {
            l = dm.findDocumentSearch(criteres);
//TODO : faire afficher les résultats dans une liste d'items cf cours
            //creation d'un json pour exploiter les reponses dans le js
            for (int i = 0; i < l.size(); i++) {

                res = res + "{\"titre\":\"" + l.get(i).getTitre() + "\",";

                if (l.get(i).getIdSerie() != null) {
                    res = res + "\"serie\":\"" + l.get(i).getIdSerie().getNomSerie() + "\",";
                } else {
                    res = res + "\"serie\":\"(hors série)\",";
                }

                res = res + "\"cote\":\"" + l.get(i).getCote() + "\", \"id\":\"" + l.get(i).getIdDocument().toString() + "\"}";

                res = res + ",";
            }

        } catch (Exception e) {

        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("{\"resultats\":[" + res.subSequence(0, res.length() - 1).toString() + "],\"nb\":\"" + l.size() + "\"}");
        response.getWriter().write("{\"resultats\":[" + res.subSequence(0, res.length() - 1).toString() + "],\"nb\":\"" + l.size() + "\"}"); // Réponse : resultats

    }
}

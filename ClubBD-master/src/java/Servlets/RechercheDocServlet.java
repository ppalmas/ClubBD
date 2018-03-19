/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Createurdocument;
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
        int m = 0;
        int n = 0;
        //transformation en liste critères
        ArrayList<String> criteres = new ArrayList();
        Boolean b = true;//b vaut true si le champ all uniquement est complété, ou
        //si aucun champ n'est complété
        if (all == null || all == "" || all.equals("")) {
            if ((titre != "") || (serie != "") || (auteur != "") || (sujet != "")) {
                for (int i = 0; i < 4; i++) {
                    criteres.add("");
                }
                criteres.set(0, "titre:" + titre);
                criteres.set(1, "sujet:" + sujet);
                criteres.set(2, "serie:" + serie);
                criteres.set(3, "auteur:" + auteur);
                
            }
            b = false;
        } else {
            criteres.add("");
            criteres.set(0, all);
            b = true;
        }
        List<Document> l = null;
        //Création d'une entité Document
        DocumentManager dm = DocumentManagerImpl.getInstance();
        l = dm.findDocumentSearch(criteres, b);
        if ((l == null)||l.isEmpty()) {
            res = res + "{\"titre\":\"" + "\"," + "\"serie\":\"\"," + "\"cote\":\"" + "\"," + "\"id\":\"" + "\",";
            res = res + "\"auteurs\":" + "{" + "\"auteur" + "\":\"" + "\"}" + "},";
            m = 0 ;
            
        } else {
            m = l.size();
//TODO : faire afficher les résultats dans une liste d'items cf cours
            //creation d'un json pour exploiter les reponses dans le js
            for (int i = 0; i < m; i++) {
                //Ecriture de chaque document
                res = res + "{\"titre\":\"" + l.get(i).getTitre() + "\",";
                if (l.get(i).getIdSerie() != null) {
                    res = res + "\"serie\":\"" + l.get(i).getIdSerie().getNomSerie() + "\",";
                } else {
                    res = res + "\"serie\":\"(Hors série)\",";
                }

                res = res + "\"cote\":\"" + l.get(i).getCote() + "\"," + "\"id\":\"" + l.get(i).getIdDocument().toString() + "\",";

                res = res + "\"auteurs\":";

                ArrayList<Createurdocument> liste = dm.findCreateur(l.get(i).getIdDocument());
                n = liste.size();
                res = res + "{";
                if (n==0){
                    res = res + "\"auteur\":\"\"}";
                } else if (n <= 1) {
                    res = res + "\"auteur" + (n - 1) + "\":\"" + liste.get(n - 1).getIdCreateur().getNomCreateur()
                            + " " + liste.get(n - 1).getIdCreateur().getPrenomCreateur() + "\"}";
                } else {
                    for (int j = 0; j < n - 1; j++) {
                        res = res + "\"auteur" + j + "\":\"" + liste.get(j).getIdCreateur().getNomCreateur()
                                + " " + liste.get(j).getIdCreateur().getPrenomCreateur() + "\",";
                    }
                    res = res + "\"auteur" + (n - 1) + "\":\"" + liste.get(n - 1).getIdCreateur().getNomCreateur()
                            + " " + liste.get(n - 1).getIdCreateur().getPrenomCreateur() + "\"}";
                }
                res = res + ",\"nbA\":\"" + n + "\"";
                res = res + "},";

            }
        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("{\"resultats\":[" + res.subSequence(0, res.length() - 1).toString() + "],\"nb\":\"" + m + "\"}");
        response.getWriter().write("{\"resultats\":[" + res.subSequence(0, res.length() - 1).toString() + "],\"nb\":\"" + m + "\"}"); // Réponse : resultats

    }
}

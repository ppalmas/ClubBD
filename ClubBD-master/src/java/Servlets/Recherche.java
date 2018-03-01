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
@WebServlet(name = "Recherche", urlPatterns = {"/Recherche"})
public class Recherche extends HttpServlet {

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
        String cote = request.getParameter("cote");
        String res = "";

        //transformation en liste critères
        ArrayList<String> criteres = new ArrayList();
        criteres.add(null);
        criteres.add(null);
        criteres.add(null);
        criteres.set(0, titre);
        criteres.set(1, serie);
        criteres.set(2, cote);
        List<Document> l = null;

        //Création d'une entité Document
        DocumentManager dm = DocumentManagerImpl.getInstance();
        try {
            l = dm.findDocument(criteres);

            for (int i = 0; i < l.size(); i++) {

                res = res + l.get(i).getTitre() + "<br>";
            }
        } catch (Exception e) {

        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(res); // Réponse : resultats

    }
}

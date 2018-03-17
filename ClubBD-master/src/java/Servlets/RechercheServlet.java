/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Document;
import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.text.Normalizer;
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
@WebServlet(name = "RerchercheServlet", urlPatterns = {"/RechercheServlet"})
public class RechercheServlet extends HttpServlet {

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
        String titre = Normalizer.normalize(request.getParameter("titre"),Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String cote = request.getParameter("cote");
        String serie = request.getParameter("serie");

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

        JsonObject json = new JsonObject();
        JsonArray resultats = new JsonArray();

        try {
            l = dm.findDocument(criteres);
            System.out.println("requete ok  "+l.size());
            //creation d'un json pour exploiter les reponses dans le js

            for (int i = 0; i < l.size(); i++) {
                JsonObject temp = new JsonObject();
                temp.addProperty("titre", l.get(i).getTitre());

                try {
                    temp.addProperty("serie", l.get(i).getIdSerie().getNomSerie());
                    
                } catch (Exception e) {
                    temp.addProperty("serie", "");
                    
                }

                try {
                    temp.addProperty("numero", l.get(i).getNumero().toString());
                } catch (Exception e) {
                    temp.addProperty("numero", "");
                }
                temp.addProperty("description", l.get(i).getDescription());
                temp.addProperty("commentaire", l.get(i).getCommentaire());
                temp.addProperty("image", l.get(i).getImageDocument());
                temp.addProperty("cote", l.get(i).getCote());
                temp.addProperty("id", l.get(i).getIdDocument().toString());
                temp.addProperty("etat", l.get(i).getIdEtat().getIdEtat().toString());
                resultats.add(temp);

            }

            json.add("resultats", resultats);
            json.addProperty("nb", l.size());

        } catch (Exception e) {

        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        System.out.println(new Gson().toJson(json));
        response.getWriter().write(new Gson().toJson(json)); // Réponse : resultats

    }

}

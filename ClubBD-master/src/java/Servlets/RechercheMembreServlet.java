/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Membre;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
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
@WebServlet(name = "RerchercheMembreServlet", urlPatterns = {"/RechercheMembreServlet"})
public class RechercheMembreServlet extends HttpServlet {

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
        // Récupération des paramètres via le javascript
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");

        List<Membre> l = null;

        //Création d'une entité Document
        MembreManager mm = MembreManagerImpl.getInstance();

        JsonObject json = new JsonObject();
        JsonArray resultats = new JsonArray();

        try {
            l = mm.findMembre(prenom,nom);
            //creation d'un json pour exploiter les reponses dans le js
          
            for (int i = 0; i < l.size(); i++) {
                JsonObject temp = new JsonObject();
                temp.addProperty("prenom", l.get(i).getPrenom());

                temp.addProperty("nom", l.get(i).getNom());

                temp.addProperty("idstat", l.get(i).getIdStatut().getIdStatut());

                temp.addProperty("id", l.get(i).getIdMembre());

                resultats.add(temp);

            }

            json.add("resultats", resultats);
            json.addProperty("nb", l.size());

        } catch (Exception e) {

        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        System.out.println((new Gson().toJson(json)));

        response.getWriter().write(new Gson().toJson(json)); // Réponse : resultats

    }

}

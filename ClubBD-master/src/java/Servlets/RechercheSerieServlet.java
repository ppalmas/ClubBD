/**
 * ********************************************************************
 * Servlet RechercheSerie
 * --------------------------------------------------------------------
 * Gestion de la recherche de série
 *********************************************************************
 */
package Servlets;

import Database.Serie;
import Managers.SerieManager;
import Managers.SerieManagerImpl;
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
 * Gestion de la recherche de série
 * @author centrale
 */
@WebServlet(name = "RerchercheSerieServlet", urlPatterns = {"/RechercheSerieServlet"})
public class RechercheSerieServlet extends HttpServlet {

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
        String seriename = request.getParameter("seriename");

        List<Serie> l = null;

        //Création d'une entité Document
        SerieManager sm = SerieManagerImpl.getInstance();

        JsonObject json = new JsonObject();
        JsonArray resultats = new JsonArray();

        try {
            l = sm.findSerie(seriename);
            //creation d'un json pour exploiter les reponses dans le js
            for (int i = 0; i < l.size(); i++) {
                JsonObject temp = new JsonObject();
                temp.addProperty("seriename", l.get(i).getNomSerie());

                temp.addProperty("seriedesc", l.get(i).getSerieDescription());

                
                temp.addProperty("id", l.get(i).getIdSerie().toString());
                
                temp.addProperty("complet", l.get(i).getComplet());
                
                resultats.add(temp);

            }

            json.add("resultats", resultats);
            json.addProperty("nb", l.size());

        } catch (Exception e) {

        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write(new Gson().toJson(json)); // Réponse : resultats

    }

}

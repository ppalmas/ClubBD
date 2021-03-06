/**
 * ********************************************************************
 * Servlet SauveRecherche
 * --------------------------------------------------------------------
 * Gestion de la sauvegarde des recherches
 *********************************************************************
 */
package Servlets;

import Managers.RechercheManager;
import Managers.RechercheManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gestion de la sauvegarde des recherches
 * @author Paola
 */
@WebServlet(name = "SauveRerchercheServlet", urlPatterns = {"/SauveRechercheServlet"})
public class SauveRechercheServlet  extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres via le javascript recherche2.js
        String titre = request.getParameter("titre");
        String serie = request.getParameter("serie");
        String all = request.getParameter("all");
        RechercheManager rm = RechercheManagerImpl.getInstance();
        Boolean b = false;
        
        if ((all!="")||!(all.equals(""))){
            rm.insert(all, "");
            b = true;
        } else {
            rm.insert(titre, serie);
        }
        
        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write(b+""); // Réponse : resultats
    }
}

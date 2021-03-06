/**
 * ********************************************************************
 * Servlet SauveProposition
 * --------------------------------------------------------------------
 * Gestion de la sauvegarde de propositions
 *********************************************************************
 */
package Servlets;

import Managers.PropositionManager;
import Managers.PropositionManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gestion de la sauvegarde de propositions
 * @author Paola
 */
@WebServlet(name = "SauvePropositionServlet", urlPatterns = {"/SauvePropositionServlet"})
public class SauvePropositionServlet extends HttpServlet {

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
        String commentaire = request.getParameter("commentaire");
        Boolean b = true;
        PropositionManager pm = PropositionManagerImpl.getInstance();
        try {
            pm.insert(titre, commentaire);

        } catch (Exception e) {
            b = false;
        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + ""); // Réponse : resultats

    }

}

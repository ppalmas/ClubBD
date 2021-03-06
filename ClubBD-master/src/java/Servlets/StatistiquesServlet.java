/**
 * ********************************************************************
 * Servlet Statistiques
 * --------------------------------------------------------------------
 * Gestion des statistiques
 *********************************************************************
 */
package Servlets;

import Managers.StatistiquesManager;
import Managers.StatistiquesManagerImpl;
import Util.CoupleStats;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gestion des statistiques
 * @author centrale
 */
@WebServlet(name = "StatistiquesServlet", urlPatterns = {"/StatistiquesServlet"})
public class StatistiquesServlet extends HttpServlet{
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
        String res = "";
        ArrayList<CoupleStats> l;
        int m;
        //Création d'une entité Document
        StatistiquesManager sm = StatistiquesManagerImpl.getInstance();
        l = sm.stats();
        if ((l == null)||l.isEmpty()) {
            res = res + "{\"nomRech\":\"" + "\"," + "\"nbRech\":\""+"\""+"},";
            m = 0 ;
            
        } else {
            m = l.size();
            for (int i = 0; i < m; i++) {
                //Ecriture de chaque document
                res = res + "{\"nomRech\":\"" + l.get(i).getName() + "\"";
                res = res + ",\"nbRech\":\"" + l.get(i).getValue() + "\"";
                res = res + "},";

            }
        }

        // Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("{\"resultats\":[" + res.subSequence(0, res.length() - 1).toString() + "],\"nb\":\"" + m + "\"}"); // Réponse : resultats

    }

}

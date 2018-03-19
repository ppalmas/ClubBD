/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import Managers.SerieManager;
import Managers.SerieManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author centrale
 */
@WebServlet(name = "ModifierServlet", urlPatterns = {"/ModifierServlet"})
public class ModifierServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //On récupère les paramètres
        String titre = request.getParameter("titre");
        String cote = request.getParameter("cote");
        String etat = request.getParameter("etat");
        String serie = request.getParameter("serie");
        String numero = request.getParameter("numero");
        String desc = request.getParameter("description");
        String comm = request.getParameter("commentaire");
        String img = request.getParameter("image");
        String iddoc = request.getParameter("idm");

        Integer type = Integer.parseInt(request.getParameter("type"));

        //pour série
        String complet = request.getParameter("complet");
        String seriename = request.getParameter("seriename");
        String seriedesc = request.getParameter("seriedesc");
        String idserie = request.getParameter("idserie");

        

        Boolean b = false;

        if (type == 0) {
            try {
                DocumentManager dm = DocumentManagerImpl.getInstance();
                dm.update(iddoc, titre, cote, etat, serie, numero, desc, comm, img);

                b = true;
            } catch (Exception e) {
            }

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(b + "");

        } else if (type == 1) {
            try {
                SerieManager sm = SerieManagerImpl.getInstance();
                sm.update(idserie, seriename, seriedesc,complet);

                b = true;
            } catch (Exception e) {
            }

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(b + "");
        }

    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */

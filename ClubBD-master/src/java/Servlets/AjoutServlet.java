/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;


/**
 *
 * @author centrale
 */
@WebServlet(name = "AjoutServlet", urlPatterns = {"/AjoutServlet"})
public class AjoutServlet extends HttpServlet {

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
        String titre = StringEscapeUtils.escapeHtml4(request.getParameter("titre"));
        String cote = StringEscapeUtils.escapeHtml4(request.getParameter("cote"));
        String etat = StringEscapeUtils.escapeHtml4(request.getParameter("etat"));
        String serie = StringEscapeUtils.escapeHtml4(request.getParameter("serie"));
        String numero = StringEscapeUtils.escapeHtml4(request.getParameter("numero"));
        String desc = StringEscapeUtils.escapeHtml4(request.getParameter("description"));
        String comm = StringEscapeUtils.escapeHtml4(request.getParameter("commentaire"));
        String img = StringEscapeUtils.escapeHtml4(request.getParameter("image"));

        DocumentManager dm = DocumentManagerImpl.getInstance();
        System.out.println("0");

        Boolean b = false;
        try {

            dm.insert(titre, cote, etat, serie, numero, desc, comm, img);

            b = true;
        } catch (Exception e) {
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + "");

    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */

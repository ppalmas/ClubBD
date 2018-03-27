/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Membre;
import Managers.EmpruntManager;
import Managers.EmpruntManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ReserveServlet", urlPatterns = {"/ReserveServlet"})
public class ReserveServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
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
        String iddoc = request.getParameter("iddoc");
        String idco = request.getParameter("idco");

        Date date_reserve = new Date();

        MembreManager mm = MembreManagerImpl.getInstance();
        Integer id = mm.findMembre(idco).getIdMembre();
        //Membre id = mm.findMembre(idco);

        EmpruntManager em = EmpruntManagerImpl.getInstance();
        Integer idDoc = Integer.parseInt(iddoc);
        
        Boolean b = false;
        try {
            em.insert(id, idDoc, date_reserve);
            b = true;
        } catch (Exception erreur) {
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + "");
        }
}

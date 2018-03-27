/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Emprunt;
import Managers.EmpruntManager;
import Managers.EmpruntManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ValiderServlet", urlPatterns = {"/ValiderServlet"})
public class ValiderServlet extends HttpServlet {

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
        String idco = request.getParameter("idco");
        String idemp = request.getParameter("idemp");
        String dateRetour = request.getParameter("date_retour")+" 23:59:59";

        Boolean b = false;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_retour = formatter.parse(dateRetour);
            Date date_emprunt = new Date();

            MembreManager mm = MembreManagerImpl.getInstance();
            Integer idstaff = mm.findMembre(idco).getIdMembre();

            Integer idEmp = Integer.parseInt(idemp);
            EmpruntManager em = EmpruntManagerImpl.getInstance();
            Emprunt emprunt = em.findEmpruntByID(idEmp);
            
            try {
                em.updateEmprunt(emprunt, date_retour, date_emprunt, idstaff);
                b = true;
            } catch (Exception erreur) {
            }
        } catch (ParseException ex) {
            Logger.getLogger(ValiderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + "");
    }
        
}

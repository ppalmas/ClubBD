/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.Membre;
import Database.Statut;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paola
 */
@WebServlet(name = "ControlInscriptionServlet", urlPatterns = {"/ControlInscriptionServlet"})
public class ControlInscriptionServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        //Paramètres
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");

        //Personne
        MembreManager mm = MembreManagerImpl.getInstance();
        Membre m = mm.findMembreByEmail(email);
        Boolean b = (m == null); // Booléen vrai si aucune personne ne correspond à l'email donné

        //Inscription si aucune personne ne correspond à l'email donné
        Statut s = new Statut(3);
        if (b) {
            mm.insert(email, mdp, name, firstname, s);
        }

        //Envoi de la réponse
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(b + ""); // Réponse : true si l'inscription a été effectuée et false si l'email était déjà dans la base
    }
    
}

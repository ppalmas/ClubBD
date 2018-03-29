/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Managers.DocumentManager;
import Managers.DocumentManagerImpl;
import Managers.MembreManager;
import Managers.MembreManagerImpl;
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
        String cnp0 = request.getParameter("cnp0");
        String cnp1 = request.getParameter("cnp1");
        String cnp2 = request.getParameter("cnp2");
        String cnp3 = request.getParameter("cnp3");
        String cnp4 = request.getParameter("cnp4");

        Integer type = Integer.parseInt(request.getParameter("type"));

        String genre = request.getParameter("genre");

        //pour série
        String complet = request.getParameter("complet");
        String seriename = request.getParameter("seriename");
        String seriedesc = request.getParameter("seriedesc");
        String idserie = request.getParameter("idserie");

        //pour membre
        String idstat = request.getParameter("idstat");
        String idmembre = request.getParameter("idmembre");
        
        //si un identifiant (cote ou titre serie) a changé
        String chg = request.getParameter("chg");
        System.out.println(chg);

        Boolean b = false;
        System.out.println("chg");
        if (!Boolean.parseBoolean(chg)){
            System.out.println("cest ok");
        } else {}



        if (type == 0) {
            try {
                DocumentManager dm = DocumentManagerImpl.getInstance();
                
                if (!Boolean.parseBoolean(chg) || dm.exist(cote)==false) {
                    System.out.println("doc avtupdate");
                    dm.update(iddoc, titre, cote, etat, serie, numero, desc, comm, img, cnp0, cnp1, cnp2, cnp3, cnp4, genre);
                    System.out.println("doc okokokk");
                    b = true;
                }
            } catch (Exception e) {
            }

        } else if (type == 1) {
            try {
                SerieManager sm = SerieManagerImpl.getInstance();
                if (!Boolean.parseBoolean(chg) || sm.exist(titre)==false) {
                    System.out.println("serie avtupdate");
                    sm.update(idserie, seriename, seriedesc, complet);

                    b = true;
                }
                System.out.println("serie okokokk");
            } catch (Exception e) {
            }

        } else if (type == 4) {
            try {
                MembreManager mm = MembreManagerImpl.getInstance();
                mm.updateStat(Integer.parseInt(idstat), Integer.parseInt(idmembre));

                b = true;
            } catch (Exception e) {

            }
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

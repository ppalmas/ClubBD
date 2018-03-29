
/**
 * ********************************************************************
 * Servlet Ajout
 * --------------------------------------------------------------------
 * Gestion des ajouts de documents
 *********************************************************************
 */
package Servlets;

import Managers.CreateurManager;
import Managers.CreateurManagerImpl;
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
 * Gestion des ajouts de documents
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
        String titre = request.getParameter("titre");
        Integer type = Integer.parseInt(request.getParameter("type"));
        String cote = request.getParameter("cote");
        String etat = request.getParameter("etat");
        String serie = request.getParameter("serie");
        String numero = request.getParameter("numero");
        String desc = request.getParameter("description");
        String comm = request.getParameter("commentaire");
        String img = request.getParameter("image");
        String seriename = request.getParameter("seriename");
        String seriedesc = request.getParameter("seriedesc");

        String nomcrea = request.getParameter("nomcrea");
        String prenomcrea = request.getParameter("prenomcrea");

        String cnp0 = request.getParameter("cnp0");
        String cnp1 = request.getParameter("cnp1");
        String cnp2 = request.getParameter("cnp2");
        String cnp3 = request.getParameter("cnp3");
        String cnp4 = request.getParameter("cnp4");

        String genre = request.getParameter("genre");

        if (type == 0) {

            DocumentManager dm = DocumentManagerImpl.getInstance();

            Boolean b = false;
            try {
                if (!dm.exist(cote)) {
                    dm.insert(titre, cote, etat, serie, numero, desc, comm, img, cnp0, cnp1, cnp2, cnp3, cnp4, genre);

                    b = true;
                }
            } catch (Exception e) {
            }

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(b + "");

        } else if (type == 1) {

            SerieManager sm = SerieManagerImpl.getInstance();

            Boolean b = false;
            try {
                if (!sm.exist(titre)) {
                    System.out.println("serie existe pas");
                    sm.insert(seriename, seriedesc);
System.out.println("serie insérée");
                    b = true;
                }
            } catch (Exception e) {
            }

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(b + "");

        } else if (type == 2) {

            
            CreateurManager cm = CreateurManagerImpl.getInstance();

            Boolean b = false;
            try {

                cm.insert(nomcrea, prenomcrea);

                b = true;
            } catch (Exception e) {
            }

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(b + "");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write("false" + "");
        }
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
